package com.group_imposter.migrate.batch.cbtrn02c;

import com.group_imposter.migrate.accessor.*;
import com.group_imposter.migrate.constant.ReturnValueConst;
import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.file.FileOpenMode;
import com.group_imposter.migrate.file.FileStatusConstant;
import com.group_imposter.migrate.file.fileaccess.DalyrejsFile_FileAccess;
import com.group_imposter.migrate.model.*;
import com.group_imposter.migrate.repository.AccountRecordRepository;
import com.group_imposter.migrate.repository.CardXrefRecordRepository;
import com.group_imposter.migrate.repository.TranCatBalRecordRepository;
import com.group_imposter.migrate.repository.TranRecordRepository;
import com.group_imposter.migrate.util.ConditionUtil;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.StringUtil;
import com.group_imposter.migrate.util.math.fDecimal;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class CBTRN02CProcessor implements ItemProcessor<DalytranRecord, TranRecord> {

    @Autowired
    private CardXrefRecordRepository cardXrefRecordRepository;

    @Autowired
    private AccountRecordRepository accountRecordRepository;

    @Autowired
    private TranCatBalRecordRepository tranCatBalRecordRepository;

    @Autowired
    private TranRecordRepository tranRecordRepository;

    private DalyrejsFile_FileAccess dalyrejsFile = new DalyrejsFile_FileAccess("src/main/resources/data/ASCII/dalyrejs.txt");
    private FdTranRecord fdTranRecord = new FdTranRecord();
    private FdTranfileRec fdTranfileRec = new FdTranfileRec();
    private FdXreffileRec fdXreffileRec = new FdXreffileRec();
    private FdRejsRecord fdRejsRecord = new FdRejsRecord();
    private FdAcctfileRec fdAcctfileRec = new FdAcctfileRec();
    private FdTranCatBalRecord fdTranCatBalRecord = new FdTranCatBalRecord();
    private DalytranRecord dalytranRecord = new DalytranRecord();
    private DalytranStatus dalytranStatus = new DalytranStatus();
    private TranRecord tranRecord = new TranRecord();
    private TranfileStatus tranfileStatus = new TranfileStatus();
    private CardXrefRecord cardXrefRecord = new CardXrefRecord();
    private XreffileStatus xreffileStatus = new XreffileStatus();
    private DalyrejsStatus dalyrejsStatus = new DalyrejsStatus();
    private AccountRecord accountRecord = new AccountRecord();
    private AcctfileStatus acctfileStatus = new AcctfileStatus();
    private TranCatBalRecord tranCatBalRecord = new TranCatBalRecord();
    private TcatbalfStatus tcatbalfStatus = new TcatbalfStatus();
    private IoStatus ioStatus = new IoStatus();
    private short twoBytesBinary;
    private IoStatus04 ioStatus04 = new IoStatus04();
    private int applResult;
    private String endOfFile = "N";
    private int abcode;
    private int timing;
    private CobolTs cobolTs = new CobolTs();
    private String db2FormatTs = FieldFormat.format(26, ValueConst.SPACE);
    private RejectRecord rejectRecord = new RejectRecord();
    private WsValidationTrailer wsValidationTrailer = new WsValidationTrailer();
    private WsCounters wsCounters = new WsCounters();
    private String wsCreateTrancatRec = "N";
    private BigDecimal wsTempBal = new BigDecimal(0);
//    private Cee3abd cee3abd = null;


    @Override
    public TranRecord process(DalytranRecord item) throws Exception {
        this.dalytranRecord = item;
        this._0300DalyrejsOpen();
        wsCounters.setWsTransactionCount(wsCounters.getWsTransactionCount() + 1);
        wsValidationTrailer.setWsValidationFailReason((short)0);
        wsValidationTrailer.setWsValidationFailReasonDesc(FieldFormat.format(76, ValueConst.SPACE));
        if (this._1500ValidateTran() == ReturnValueConst.BACK){
            //just return empty record then the writer will know how to handle it
            return new TranRecord();
        }
        if (wsValidationTrailer.getWsValidationFailReason() == 0){
            if (_2000PostTransaction() == ReturnValueConst.BACK){
                return new TranRecord();
            }
        } else {
            wsCounters.setWsRejectCount(wsCounters.getWsRejectCount() + 1);
            if (_2500WriteRejectRec() == ReturnValueConst.BACK){
                return new TranRecord();
            }
        }
        this._9300DalyrejsClose();
        return tranRecord;
    }

    private int _0300DalyrejsOpen(){
        applResult = 8;
        DalyrejsStatus_Accessor.setDalyrejsStatus(dalyrejsStatus, dalyrejsFile.open(FileOpenMode.OUT));
        if (StringUtil.compare(DalyrejsStatus_Accessor.getDalyrejsStatus(dalyrejsStatus), "00") == 0){
            applResult = 0;
        } else {
            applResult = 12;
        }
        if (ApplResult_Accessor.isApplAok(applResult)){
        } else {
            System.out.println("ERROR OPENING DALY REJECTS FILE");
            IoStatus_Accessor.setIoStatus(ioStatus, DalyrejsStatus_Accessor.getDalyrejsStatus(dalyrejsStatus));
            if (_9910DisplayIoStatus() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
            if (_9999AbendProgram() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }
        return ReturnValueConst.CONTINUE;
    }

    private int _1500ValidateTran(){
        if (_1500ALookupXref() == ReturnValueConst.BACK){
            return ReturnValueConst.BACK;
        }
        if (wsValidationTrailer.getWsValidationFailReason() == 0){
            if (_1500BLookupAcct() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }
        return ReturnValueConst.CONTINUE;
    }

    private int _1500ALookupXref(){
        fdXreffileRec.setFdXrefCardNum(dalytranRecord.getDalytranCardNum());
        XreffileStatus_Accessor.setXreffileStatus(xreffileStatus, "00");
        cardXrefRecord = cardXrefRecordRepository.findById(fdXreffileRec.getFdXrefCardNum()).orElseGet(() -> {
            System.out.println("Reject trans for key :" + fdXreffileRec.getFdXrefCardNum());
            wsValidationTrailer.setWsValidationFailReason((short)100);
            wsValidationTrailer.setWsValidationFailReasonDesc("INVALID CARD NUMBER FOUND");
            XreffileStatus_Accessor.setXreffileStatus(xreffileStatus, FileStatusConstant.FAILED);
            return new CardXrefRecord();
        });
//        CardXrefRecord_Accessor.setCardXrefRecord(cardXrefRecord, FieldFormat.format(50, cardXrefRecord.toString()));
        return ReturnValueConst.CONTINUE;
    }

    private int _1500BLookupAcct(){
        fdAcctfileRec.setFdAcctId(cardXrefRecord.getXrefAcctId());
        AcctfileStatus_Accessor.setAcctfileStatus(acctfileStatus, "00");
        accountRecord = accountRecordRepository.findById(fdAcctfileRec.getFdAcctId()).orElseGet(() -> {
            wsValidationTrailer.setWsValidationFailReason((short)101);
            wsValidationTrailer.setWsValidationFailReasonDesc("ACCOUNT RECORD NOT FOUND");
            AcctfileStatus_Accessor.setAcctfileStatus(acctfileStatus, FileStatusConstant.FAILED);
            return new AccountRecord();
        });

        if (StringUtil.compare(AcctfileStatus_Accessor.getAcctfileStatus(acctfileStatus), "00") == 0){
            wsTempBal = accountRecord.getAcctCurrCycCredit().subtract(accountRecord.getAcctCurrCycDebit()).add(dalytranRecord.getDalytranAmt());

            if (accountRecord.getAcctCreditLimit().compareTo(wsTempBal) < 0){
                wsValidationTrailer.setWsValidationFailReason((short)102);
                wsValidationTrailer.setWsValidationFailReasonDesc("OVERLIMIT TRANSACTION");
            }

            if (accountRecord.getAcctExpiraionDate().compareTo(dalytranRecord.getDalytranOrigTs()) < 0){
                wsValidationTrailer.setWsValidationFailReason((short)103);
                wsValidationTrailer.setWsValidationFailReasonDesc("TRANSACTION RECEIVED AFTER ACCT EXPIRATION");
            }
        }
//        AccountRecord_Accessor.setAccountRecord(accountRecord, FieldFormat.format(300, accountFile.getCurrentLine()));
        return ReturnValueConst.CONTINUE;
    }

    private int _2000PostTransaction(){
        tranRecord.setTranId(dalytranRecord.getDalytranId());
        tranRecord.setTranTypeCd(dalytranRecord.getDalytranTypeCd());
        tranRecord.setTranCatCd(dalytranRecord.getDalytranCatCd());
        tranRecord.setTranSource(dalytranRecord.getDalytranSource());
        tranRecord.setTranDesc(dalytranRecord.getDalytranDesc());
        tranRecord.setTranAmt(dalytranRecord.getDalytranAmt());
        tranRecord.setTranMerchantId(dalytranRecord.getDalytranMerchantId());
        tranRecord.setTranMerchantName(dalytranRecord.getDalytranMerchantName());
        tranRecord.setTranMerchantCity(dalytranRecord.getDalytranMerchantCity());
        tranRecord.setTranMerchantZip(dalytranRecord.getDalytranMerchantZip());
        tranRecord.setTranCardNum(dalytranRecord.getDalytranCardNum());
        tranRecord.setTranOrigTs(dalytranRecord.getDalytranOrigTs());
        if (zGetDb2FormatTimestamp() == ReturnValueConst.BACK){
            return ReturnValueConst.BACK;
        }
        tranRecord.setTranProcTs(db2FormatTs);
        if (_2700UpdateTcatbal() == ReturnValueConst.BACK){
            return ReturnValueConst.BACK;
        }
        if (_2800UpdateAccountRec() == ReturnValueConst.BACK){
            return ReturnValueConst.BACK;
        }
        if (_2900WriteTransactionFile() == ReturnValueConst.BACK){
            return ReturnValueConst.BACK;
        }
        return ReturnValueConst.CONTINUE;
    }

    private int _2500WriteRejectRec(){
        rejectRecord.setRejectTranData(DalytranRecord_Accessor.getDalytranRecord(dalytranRecord));
        rejectRecord.setValidationTrailer(WsValidationTrailer_Accessor.getWsValidationTrailer(wsValidationTrailer));
        applResult = 8;
        FdRejsRecord_Accessor.setFdRejsRecord(fdRejsRecord, RejectRecord_Accessor.getRejectRecord(rejectRecord));
        DalyrejsStatus_Accessor.setDalyrejsStatus(dalyrejsStatus, dalyrejsFile.writeFromObject(fdRejsRecord));
        if (StringUtil.compare(DalyrejsStatus_Accessor.getDalyrejsStatus(dalyrejsStatus), "00") == 0){
            applResult = 0;
        } else {
            applResult = 12;
        }
        if (ApplResult_Accessor.isApplAok(applResult)){
        } else {
            System.out.println("ERROR WRITING TO REJECTS FILE");
            IoStatus_Accessor.setIoStatus(ioStatus, DalyrejsStatus_Accessor.getDalyrejsStatus(dalyrejsStatus));
            if (_9910DisplayIoStatus() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
            if (_9999AbendProgram() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }
        return ReturnValueConst.CONTINUE;
    }

    private int _2700UpdateTcatbal(){
        fdTranCatBalRecord.getFdTranCatBalRecordId().setTrancatAcctId(cardXrefRecord.getXrefAcctId());
        fdTranCatBalRecord.getFdTranCatBalRecordId().setTrancatTypeCd(dalytranRecord.getDalytranTypeCd());
        fdTranCatBalRecord.getFdTranCatBalRecordId().setTrancatCd(dalytranRecord.getDalytranCatCd());
        wsCreateTrancatRec = "N";
        TcatbalfStatus_Accessor.setTcatbalfStatus(tcatbalfStatus, "00");

        System.out.println("Search with id: " + fdTranCatBalRecord.getFdTranCatBalRecordId());
        tranCatBalRecord = tranCatBalRecordRepository.findById(fdTranCatBalRecord.getFdTranCatBalRecordId()).orElseGet(() -> {
//            TcatbalfStatus_Accessor.setTcatbalfStatus(tcatbalfStatus, FileStatusConstant.FAILED);
            System.out.println("TCATBAL record not found for key :" + fdTranCatBalRecord.getFdTranCatBalRecordId() + " creating...");
            wsCreateTrancatRec = "Y";
            return new TranCatBalRecord();
        });

//        TranCatBalRecord_Accessor.setTranCatBalRecord(tranCatBalRecord, FieldFormat.format(50, tcatbalFile.getCurrentLine()));
        if (StringUtil.compare(TcatbalfStatus_Accessor.getTcatbalfStatus(tcatbalfStatus), "00") == 0 || StringUtil.compare(TcatbalfStatus_Accessor.getTcatbalfStatus(tcatbalfStatus), "23") == 0){
            applResult = 0;
        } else {
            applResult = 12;
        }
        if (ApplResult_Accessor.isApplAok(applResult)){
        } else {
            System.out.println("ERROR READING TRANSACTION BALANCE FILE");
            IoStatus_Accessor.setIoStatus(ioStatus, TcatbalfStatus_Accessor.getTcatbalfStatus(tcatbalfStatus));
            if (_9910DisplayIoStatus() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
            if (_9999AbendProgram() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }
        if (StringUtil.compare(wsCreateTrancatRec, "Y") == 0){
            if (_2700ACreateTcatbalRec() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        } else {
            if (_2700BUpdateTcatbalRec() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }
        return ReturnValueConst.CONTINUE;
    }

    private int _2700ACreateTcatbalRec(){
        tranCatBalRecord = new TranCatBalRecord();
        tranCatBalRecord.getTranCatBalRecordId().setTrancatAcctId(cardXrefRecord.getXrefAcctId());
        tranCatBalRecord.getTranCatBalRecordId().setTrancatTypeCd(dalytranRecord.getDalytranTypeCd());
        tranCatBalRecord.getTranCatBalRecordId().setTrancatCd(dalytranRecord.getDalytranCatCd());
        tranCatBalRecord.setTranCatBal(new fDecimal(tranCatBalRecord.getTranCatBal(), 2).add(dalytranRecord.getDalytranAmt(), 2).bigDecimalValue());

        System.out.println("Create trancatbal record with id: " + tranCatBalRecord.getTranCatBalRecordId());
        tranCatBalRecordRepository.save(tranCatBalRecord);

        FdTranCatBalRecord_Accessor.setFdTranCatBalRecord(fdTranCatBalRecord, TranCatBalRecord_Accessor.getTranCatBalRecord(tranCatBalRecord));
        TcatbalfStatus_Accessor.setTcatbalfStatus(tcatbalfStatus, "00");
        if (StringUtil.compare(TcatbalfStatus_Accessor.getTcatbalfStatus(tcatbalfStatus), "00") == 0){
            applResult = 0;
        } else {
            applResult = 12;
        }
        if (ApplResult_Accessor.isApplAok(applResult)){
        } else {
            System.out.println("ERROR WRITING TRANSACTION BALANCE FILE");
            IoStatus_Accessor.setIoStatus(ioStatus, TcatbalfStatus_Accessor.getTcatbalfStatus(tcatbalfStatus));
            if (_9910DisplayIoStatus() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
            if (_9999AbendProgram() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }
        return ReturnValueConst.CONTINUE;
    }


    private int _2700BUpdateTcatbalRec(){
        tranCatBalRecord.setTranCatBal(new fDecimal(tranCatBalRecord.getTranCatBal(), 2).add(dalytranRecord.getDalytranAmt(), 2).bigDecimalValue());
        System.out.println("Update trancatbal id: " + tranCatBalRecord.getTranCatBalRecordId() + " value: " + tranCatBalRecord.getTranCatBal());
        System.out.println("Result update: " + tranCatBalRecordRepository.saveAndFlush(tranCatBalRecord));

        TcatbalfStatus_Accessor.setTcatbalfStatus(tcatbalfStatus, "00");

        if (StringUtil.compare(TcatbalfStatus_Accessor.getTcatbalfStatus(tcatbalfStatus), "00") == 0){
            applResult = 0;
        } else {
            applResult = 12;
        }
        if (ApplResult_Accessor.isApplAok(applResult)){
        } else {
            System.out.println("ERROR REWRITING TRANSACTION BALANCE FILE");
            IoStatus_Accessor.setIoStatus(ioStatus, TcatbalfStatus_Accessor.getTcatbalfStatus(tcatbalfStatus));
            if (_9910DisplayIoStatus() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
            if (_9999AbendProgram() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }
        return ReturnValueConst.CONTINUE;
    }


    private int _2800UpdateAccountRec(){
        accountRecord.setAcctCurrBal(new fDecimal(accountRecord.getAcctCurrBal(), 2).add(dalytranRecord.getDalytranAmt(), 2).bigDecimalValue());
        if (dalytranRecord.getDalytranAmt().compareTo(new BigDecimal(0)) >= 0){
            accountRecord.setAcctCurrCycCredit(new fDecimal(accountRecord.getAcctCurrCycCredit(), 2).add(dalytranRecord.getDalytranAmt(), 2).bigDecimalValue());
        } else {
            accountRecord.setAcctCurrCycDebit(new fDecimal(accountRecord.getAcctCurrCycDebit(), 2).add(dalytranRecord.getDalytranAmt(), 2).bigDecimalValue());
        }

        accountRecordRepository.save(accountRecord);
        return ReturnValueConst.CONTINUE;
    }


    private int _2900WriteTransactionFile(){
        applResult = 8;
        FdTranfileRec_Accessor.setFdTranfileRec(fdTranfileRec, TranRecord_Accessor.getTranRecord(tranRecord));

        tranRecordRepository.save(tranRecord);
        TranfileStatus_Accessor.setTranfileStatus(tranfileStatus, "00");
        if (StringUtil.compare(TranfileStatus_Accessor.getTranfileStatus(tranfileStatus), "00") == 0){
            applResult = 0;
        } else {
            applResult = 12;
        }
        if (ApplResult_Accessor.isApplAok(applResult)){
        } else {
            System.out.println("ERROR WRITING TO TRANSACTION FILE");
            IoStatus_Accessor.setIoStatus(ioStatus, TranfileStatus_Accessor.getTranfileStatus(tranfileStatus));
            if (_9910DisplayIoStatus() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
            if (_9999AbendProgram() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }
        return ReturnValueConst.CONTINUE;
    }

    private int zGetDb2FormatTimestamp(){
        cobolTs.setCobDd(String.valueOf(LocalDateTime.now().getDayOfMonth()));
        cobolTs.setCobMm(String.valueOf(LocalDateTime.now().getMonthValue()));
        cobolTs.setCobHh(String.valueOf(LocalDateTime.now().getHour()));
        cobolTs.setCobMin(String.valueOf(LocalDateTime.now().getMinute()));
        cobolTs.setCobSs(String.valueOf(LocalDateTime.now().getSecond()));
        cobolTs.setCobMil(String.valueOf(LocalDateTime.now().getNano() / 1000000));
        cobolTs.setCobYyyy(String.valueOf(LocalDateTime.now().getYear()));
        cobolTs.setCobRest(String.valueOf(LocalDateTime.now().getNano() % 1000000));

        db2FormatTs = Filler_Accessor.setDb2Yyyy(db2FormatTs, cobolTs.getCobYyyy());
        db2FormatTs = Filler_Accessor.setDb2Mm(db2FormatTs, cobolTs.getCobMm());
        db2FormatTs = Filler_Accessor.setDb2Dd(db2FormatTs, cobolTs.getCobDd());
        db2FormatTs = Filler_Accessor.setDb2Hh(db2FormatTs, cobolTs.getCobHh());
        db2FormatTs = Filler_Accessor.setDb2Min(db2FormatTs, cobolTs.getCobMin());
        db2FormatTs = Filler_Accessor.setDb2Ss(db2FormatTs, cobolTs.getCobSs());
        db2FormatTs = Filler_Accessor.setDb2Mil(db2FormatTs, Short.valueOf(cobolTs.getCobMil()));
        db2FormatTs = Filler_Accessor.setDb2Rest(db2FormatTs, "0000");
        db2FormatTs = Filler_Accessor.setDb2Streep1(db2FormatTs, "-");
        db2FormatTs = Filler_Accessor.setDb2Streep2(db2FormatTs, "-");
        db2FormatTs = Filler_Accessor.setDb2Streep3(db2FormatTs, "-");
        db2FormatTs = Filler_Accessor.setDb2Dot1(db2FormatTs, ".");
        db2FormatTs = Filler_Accessor.setDb2Dot2(db2FormatTs, ".");
        db2FormatTs = Filler_Accessor.setDb2Dot3(db2FormatTs, ".");
        return ReturnValueConst.CONTINUE;
    }

    private int _9300DalyrejsClose(){
        applResult = 8;
        DalyrejsStatus_Accessor.setDalyrejsStatus(dalyrejsStatus, dalyrejsFile.close());
        if (StringUtil.compare(DalyrejsStatus_Accessor.getDalyrejsStatus(dalyrejsStatus), "00") == 0){
            applResult = 0;
        } else {
            applResult = 12;
        }
        if (ApplResult_Accessor.isApplAok(applResult)){
        } else {
            System.out.println("ERROR CLOSING DAILY REJECTS FILE");
            IoStatus_Accessor.setIoStatus(ioStatus, XreffileStatus_Accessor.getXreffileStatus(xreffileStatus));
            if (_9910DisplayIoStatus() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
            if (_9999AbendProgram() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }
        return ReturnValueConst.CONTINUE;
    }

    private int _9910DisplayIoStatus(){
        if (!ConditionUtil.isNumeric(IoStatus_Accessor.getIoStatus(ioStatus)) || StringUtil.compare(ioStatus.getIoStat1(), "9") == 0){
            IoStatus04_Accessor.setIoStatus04(ioStatus04, ioStatus.getIoStat1() + IoStatus04_Accessor.getIoStatus04(ioStatus04).substring(1, 4));
            twoBytesBinary = (short)0;
            twoBytesBinary = TwoBytesAlpha_Accessor.setTwoBytesRight(twoBytesBinary, ioStatus.getIoStat2());
            ioStatus04.setIoStatus0403(twoBytesBinary);
            System.out.println("FILE STATUS IS: NNNN" + IoStatus04_Accessor.getIoStatus04(ioStatus04));
        } else {
            IoStatus04_Accessor.setIoStatus04(ioStatus04, "0000");
            IoStatus04_Accessor.setIoStatus04(ioStatus04, IoStatus04_Accessor.getIoStatus04(ioStatus04).substring(0, 2) + IoStatus_Accessor.getIoStatus(ioStatus));
            System.out.println("FILE STATUS IS: NNNN" + IoStatus04_Accessor.getIoStatus04(ioStatus04));
        }
        return ReturnValueConst.CONTINUE;
    }

    private int _9999AbendProgram(){
        System.out.println("ABENDING PROGRAM");
        timing = 0;
        abcode = 999;
//        cee3abd = new Cee3abd();
//        cee3abd.execute();
        return ReturnValueConst.CONTINUE;
    }
}
