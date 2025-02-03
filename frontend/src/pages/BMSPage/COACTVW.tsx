
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COACTVW() {
    
    type formInput = {
        acctsid: string,

    }

    type formOutput = {
        coactvw: string,
cactvwa: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
acsttus: string,
adtopen: string,
acrdlim: string,
aexpdt: string,
acshlim: string,
areisdt: string,
acurbal: string,
acrcycr: string,
aaddgrp: string,
acrcydb: string,
acstnum: string,
acstssn: string,
acstdob: string,
acstfco: string,
acsfnam: string,
acsmnam: string,
acslnam: string,
acsadl1: string,
acsstte: string,
acsadl2: string,
acszipc: string,
acscity: string,
acsctry: string,
acsphn1: string,
acsgovt: string,
acsphn2: string,
acseftc: string,
acspflg: string,
infomsg: string,
errmsg: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
    {
        acctsid: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        coactvw: '',
cactvwa: '',
trnname: '',
title01: '',
curdate: 'mm/dd/yy',
pgmname: '',
title02: '',
curtime: 'hh:mm:ss',
acsttus: '',
adtopen: '',
acrdlim: '',
aexpdt: '',
acshlim: '',
areisdt: '',
acurbal: '',
acrcycr: '',
aaddgrp: '',
acrcydb: '',
acstnum: '',
acstssn: '',
acstdob: '',
acstfco: '',
acsfnam: '',
acsmnam: '',
acslnam: '',
acsadl1: '',
acsstte: '',
acsadl2: '',
acszipc: '',
acscity: '',
acsctry: '',
acsphn1: '',
acsgovt: '',
acsphn2: '',
acseftc: '',
acspflg: '',
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
        httpConfig.domain + '/Coactvw',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COACTVW</title>
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

    
<GridItem col={33} row={4}>
    <pre style={{color:"neutral"}}>
         View Account 
    </pre>
</GridItem>

    
<GridItem col={19} row={5}>
    <pre style={{color:"turquoise"}}>
         Account Number : 
    </pre>
</GridItem>

    
<GridItem col={38} row={5}>
    <Input maxLength={11} className='bms underLine' name='acctsid' id='acctsid' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={50} row={5}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={57} row={5}>
    <pre style={{color:"turquoise"}}>
         Active Y/N:  
    </pre>
</GridItem>

    
<GridItem col={70} row={5}>
    <pre >
         {receivedData.acsttus } 
    </pre>
</GridItem>

    
<GridItem col={72} row={5}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={6}>
    <pre style={{color:"turquoise"}}>
         Opened: 
    </pre>
</GridItem>

    
<GridItem col={17} row={6}>
    <pre >
         {receivedData.adtopen } 
    </pre>
</GridItem>

    
<GridItem col={28} row={6}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={39} row={6}>
    <pre style={{color:"turquoise"}}>
         Credit Limit        : 
    </pre>
</GridItem>

    
<GridItem col={61} row={6}>
    <pre >
         {receivedData.acrdlim } 
    </pre>
</GridItem>

    
<GridItem col={77} row={6}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={7}>
    <pre style={{color:"turquoise"}}>
         Expiry: 
    </pre>
</GridItem>

    
<GridItem col={17} row={7}>
    <pre >
         {receivedData.aexpdt } 
    </pre>
</GridItem>

    
<GridItem col={28} row={7}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={39} row={7}>
    <pre style={{color:"turquoise"}}>
         Cash credit Limit   : 
    </pre>
</GridItem>

    
<GridItem col={61} row={7}>
    <pre >
         {receivedData.acshlim } 
    </pre>
</GridItem>

    
<GridItem col={77} row={7}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={8}>
    <pre style={{color:"turquoise"}}>
         Reissue: 
    </pre>
</GridItem>

    
<GridItem col={17} row={8}>
    <pre >
         {receivedData.areisdt } 
    </pre>
</GridItem>

    
<GridItem col={28} row={8}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={39} row={8}>
    <pre style={{color:"turquoise"}}>
         Current Balance     : 
    </pre>
</GridItem>

    
<GridItem col={61} row={8}>
    <pre >
         {receivedData.acurbal } 
    </pre>
</GridItem>

    
<GridItem col={77} row={8}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={39} row={9}>
    <pre style={{color:"turquoise"}}>
         Current Cycle Credit: 
    </pre>
</GridItem>

    
<GridItem col={61} row={9}>
    <pre >
         {receivedData.acrcycr } 
    </pre>
</GridItem>

    
<GridItem col={77} row={9}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={10}>
    <pre style={{color:"turquoise"}}>
         Account Group: 
    </pre>
</GridItem>

    
<GridItem col={23} row={10}>
    <pre >
         {receivedData.aaddgrp } 
    </pre>
</GridItem>

    
<GridItem col={34} row={10}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={39} row={10}>
    <pre style={{color:"turquoise"}}>
         Current Cycle Debit : 
    </pre>
</GridItem>

    
<GridItem col={61} row={10}>
    <pre >
         {receivedData.acrcydb } 
    </pre>
</GridItem>

    
<GridItem col={77} row={10}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={32} row={11}>
    <pre style={{color:"neutral"}}>
         Customer Details 
    </pre>
</GridItem>

    
<GridItem col={8} row={12}>
    <pre style={{color:"turquoise"}}>
         Customer id  : 
    </pre>
</GridItem>

    
<GridItem col={23} row={12}>
    <pre >
         {receivedData.acstnum } 
    </pre>
</GridItem>

    
<GridItem col={33} row={12}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={49} row={12}>
    <pre style={{color:"turquoise"}}>
         SSN: 
    </pre>
</GridItem>

    
<GridItem col={54} row={12}>
    <pre >
         {receivedData.acstssn } 
    </pre>
</GridItem>

    
<GridItem col={67} row={12}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={13}>
    <pre style={{color:"turquoise"}}>
         Date of birth: 
    </pre>
</GridItem>

    
<GridItem col={23} row={13}>
    <pre >
         {receivedData.acstdob } 
    </pre>
</GridItem>

    
<GridItem col={34} row={13}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={49} row={13}>
    <pre style={{color:"turquoise"}}>
         FICO Score: 
    </pre>
</GridItem>

    
<GridItem col={61} row={13}>
    <pre >
         {receivedData.acstfco } 
    </pre>
</GridItem>

    
<GridItem col={65} row={13}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={1} row={14}>
    <pre style={{color:"turquoise"}}>
         First Name 
    </pre>
</GridItem>

    
<GridItem col={28} row={14}>
    <pre style={{color:"turquoise"}}>
         Middle Name:  
    </pre>
</GridItem>

    
<GridItem col={55} row={14}>
    <pre style={{color:"turquoise"}}>
         Last Name :  
    </pre>
</GridItem>

    
<GridItem col={1} row={15}>
    <pre >
         {receivedData.acsfnam } 
    </pre>
</GridItem>

    
<GridItem col={27} row={15}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={28} row={15}>
    <pre >
         {receivedData.acsmnam } 
    </pre>
</GridItem>

    
<GridItem col={54} row={15}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={55} row={15}>
    <pre >
         {receivedData.acslnam } 
    </pre>
</GridItem>

    
<GridItem col={1} row={16}>
    <pre style={{color:"turquoise"}}>
         Address: 
    </pre>
</GridItem>

    
<GridItem col={10} row={16}>
    <pre >
         {receivedData.acsadl1 } 
    </pre>
</GridItem>

    
<GridItem col={61} row={16}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={63} row={16}>
    <pre style={{color:"turquoise"}}>
         State  
    </pre>
</GridItem>

    
<GridItem col={73} row={16}>
    <pre >
         {receivedData.acsstte } 
    </pre>
</GridItem>

    
<GridItem col={76} row={16}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={10} row={17}>
    <pre >
         {receivedData.acsadl2 } 
    </pre>
</GridItem>

    
<GridItem col={61} row={17}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={63} row={17}>
    <pre style={{color:"turquoise"}}>
         Zip 
    </pre>
</GridItem>

    
<GridItem col={73} row={17}>
    <pre >
         {receivedData.acszipc } 
    </pre>
</GridItem>

    
<GridItem col={79} row={17}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={1} row={18}>
    <pre style={{color:"turquoise"}}>
         City  
    </pre>
</GridItem>

    
<GridItem col={10} row={18}>
    <pre >
         {receivedData.acscity } 
    </pre>
</GridItem>

    
<GridItem col={61} row={18}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={63} row={18}>
    <pre style={{color:"turquoise"}}>
         Country 
    </pre>
</GridItem>

    
<GridItem col={73} row={18}>
    <pre >
         {receivedData.acsctry } 
    </pre>
</GridItem>

    
<GridItem col={77} row={18}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={1} row={19}>
    <pre style={{color:"turquoise"}}>
         Phone 1: 
    </pre>
</GridItem>

    
<GridItem col={10} row={19}>
    <pre >
         {receivedData.acsphn1 } 
    </pre>
</GridItem>

    
<GridItem col={24} row={19}>
    <pre style={{color:"turquoise"}}>
         Government Issued Id Ref    :  
    </pre>
</GridItem>

    
<GridItem col={58} row={19}>
    <pre >
         {receivedData.acsgovt } 
    </pre>
</GridItem>

    
<GridItem col={79} row={19}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={1} row={20}>
    <pre style={{color:"turquoise"}}>
         Phone 2: 
    </pre>
</GridItem>

    
<GridItem col={10} row={20}>
    <pre >
         {receivedData.acsphn2 } 
    </pre>
</GridItem>

    
<GridItem col={24} row={20}>
    <pre style={{color:"turquoise"}}>
         EFT Account Id:  
    </pre>
</GridItem>

    
<GridItem col={41} row={20}>
    <pre >
         {receivedData.acseftc } 
    </pre>
</GridItem>

    
<GridItem col={52} row={20}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={53} row={20}>
    <pre style={{color:"turquoise"}}>
         Primary Card Holder Y/N: 
    </pre>
</GridItem>

    
<GridItem col={78} row={20}>
    <pre >
         {receivedData.acspflg } 
    </pre>
</GridItem>

    
<GridItem col={80} row={20}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={23} row={22}>
    <pre style={{color:"neutral"}}>
         {receivedData.infomsg } 
    </pre>
</GridItem>

    
<GridItem col={69} row={22}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={1} row={1}>
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
           F3=Exit  
    </pre>
</GridItem>

    
    </>
  );
}
    