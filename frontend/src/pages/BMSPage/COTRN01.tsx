
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COTRN01() {
    
    type formInput = {
        trnidin: string,

    }

    type formOutput = {
        cotrn01: string,
cotrn1a: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
trnid: string,
cardnum: string,
ttypcd: string,
tcatcd: string,
trnsrc: string,
tdesc: string,
trnamt: string,
torigdt: string,
tprocdt: string,
mid: string,
mname: string,
mcity: string,
mzip: string,
errmsg: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
    {
        trnidin: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        cotrn01: '',
cotrn1a: '',
trnname: '',
title01: '',
curdate: 'mm/dd/yy',
pgmname: '',
title02: '',
curtime: 'hh:mm:ss',
trnid: ' ',
cardnum: ' ',
ttypcd: ' ',
tcatcd: ' ',
trnsrc: ' ',
tdesc: ' ',
trnamt: ' ',
torigdt: ' ',
tprocdt: ' ',
mid: ' ',
mname: ' ',
mcity: ' ',
mzip: ' ',
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
        httpConfig.domain + '/Cotrn01',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COTRN01</title>
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

    
<GridItem col={30} row={4}>
    <pre style={{color:"neutral"}}>
         View Transaction 
    </pre>
</GridItem>

    
<GridItem col={6} row={6}>
    <pre style={{color:"turquoise"}}>
         Enter Tran ID: 
    </pre>
</GridItem>

    
<GridItem col={21} row={6}>
    <Input maxLength={16} className='bms underLine' name='trnidin' id='trnidin' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={38} row={6}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={8}>
    <pre style={{color:"neutral"}}>
         ---------------------------------------------------------------------- 
    </pre>
</GridItem>

    
<GridItem col={6} row={10}>
    <pre style={{color:"turquoise"}}>
         Transaction ID: 
    </pre>
</GridItem>

    
<GridItem col={22} row={10}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnid } 
    </pre>
</GridItem>

    
<GridItem col={39} row={10}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={45} row={10}>
    <pre style={{color:"turquoise"}}>
         Card Number: 
    </pre>
</GridItem>

    
<GridItem col={58} row={10}>
    <pre style={{color:"#7faded"}}>
         {receivedData.cardnum } 
    </pre>
</GridItem>

    
<GridItem col={75} row={10}>
    <pre style={{color:"green"}}>
          
    </pre>
</GridItem>

    
<GridItem col={6} row={12}>
    <pre style={{color:"turquoise"}}>
         Type CD: 
    </pre>
</GridItem>

    
<GridItem col={15} row={12}>
    <pre style={{color:"#7faded"}}>
         {receivedData.ttypcd } 
    </pre>
</GridItem>

    
<GridItem col={18} row={12}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={23} row={12}>
    <pre style={{color:"turquoise"}}>
         Category CD: 
    </pre>
</GridItem>

    
<GridItem col={36} row={12}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tcatcd } 
    </pre>
</GridItem>

    
<GridItem col={41} row={12}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={46} row={12}>
    <pre style={{color:"turquoise"}}>
         Source: 
    </pre>
</GridItem>

    
<GridItem col={54} row={12}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnsrc } 
    </pre>
</GridItem>

    
<GridItem col={65} row={12}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={14}>
    <pre style={{color:"turquoise"}}>
         Description: 
    </pre>
</GridItem>

    
<GridItem col={19} row={14}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdesc } 
    </pre>
</GridItem>

    
<GridItem col={80} row={14}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={16}>
    <pre style={{color:"turquoise"}}>
         Amount: 
    </pre>
</GridItem>

    
<GridItem col={14} row={16}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnamt } 
    </pre>
</GridItem>

    
<GridItem col={27} row={16}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={31} row={16}>
    <pre style={{color:"turquoise"}}>
         Orig Date: 
    </pre>
</GridItem>

    
<GridItem col={42} row={16}>
    <pre style={{color:"#7faded"}}>
         {receivedData.torigdt } 
    </pre>
</GridItem>

    
<GridItem col={53} row={16}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={57} row={16}>
    <pre style={{color:"turquoise"}}>
         Proc Date: 
    </pre>
</GridItem>

    
<GridItem col={68} row={16}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tprocdt } 
    </pre>
</GridItem>

    
<GridItem col={79} row={16}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={18}>
    <pre style={{color:"turquoise"}}>
         Merchant ID: 
    </pre>
</GridItem>

    
<GridItem col={19} row={18}>
    <pre style={{color:"#7faded"}}>
         {receivedData.mid } 
    </pre>
</GridItem>

    
<GridItem col={29} row={18}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={33} row={18}>
    <pre style={{color:"turquoise"}}>
         Merchant Name: 
    </pre>
</GridItem>

    
<GridItem col={48} row={18}>
    <pre style={{color:"#7faded"}}>
         {receivedData.mname } 
    </pre>
</GridItem>

    
<GridItem col={79} row={18}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={20}>
    <pre style={{color:"turquoise"}}>
         Merchant City: 
    </pre>
</GridItem>

    
<GridItem col={21} row={20}>
    <pre style={{color:"#7faded"}}>
         {receivedData.mcity } 
    </pre>
</GridItem>

    
<GridItem col={47} row={20}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={53} row={20}>
    <pre style={{color:"turquoise"}}>
         Merchant Zip: 
    </pre>
</GridItem>

    
<GridItem col={67} row={20}>
    <pre style={{color:"#7faded"}}>
         {receivedData.mzip } 
    </pre>
</GridItem>

    
<GridItem col={78} row={20}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={1} row={23}>
    <pre style={{color:"red"}}>
         {receivedData.errmsg } 
    </pre>
</GridItem>

    
<GridItem col={1} row={24}>
    <pre style={{color:"yellow"}}>
         ENTER=Fetch  F3=Back  F4=Clear  F5=Browse Tran. 
    </pre>
</GridItem>

    
    </>
  );
}
    