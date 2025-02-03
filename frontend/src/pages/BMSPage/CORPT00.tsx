
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function CORPT00() {
    
    type formInput = {
        monthly: string,
yearly: string,
custom: string,
sdtmm: string,
sdtdd: string,
sdtyyyy: string,
edtmm: string,
edtdd: string,
edtyyyy: string,
confirm: string,

    }

    type formOutput = {
        corpt00: string,
corpt0a: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
errmsg: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
    {
        monthly: '',
yearly: '',
custom: '',
sdtmm: '',
sdtdd: '',
sdtyyyy: '',
edtmm: '',
edtdd: '',
edtyyyy: '',
confirm: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        corpt00: '',
corpt0a: '',
trnname: '',
title01: '',
curdate: 'mm/dd/yy',
pgmname: '',
title02: '',
curtime: 'hh:mm:ss',
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
        httpConfig.domain + '/Corpt00',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>CORPT00</title>
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
         Transaction Reports 
    </pre>
</GridItem>

    
<GridItem col={10} row={7}>
    <Input maxLength={1} className='bms underLine' name='monthly' id='monthly' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={12} row={7}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={15} row={7}>
    <pre style={{color:"turquoise"}}>
         Monthly (Current Month) 
    </pre>
</GridItem>

    
<GridItem col={10} row={9}>
    <Input maxLength={1} className='bms underLine' name='yearly' id='yearly' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={12} row={9}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={15} row={9}>
    <pre style={{color:"turquoise"}}>
         Yearly (Current Year) 
    </pre>
</GridItem>

    
<GridItem col={10} row={11}>
    <Input maxLength={1} className='bms underLine' name='custom' id='custom' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={12} row={11}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={15} row={11}>
    <pre style={{color:"turquoise"}}>
         Custom (Date Range) 
    </pre>
</GridItem>

    
<GridItem col={15} row={13}>
    <pre style={{color:"turquoise"}}>
         Start Date : 
    </pre>
</GridItem>

    
<GridItem col={29} row={13}>
    <Input maxLength={2} className='bms underLine' name='sdtmm' id='sdtmm' type='number' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={32} row={13}>
    <pre style={{color:"#7faded"}}>
         / 
    </pre>
</GridItem>

    
<GridItem col={34} row={13}>
    <Input maxLength={2} className='bms underLine' name='sdtdd' id='sdtdd' type='number' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={37} row={13}>
    <pre style={{color:"#7faded"}}>
         / 
    </pre>
</GridItem>

    
<GridItem col={39} row={13}>
    <Input maxLength={4} className='bms underLine' name='sdtyyyy' id='sdtyyyy' type='number' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={44} row={13}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={46} row={13}>
    <pre style={{color:"#7faded"}}>
         (MM/DD/YYYY) 
    </pre>
</GridItem>

    
<GridItem col={15} row={14}>
    <pre style={{color:"turquoise"}}>
           End Date : 
    </pre>
</GridItem>

    
<GridItem col={29} row={14}>
    <Input maxLength={2} className='bms underLine' name='edtmm' id='edtmm' type='number' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={32} row={14}>
    <pre style={{color:"#7faded"}}>
         / 
    </pre>
</GridItem>

    
<GridItem col={34} row={14}>
    <Input maxLength={2} className='bms underLine' name='edtdd' id='edtdd' type='number' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={37} row={14}>
    <pre style={{color:"#7faded"}}>
         / 
    </pre>
</GridItem>

    
<GridItem col={39} row={14}>
    <Input maxLength={4} className='bms underLine' name='edtyyyy' id='edtyyyy' type='number' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={44} row={14}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={46} row={14}>
    <pre style={{color:"#7faded"}}>
         (MM/DD/YYYY) 
    </pre>
</GridItem>

    
<GridItem col={6} row={19}>
    <pre style={{color:"turquoise"}}>
         The Report will be submitted for printing. Please confirm:  
    </pre>
</GridItem>

    
<GridItem col={66} row={19}>
    <Input maxLength={1} className='bms underLine' name='confirm' id='confirm' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={68} row={19}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={69} row={19}>
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
         ENTER=Continue  F3=Back 
    </pre>
</GridItem>

    
    </>
  );
}
    