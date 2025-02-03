
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COTRN00() {
    
    type formInput = {
        trnidin: string,
sel0001: string,
sel0002: string,
sel0003: string,
sel0004: string,
sel0005: string,
sel0006: string,
sel0007: string,
sel0008: string,
sel0009: string,
sel0010: string,

    }

    type formOutput = {
        cotrn00: string,
cotrn0a: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
pagenum: string,
trnid01: string,
tdate01: string,
tdesc01: string,
tamt001: string,
trnid02: string,
tdate02: string,
tdesc02: string,
tamt002: string,
trnid03: string,
tdate03: string,
tdesc03: string,
tamt003: string,
trnid04: string,
tdate04: string,
tdesc04: string,
tamt004: string,
trnid05: string,
tdate05: string,
tdesc05: string,
tamt005: string,
trnid06: string,
tdate06: string,
tdesc06: string,
tamt006: string,
trnid07: string,
tdate07: string,
tdesc07: string,
tamt007: string,
trnid08: string,
tdate08: string,
tdesc08: string,
tamt008: string,
trnid09: string,
tdate09: string,
tdesc09: string,
tamt009: string,
trnid10: string,
tdate10: string,
tdesc10: string,
tamt010: string,
errmsg: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
    {
        trnidin: '',
sel0001: '',
sel0002: '',
sel0003: '',
sel0004: '',
sel0005: '',
sel0006: '',
sel0007: '',
sel0008: '',
sel0009: '',
sel0010: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        cotrn00: '',
cotrn0a: '',
trnname: '',
title01: '',
curdate: 'mm/dd/yy',
pgmname: '',
title02: '',
curtime: 'hh:mm:ss',
pagenum: ' ',
trnid01: ' ',
tdate01: ' ',
tdesc01: ' ',
tamt001: ' ',
trnid02: ' ',
tdate02: ' ',
tdesc02: ' ',
tamt002: ' ',
trnid03: ' ',
tdate03: ' ',
tdesc03: ' ',
tamt003: ' ',
trnid04: ' ',
tdate04: ' ',
tdesc04: ' ',
tamt004: ' ',
trnid05: ' ',
tdate05: ' ',
tdesc05: ' ',
tamt005: ' ',
trnid06: ' ',
tdate06: ' ',
tdesc06: ' ',
tamt006: ' ',
trnid07: ' ',
tdate07: ' ',
tdesc07: ' ',
tamt007: ' ',
trnid08: ' ',
tdate08: ' ',
tdesc08: ' ',
tamt008: ' ',
trnid09: ' ',
tdate09: ' ',
tdesc09: ' ',
tamt009: ' ',
trnid10: ' ',
tdate10: ' ',
tdesc10: ' ',
tamt010: ' ',
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
        httpConfig.domain + '/Cotrn00',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COTRN00</title>
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
         List Transactions 
    </pre>
</GridItem>

    
<GridItem col={65} row={4}>
    <pre style={{color:"turquoise"}}>
         Page: 
    </pre>
</GridItem>

    
<GridItem col={71} row={4}>
    <pre style={{color:"#7faded"}}>
         {receivedData.pagenum } 
    </pre>
</GridItem>

    
<GridItem col={5} row={6}>
    <pre style={{color:"turquoise"}}>
         Search Tran ID: 
    </pre>
</GridItem>

    
<GridItem col={21} row={6}>
    <Input maxLength={16} className='bms underLine' name='trnidin' id='trnidin' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={38} row={6}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={2} row={8}>
    <pre style={{color:"neutral"}}>
         Sel 
    </pre>
</GridItem>

    
<GridItem col={8} row={8}>
    <pre style={{color:"neutral"}}>
          Transaction ID  
    </pre>
</GridItem>

    
<GridItem col={27} row={8}>
    <pre style={{color:"neutral"}}>
           Date   
    </pre>
</GridItem>

    
<GridItem col={38} row={8}>
    <pre style={{color:"neutral"}}>
              Description           
    </pre>
</GridItem>

    
<GridItem col={67} row={8}>
    <pre style={{color:"neutral"}}>
            Amount    
    </pre>
</GridItem>

    
<GridItem col={2} row={9}>
    <pre style={{color:"neutral"}}>
         --- 
    </pre>
</GridItem>

    
<GridItem col={8} row={9}>
    <pre style={{color:"neutral"}}>
         ---------------- 
    </pre>
</GridItem>

    
<GridItem col={27} row={9}>
    <pre style={{color:"neutral"}}>
         -------- 
    </pre>
</GridItem>

    
<GridItem col={38} row={9}>
    <pre style={{color:"neutral"}}>
         -------------------------- 
    </pre>
</GridItem>

    
<GridItem col={67} row={9}>
    <pre style={{color:"neutral"}}>
         ------------ 
    </pre>
</GridItem>

    
<GridItem col={3} row={10}>
    <Input maxLength={1} className='bms underLine' name='sel0001' id='sel0001' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={5} row={10}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={10}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnid01 } 
    </pre>
</GridItem>

    
<GridItem col={27} row={10}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdate01 } 
    </pre>
</GridItem>

    
<GridItem col={38} row={10}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdesc01 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={10}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tamt001 } 
    </pre>
</GridItem>

    
<GridItem col={3} row={11}>
    <Input maxLength={1} className='bms underLine' name='sel0002' id='sel0002' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={5} row={11}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={11}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnid02 } 
    </pre>
</GridItem>

    
<GridItem col={27} row={11}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdate02 } 
    </pre>
</GridItem>

    
<GridItem col={38} row={11}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdesc02 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={11}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tamt002 } 
    </pre>
</GridItem>

    
<GridItem col={3} row={12}>
    <Input maxLength={1} className='bms underLine' name='sel0003' id='sel0003' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={5} row={12}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={12}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnid03 } 
    </pre>
</GridItem>

    
<GridItem col={27} row={12}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdate03 } 
    </pre>
</GridItem>

    
<GridItem col={38} row={12}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdesc03 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={12}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tamt003 } 
    </pre>
</GridItem>

    
<GridItem col={3} row={13}>
    <Input maxLength={1} className='bms underLine' name='sel0004' id='sel0004' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={5} row={13}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={13}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnid04 } 
    </pre>
</GridItem>

    
<GridItem col={27} row={13}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdate04 } 
    </pre>
</GridItem>

    
<GridItem col={38} row={13}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdesc04 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={13}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tamt004 } 
    </pre>
</GridItem>

    
<GridItem col={3} row={14}>
    <Input maxLength={1} className='bms underLine' name='sel0005' id='sel0005' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={5} row={14}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={14}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnid05 } 
    </pre>
</GridItem>

    
<GridItem col={27} row={14}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdate05 } 
    </pre>
</GridItem>

    
<GridItem col={38} row={14}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdesc05 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={14}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tamt005 } 
    </pre>
</GridItem>

    
<GridItem col={3} row={15}>
    <Input maxLength={1} className='bms underLine' name='sel0006' id='sel0006' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={5} row={15}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={15}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnid06 } 
    </pre>
</GridItem>

    
<GridItem col={27} row={15}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdate06 } 
    </pre>
</GridItem>

    
<GridItem col={38} row={15}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdesc06 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={15}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tamt006 } 
    </pre>
</GridItem>

    
<GridItem col={3} row={16}>
    <Input maxLength={1} className='bms underLine' name='sel0007' id='sel0007' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={5} row={16}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={16}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnid07 } 
    </pre>
</GridItem>

    
<GridItem col={27} row={16}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdate07 } 
    </pre>
</GridItem>

    
<GridItem col={38} row={16}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdesc07 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={16}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tamt007 } 
    </pre>
</GridItem>

    
<GridItem col={3} row={17}>
    <Input maxLength={1} className='bms underLine' name='sel0008' id='sel0008' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={5} row={17}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={17}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnid08 } 
    </pre>
</GridItem>

    
<GridItem col={27} row={17}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdate08 } 
    </pre>
</GridItem>

    
<GridItem col={38} row={17}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdesc08 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={17}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tamt008 } 
    </pre>
</GridItem>

    
<GridItem col={3} row={18}>
    <Input maxLength={1} className='bms underLine' name='sel0009' id='sel0009' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={5} row={18}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={18}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnid09 } 
    </pre>
</GridItem>

    
<GridItem col={27} row={18}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdate09 } 
    </pre>
</GridItem>

    
<GridItem col={38} row={18}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdesc09 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={18}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tamt009 } 
    </pre>
</GridItem>

    
<GridItem col={3} row={19}>
    <Input maxLength={1} className='bms underLine' name='sel0010' id='sel0010' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={5} row={19}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={19}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnid10 } 
    </pre>
</GridItem>

    
<GridItem col={27} row={19}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdate10 } 
    </pre>
</GridItem>

    
<GridItem col={38} row={19}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tdesc10 } 
    </pre>
</GridItem>

    
<GridItem col={67} row={19}>
    <pre style={{color:"#7faded"}}>
         {receivedData.tamt010 } 
    </pre>
</GridItem>

    
<GridItem col={12} row={21}>
    <pre style={{color:"neutral"}}>
         Type  
    </pre>
</GridItem>

    
<GridItem col={1} row={23}>
    <pre style={{color:"red"}}>
         {receivedData.errmsg } 
    </pre>
</GridItem>

    
<GridItem col={1} row={24}>
    <pre style={{color:"yellow"}}>
         ENTER=Continue  F3=Back  F7=Backward  F8=Forward 
    </pre>
</GridItem>

    
    </>
  );
}
    