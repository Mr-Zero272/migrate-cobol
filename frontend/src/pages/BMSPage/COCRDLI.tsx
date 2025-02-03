
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COCRDLI() {
    
    type formInput = {
        acctsid: string,
cardsid: string,

    }

    type formOutput = {
        cocrdli: string,
ccrdlia: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
pageno: string,
crdsel1: string,
acctno1: string,
crdnum1: string,
crdsts1: string,
crdsel2: string,
crdstp2: string,
acctno2: string,
crdnum2: string,
crdsts2: string,
crdsel3: string,
crdstp3: string,
acctno3: string,
crdnum3: string,
crdsts3: string,
crdsel4: string,
crdstp4: string,
acctno4: string,
crdnum4: string,
crdsts4: string,
crdsel5: string,
crdstp5: string,
acctno5: string,
crdnum5: string,
crdsts5: string,
crdsel6: string,
crdstp6: string,
acctno6: string,
crdnum6: string,
crdsts6: string,
crdsel7: string,
crdstp7: string,
acctno7: string,
crdnum7: string,
crdsts7: string,
infomsg: string,
errmsg: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
    {
        acctsid: '',
cardsid: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        cocrdli: '',
ccrdlia: '',
trnname: '',
title01: '',
curdate: 'mm/dd/yy',
pgmname: '',
title02: '',
curtime: 'hh:mm:ss',
pageno: '',
crdsel1: '',
acctno1: '',
crdnum1: '',
crdsts1: '',
crdsel2: '',
crdstp2: '',
acctno2: '',
crdnum2: '',
crdsts2: '',
crdsel3: '',
crdstp3: '',
acctno3: '',
crdnum3: '',
crdsts3: '',
crdsel4: '',
crdstp4: '',
acctno4: '',
crdnum4: '',
crdsts4: '',
crdsel5: '',
crdstp5: '',
acctno5: '',
crdnum5: '',
crdsts5: '',
crdsel6: '',
crdstp6: '',
acctno6: '',
crdnum6: '',
crdsts6: '',
crdsel7: '',
crdstp7: '',
acctno7: '',
crdnum7: '',
crdsts7: '',
infomsg: '',
errmsg: '',

    });

    const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    setFormData((state) => {
        return {
        ...state,
        [event.target.name]: event.target.value,
        };
    });
    };

    const handleSubmit = async (event: KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') {
        for (const key in formData) {
        if (!formData[key]) {
            return;
        }
        }

        const response = await axios.post(
        httpConfig.domain + '/Cocrdli',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COCRDLI</title>
    </Helmet>
    
<GridItem col={1} row={1}>
    <pre style={{color:"#7faded"}}>
         Tran: 
    </pre>
</GridItem>

    
<GridItem col={7} row={1}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnname } 
    </pre>
</GridItem>

    
<GridItem col={21} row={1}>
    <pre style={{color:"yellow"}}>
         {receivedData.title01 } 
    </pre>
</GridItem>

    
<GridItem col={65} row={1}>
    <pre style={{color:"#7faded"}}>
         Date: 
    </pre>
</GridItem>

    
<GridItem col={71} row={1}>
    <pre style={{color:"#7faded"}}>
         {receivedData.curdate } 
    </pre>
</GridItem>

    
<GridItem col={1} row={2}>
    <pre style={{color:"#7faded"}}>
         Prog: 
    </pre>
</GridItem>

    
<GridItem col={7} row={2}>
    <pre style={{color:"#7faded"}}>
         {receivedData.pgmname } 
    </pre>
</GridItem>

    
<GridItem col={21} row={2}>
    <pre style={{color:"yellow"}}>
         {receivedData.title02 } 
    </pre>
</GridItem>

    
<GridItem col={65} row={2}>
    <pre style={{color:"#7faded"}}>
         Time: 
    </pre>
</GridItem>

    
<GridItem col={71} row={2}>
    <pre style={{color:"#7faded"}}>
         {receivedData.curtime } 
    </pre>
</GridItem>

    
<GridItem col={31} row={4}>
    <pre style={{color:"neutral"}}>
         List Credit Cards 
    </pre>
</GridItem>

    
<GridItem col={70} row={4}>
    <pre >
         Page  
    </pre>
</GridItem>

    
<GridItem col={76} row={4}>
    <pre >
         {receivedData.pageno } 
    </pre>
</GridItem>

    
<GridItem col={22} row={6}>
    <pre style={{color:"turquoise"}}>
         Account Number    : 
    </pre>
</GridItem>

    
<GridItem col={44} row={6}>
    <Input maxLength={11} className='bms underLine' name='acctsid' id='acctsid' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={56} row={6}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={22} row={7}>
    <pre style={{color:"turquoise"}}>
         Credit Card Number: 
    </pre>
</GridItem>

    
<GridItem col={44} row={7}>
    <Input maxLength={16} className='bms underLine' name='cardsid' id='cardsid' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={61} row={7}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={10} row={9}>
    <pre style={{color:"neutral"}}>
         Select     
    </pre>
</GridItem>

    
<GridItem col={21} row={9}>
    <pre style={{color:"neutral"}}>
         Account Number 
    </pre>
</GridItem>

    
<GridItem col={45} row={9}>
    <pre style={{color:"neutral"}}>
          Card Number  
    </pre>
</GridItem>

    
<GridItem col={66} row={9}>
    <pre style={{color:"neutral"}}>
         Active  
    </pre>
</GridItem>

    
<GridItem col={10} row={10}>
    <pre style={{color:"neutral"}}>
         ------ 
    </pre>
</GridItem>

    
<GridItem col={20} row={10}>
    <pre style={{color:"neutral"}}>
         --------------- 
    </pre>
</GridItem>

    
<GridItem col={43} row={10}>
    <pre style={{color:"neutral"}}>
         --------------- 
    </pre>
</GridItem>

    
<GridItem col={65} row={10}>
    <pre style={{color:"neutral"}}>
         -------- 
    </pre>
</GridItem>

    
<GridItem col={12} row={11}>
    <pre style={{color:"default"}}>
         {receivedData.crdsel1 } 
    </pre>
</GridItem>

    
<GridItem col={14} row={11}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={22} row={11}>
    <pre style={{color:"default"}}>
         {receivedData.acctno1 } 
    </pre>
</GridItem>

    
<GridItem col={43} row={11}>
    <pre style={{color:"default"}}>
         {receivedData.crdnum1 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={11}>
    <pre style={{color:"default"}}>
         {receivedData.crdsts1 } 
    </pre>
</GridItem>

    
<GridItem col={12} row={12}>
    <pre style={{color:"default"}}>
         {receivedData.crdsel2 } 
    </pre>
</GridItem>

    
<GridItem col={14} row={12}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={14} row={12}>
    <pre style={{color:"default"}}>
         {receivedData.crdstp2 } 
    </pre>
</GridItem>

    
<GridItem col={22} row={12}>
    <pre style={{color:"default"}}>
         {receivedData.acctno2 } 
    </pre>
</GridItem>

    
<GridItem col={43} row={12}>
    <pre style={{color:"default"}}>
         {receivedData.crdnum2 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={12}>
    <pre style={{color:"default"}}>
         {receivedData.crdsts2 } 
    </pre>
</GridItem>

    
<GridItem col={12} row={13}>
    <pre style={{color:"default"}}>
         {receivedData.crdsel3 } 
    </pre>
</GridItem>

    
<GridItem col={14} row={13}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={14} row={13}>
    <pre style={{color:"default"}}>
         {receivedData.crdstp3 } 
    </pre>
</GridItem>

    
<GridItem col={22} row={13}>
    <pre style={{color:"default"}}>
         {receivedData.acctno3 } 
    </pre>
</GridItem>

    
<GridItem col={43} row={13}>
    <pre style={{color:"default"}}>
         {receivedData.crdnum3 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={13}>
    <pre style={{color:"default"}}>
         {receivedData.crdsts3 } 
    </pre>
</GridItem>

    
<GridItem col={12} row={14}>
    <pre style={{color:"default"}}>
         {receivedData.crdsel4 } 
    </pre>
</GridItem>

    
<GridItem col={14} row={14}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={14} row={14}>
    <pre style={{color:"default"}}>
         {receivedData.crdstp4 } 
    </pre>
</GridItem>

    
<GridItem col={22} row={14}>
    <pre style={{color:"default"}}>
         {receivedData.acctno4 } 
    </pre>
</GridItem>

    
<GridItem col={43} row={14}>
    <pre style={{color:"default"}}>
         {receivedData.crdnum4 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={14}>
    <pre style={{color:"default"}}>
         {receivedData.crdsts4 } 
    </pre>
</GridItem>

    
<GridItem col={12} row={15}>
    <pre style={{color:"default"}}>
         {receivedData.crdsel5 } 
    </pre>
</GridItem>

    
<GridItem col={14} row={15}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={14} row={15}>
    <pre style={{color:"default"}}>
         {receivedData.crdstp5 } 
    </pre>
</GridItem>

    
<GridItem col={22} row={15}>
    <pre style={{color:"default"}}>
         {receivedData.acctno5 } 
    </pre>
</GridItem>

    
<GridItem col={43} row={15}>
    <pre style={{color:"default"}}>
         {receivedData.crdnum5 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={15}>
    <pre style={{color:"default"}}>
         {receivedData.crdsts5 } 
    </pre>
</GridItem>

    
<GridItem col={12} row={16}>
    <pre style={{color:"default"}}>
         {receivedData.crdsel6 } 
    </pre>
</GridItem>

    
<GridItem col={14} row={16}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={14} row={16}>
    <pre style={{color:"default"}}>
         {receivedData.crdstp6 } 
    </pre>
</GridItem>

    
<GridItem col={22} row={16}>
    <pre style={{color:"default"}}>
         {receivedData.acctno6 } 
    </pre>
</GridItem>

    
<GridItem col={43} row={16}>
    <pre style={{color:"default"}}>
         {receivedData.crdnum6 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={16}>
    <pre style={{color:"default"}}>
         {receivedData.crdsts6 } 
    </pre>
</GridItem>

    
<GridItem col={12} row={17}>
    <pre style={{color:"default"}}>
         {receivedData.crdsel7 } 
    </pre>
</GridItem>

    
<GridItem col={14} row={17}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={14} row={17}>
    <pre style={{color:"default"}}>
         {receivedData.crdstp7 } 
    </pre>
</GridItem>

    
<GridItem col={22} row={17}>
    <pre style={{color:"default"}}>
         {receivedData.acctno7 } 
    </pre>
</GridItem>

    
<GridItem col={43} row={17}>
    <pre style={{color:"default"}}>
         {receivedData.crdnum7 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={17}>
    <pre style={{color:"default"}}>
         {receivedData.crdsts7 } 
    </pre>
</GridItem>

    
<GridItem col={19} row={20}>
    <pre style={{color:"neutral"}}>
         {receivedData.infomsg } 
    </pre>
</GridItem>

    
<GridItem col={65} row={20}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={1} row={23}>
    <pre style={{color:"red"}}>
         {receivedData.errmsg } 
    </pre>
</GridItem>

    
<GridItem col={1} row={24}>
    <pre style={{color:"turquoise"}}>
           F3=Exit F7=Backward  F8=Forward 
    </pre>
</GridItem>

    
    </>
  );
}
    