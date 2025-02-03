
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COBIL00() {
    
    type formInput = {
        actidin: string,
confirm: string,

    }

    type formOutput = {
        cobil00: string,
cobil0a: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
curbal: string,
errmsg: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
    {
        actidin: '',
confirm: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        cobil00: '',
cobil0a: '',
trnname: '',
title01: '',
curdate: 'mm/dd/yy',
pgmname: '',
title02: '',
curtime: 'hh:mm:ss',
curbal: '',
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
        httpConfig.domain + '/Cobil00',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COBIL00</title>
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

    
<GridItem col={35} row={4}>
    <pre style={{color:"neutral"}}>
         Bill Payment 
    </pre>
</GridItem>

    
<GridItem col={6} row={6}>
    <pre style={{color:"green"}}>
         Enter Acct ID: 
    </pre>
</GridItem>

    
<GridItem col={21} row={6}>
    <Input maxLength={11} className='bms underLine' name='actidin' id='actidin' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={33} row={6}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={8}>
    <pre style={{color:"yellow"}}>
         ---------------------------------------------------------------------- 
    </pre>
</GridItem>

    
<GridItem col={6} row={11}>
    <pre style={{color:"turquoise"}}>
         Your current balance is:  
    </pre>
</GridItem>

    
<GridItem col={32} row={11}>
    <pre style={{color:"#7faded"}}>
         {receivedData.curbal } 
    </pre>
</GridItem>

    
<GridItem col={47} row={11}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={15}>
    <pre style={{color:"turquoise"}}>
         Do you want to pay your balance now. Please confirm:  
    </pre>
</GridItem>

    
<GridItem col={60} row={15}>
    <Input maxLength={1} className='bms underLine' name='confirm' id='confirm' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={62} row={15}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={63} row={15}>
    <pre style={{color:"neutral"}}>
         (Y/N) 
    </pre>
</GridItem>

    
<GridItem col={1} row={23}>
    <pre style={{color:"red"}}>
         {receivedData.errmsg } 
    </pre>
</GridItem>

    
<GridItem col={1} row={24}>
    <pre style={{color:"yellow"}}>
         ENTER=Continue  F3=Back  F4=Clear 
    </pre>
</GridItem>

    
    </>
  );
}
    