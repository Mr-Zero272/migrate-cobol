package com.group_imposter.migrate.batch.cbact04c;

import com.group_imposter.migrate.accessor.*;
import com.group_imposter.migrate.constant.ReturnValueConst;
import com.group_imposter.migrate.constant.ValueConst;
import com.group_imposter.migrate.file.FileStatusConstant;
import com.group_imposter.migrate.model.*;
import com.group_imposter.migrate.repository.AccountRecordRepository;
import com.group_imposter.migrate.repository.CardXrefRecordRepository;
import com.group_imposter.migrate.repository.DisGroupRecordRepository;
import com.group_imposter.migrate.repository.TranRecordRepository;
import com.group_imposter.migrate.util.ConditionUtil;
import com.group_imposter.migrate.util.FieldFormat;
import com.group_imposter.migrate.util.StringUtil;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Component
public class CBACT04CProcessor implements ItemProcessor<TranCatBalRecord, TranRecord> {

    @Autowired
    private AccountRecordRepository accountRecordRepository;

    @Autowired
    private CardXrefRecordRepository cardXrefRecordRepository;

    @Autowired
    private DisGroupRecordRepository disGroupRecordRepository;

    private TranCatBalRecord tranCatBalRecord = new TranCatBalRecord();
    private AccountRecord accountRecord = new AccountRecord();
    private CardXrefRecord cardXrefRecord = new CardXrefRecord();
    private TranRecord tranRecord = new TranRecord();

    private FdAcctfileRec fdAcctfileRec = new FdAcctfileRec();
    private CBACT04CFdXreffileRec fdXreffileRec = new CBACT04CFdXreffileRec();
    private FdDiscgrpRec fdDiscgrpRec = new FdDiscgrpRec();
    private DisGroupRecord disGroupRecord = new DisGroupRecord();

    private CBACT04CWsCounters wsCounters = new CBACT04CWsCounters();
    private WsMiscVars wsMiscVars = new WsMiscVars();

    private CobolTs cobolTs = new CobolTs();
    private String db2FormatTs = FieldFormat.format(26, ValueConst.SPACE);

    private XreffileStatus xreffileStatus = new XreffileStatus();
    private AcctfileStatus acctfileStatus = new AcctfileStatus();
    private DiscgrpStatus discgrpStatus = new DiscgrpStatus();
    private TranfileStatus tranfileStatus = new TranfileStatus();

    private int applResult;
    private int abcode;
    private int timing;
    private IoStatus ioStatus = new IoStatus();
    private short twoBytesBinary;
    private IoStatus04 ioStatus04 = new IoStatus04();
    @Autowired
    private TranRecordRepository tranRecordRepository;


    @Override
    public TranRecord process(TranCatBalRecord item) throws Exception {
        this.tranCatBalRecord = item;
        wsCounters.setWsRecordCount(wsCounters.getWsRecordCount() + 1);
        System.out.println(item);
        if (item.getTranCatBalRecordId().getTrancatAcctId() != wsMiscVars.getWsLastAcctNum()) {
            if (wsMiscVars.getFirstTime() != 'Y') {
                if (_1050UpdateAccount() == ReturnValueConst.BACK){
                    // return empty object
                    return new TranRecord();
                }
            } else {
                wsMiscVars.setFirstTime('N');
            }
            wsMiscVars.setWsTotalInt(new BigDecimal(0));
            wsMiscVars.setWsLastAcctNum(item.getTranCatBalRecordId().getTrancatAcctId());
            fdAcctfileRec.setFdAcctId(item.getTranCatBalRecordId().getTrancatAcctId());
            if (_1100GetAcctData() == ReturnValueConst.BACK) {
                // return empty object
                return new TranRecord();
            }
            fdXreffileRec.setFdXrefAcctId(item.getTranCatBalRecordId().getTrancatAcctId());
            if (_1110GetXrefData() == ReturnValueConst.BACK) {
                // return empty object
                return new TranRecord();
            }
            fdDiscgrpRec.getFdDiscgrpKey().setDisAcctGroupId(accountRecord.getAcctGroupId());
            fdDiscgrpRec.getFdDiscgrpKey().setDisTranCatCd(tranCatBalRecord.getTranCatBalRecordId().getTrancatCd());
            fdDiscgrpRec.getFdDiscgrpKey().setDisTranTypeCd(tranCatBalRecord.getTranCatBalRecordId().getTrancatTypeCd());
            if (_1200GetTnterestRate() == ReturnValueConst.BACK) {
                // return empty object
                return new TranRecord();
            }
            if (disGroupRecord.getDisIntRate().compareTo(new BigDecimal(0)) != 0) {
                if (_1300ComputeInterest() == ReturnValueConst.BACK) {
                    // return empty object
                    return new TranRecord();
                }
                if (_1400ComoputeFees() == ReturnValueConst.BACK) {
                    // return empty object
                    return new TranRecord();
                }
            }
        }

        return tranRecord;
    }

    private int _1050UpdateAccount() {
        accountRecord.setAcctCurrBal(accountRecord.getAcctCurrBal().add(wsMiscVars.getWsTotalInt()));
        accountRecord.setAcctCurrCycDebit(new BigDecimal(0));
        accountRecord.setAcctCurrCycCredit(new BigDecimal(0));

        if (accountRecord.getAcctId() != 0) {
            accountRecordRepository.save(accountRecord);
        }

        AcctfileStatus_Accessor.setAcctfileStatus(acctfileStatus, "00");
        if (StringUtil.compare(AcctfileStatus_Accessor.getAcctfileStatus(acctfileStatus), "00") == 0){
            applResult = 0;
        } else {
            applResult = 12;
        }

        if (ApplResult_Accessor.isApplAok(applResult)){
            return ReturnValueConst.CONTINUE;
        } else {
            System.out.println("ERROR RE-WRITING ACCOUNT FILE");
            IoStatus_Accessor.setIoStatus(ioStatus, AcctfileStatus_Accessor.getAcctfileStatus(acctfileStatus));
            if (_9910DisplayIoStatus() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
            if (_9999AbendProgram() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }

        return ReturnValueConst.CONTINUE;
    }

    private int _1100GetAcctData() {
        AcctfileStatus_Accessor.setAcctfileStatus(acctfileStatus, FileStatusConstant.SUCCESS);
        accountRecord = accountRecordRepository.findById(fdAcctfileRec.getFdAcctId()).orElseGet(() -> {
            System.out.println("ACCOUNT NOT FOUND: " + fdAcctfileRec.getFdAcctId());
            AcctfileStatus_Accessor.setAcctfileStatus(acctfileStatus, FileStatusConstant.INVALID_KEY_READ);
            return new AccountRecord();
        });

        if (StringUtil.compare(AcctfileStatus_Accessor.getAcctfileStatus(acctfileStatus), "00") == 0){
            applResult = 0;
        } else {
            applResult = 12;
        }

        if (ApplResult_Accessor.isApplAok(applResult)){
            return ReturnValueConst.CONTINUE;
        } else {
            System.out.println("ERROR READING ACCOUNT FILE");
            IoStatus_Accessor.setIoStatus(ioStatus, AcctfileStatus_Accessor.getAcctfileStatus(acctfileStatus));
            if (_9910DisplayIoStatus() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
            if (_9999AbendProgram() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }

        return ReturnValueConst.CONTINUE;
    }

    private int _1110GetXrefData() {
        XreffileStatus_Accessor.setXreffileStatus(xreffileStatus, FileStatusConstant.SUCCESS);
        cardXrefRecord = cardXrefRecordRepository.findByXrefAcctId(fdXreffileRec.getFdXrefAcctId()).orElseGet(() -> {
            System.out.println("ACCOUNT NOT FOUND: " + fdXreffileRec.getFdXrefAcctId());
            XreffileStatus_Accessor.setXreffileStatus(xreffileStatus, FileStatusConstant.INVALID_KEY_READ);
            return new CardXrefRecord();
        });

        if (StringUtil.compare(XreffileStatus_Accessor.getXreffileStatus(xreffileStatus), "00") == 0){
            applResult = 0;
        } else {
            applResult = 12;
        }

        if (ApplResult_Accessor.isApplAok(applResult)){
            return ReturnValueConst.CONTINUE;
        } else {
            System.out.println("ERROR READING XREF FILE");
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

    private int _1200GetTnterestRate() {
        DiscgrpStatus_Accessor.setDiscgrpStatus(discgrpStatus, FileStatusConstant.SUCCESS);
        System.out.println("Find with id: " + fdDiscgrpRec.getFdDiscgrpKey());
        disGroupRecord = disGroupRecordRepository.findById(fdDiscgrpRec.getFdDiscgrpKey()).orElseGet(() -> {
            DiscgrpStatus_Accessor.setDiscgrpStatus(discgrpStatus, FileStatusConstant.INVALID_KEY_READ);
            System.out.println("DISCLOSURE GROUP RECORD MISSING");
            System.out.println("TRY WITH DEFAULT GROUP CODE");
            return new DisGroupRecord();
        });

        if (StringUtil.compare(DiscgrpStatus_Accessor.getDiscgrpStatus(discgrpStatus), "00") == 0 ||
                StringUtil.compare(DiscgrpStatus_Accessor.getDiscgrpStatus(discgrpStatus), "23") == 0){
            applResult = 0;
        } else {
            applResult = 12;
        }

        if (ApplResult_Accessor.isApplAok(applResult)){
            return ReturnValueConst.CONTINUE;
        } else {
            System.out.println("ERROR READING DISCLOSURE GROUP FILE");
            IoStatus_Accessor.setIoStatus(ioStatus, DiscgrpStatus_Accessor.getDiscgrpStatus(discgrpStatus));
            if (_9910DisplayIoStatus() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
            if (_9999AbendProgram() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }

        if (StringUtil.compare(DiscgrpStatus_Accessor.getDiscgrpStatus(discgrpStatus), "23") == 0) {
            fdDiscgrpRec.getFdDiscgrpKey().setDisAcctGroupId("DEFAULT");
            if (_1200AGetDefaultIntRate() == ReturnValueConst.BACK) {
                return ReturnValueConst.BACK;
            };
        }
        return ReturnValueConst.CONTINUE;
    }

    private int _1200AGetDefaultIntRate() {
        DiscgrpStatus_Accessor.setDiscgrpStatus(discgrpStatus, FileStatusConstant.SUCCESS);
        disGroupRecord = disGroupRecordRepository.findById(fdDiscgrpRec.getFdDiscgrpKey()).orElseGet(() -> {
            DiscgrpStatus_Accessor.setDiscgrpStatus(discgrpStatus, FileStatusConstant.INVALID_KEY_READ);
            return new DisGroupRecord();
        });

        if (StringUtil.compare(DiscgrpStatus_Accessor.getDiscgrpStatus(discgrpStatus), "00") == 0){
            applResult = 0;
        } else {
            applResult = 12;
        }

        if (ApplResult_Accessor.isApplAok(applResult)){
            return ReturnValueConst.CONTINUE;
        } else {
            System.out.println("ERROR READING DEFAULT DISCLOSURE GROUP");
            IoStatus_Accessor.setIoStatus(ioStatus, DiscgrpStatus_Accessor.getDiscgrpStatus(discgrpStatus));
            if (_9910DisplayIoStatus() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
            if (_9999AbendProgram() == ReturnValueConst.BACK){
                return ReturnValueConst.BACK;
            }
        }

        return ReturnValueConst.CONTINUE;
    }

    private int _1300ComputeInterest() {
        System.out.println(tranCatBalRecord.getTranCatBal());
        System.out.println(disGroupRecord.getDisIntRate());
        wsMiscVars.setWsMonthlyInt(tranCatBalRecord.getTranCatBal().multiply(disGroupRecord.getDisIntRate()).divide(new BigDecimal(1200)));
        wsMiscVars.setWsTotalInt(wsMiscVars.getWsMonthlyInt());

        if(_1300BWriteTx() == ReturnValueConst.BACK) {
            return ReturnValueConst.BACK;
        }

        return ReturnValueConst.CONTINUE;
    }

    private int _1300BWriteTx() {
        wsCounters.setWsTranIdSuffix(wsCounters.getWsTranIdSuffix() + 1);
        //TODO add params for this program here (PARM-DATE)
        String pattern = "yyyy-dd-MM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        tranRecord.setTranId(simpleDateFormat.format(new Date()) + wsCounters.getWsTranIdSuffix());
        tranRecord.setTranTypeCd("01");
        tranRecord.setTranCatCd((short)5);
        tranRecord.setTranSource("System");
        tranRecord.setTranDesc("Int. for a/c " + accountRecord.getAcctId());
        tranRecord.setTranAmt(wsMiscVars.getWsMonthlyInt());
        tranRecord.setTranMerchantId(0);
        tranRecord.setTranMerchantName(ValueConst.SPACE);
        tranRecord.setTranMerchantCity(ValueConst.SPACE);
        tranRecord.setTranMerchantZip(ValueConst.SPACE);
        tranRecord.setTranCardNum(cardXrefRecord.getXrefCardNum());
        if (zGetDb2FormatTimestamp() == ReturnValueConst.BACK) {
            return ReturnValueConst.BACK;
        };
        tranRecord.setTranOrigTs(db2FormatTs);
        tranRecord.setTranProcTs(db2FormatTs);

        TranfileStatus_Accessor.setTranfileStatus(tranfileStatus, FileStatusConstant.SUCCESS);
        tranRecordRepository.save(tranRecord);

        if (ApplResult_Accessor.isApplAok(applResult)){
            return ReturnValueConst.CONTINUE;
        } else {
            System.out.println("ERROR WRITING TRANSACTION RECORD");
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

    private int _1400ComoputeFees() {
        // TODO implement later due to no code cobol implemented
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

    private int _9999AbendProgram(){
        System.out.println("ABENDING PROGRAM");
        timing = 0;
        abcode = 999;
//        cee3abd = new Cee3abd();
//        cee3abd.execute();
        return ReturnValueConst.CONTINUE;
    }
}
