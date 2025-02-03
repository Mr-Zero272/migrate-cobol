
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COTRN02() {
    
    type formInput = {
        actidin: string,
cardnin: string,
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
confirm: string,

    }

    type formOutput = {
        cotrn02: string,
cotrn2a: string,
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
        actidin: '',
cardnin: '',
ttypcd: '',
tcatcd: '',
trnsrc: '',
tdesc: '',
trnamt: '',
torigdt: '',
tprocdt: '',
mid: '',
mname: '',
mcity: '',
mzip: '',
confirm: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        cotrn02: '',
cotrn2a: '',
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
        httpConfig.domain + '/Cotrn02',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COTRN02</title>
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
         Add Transaction 
    </pre>
</GridItem>

    
<GridItem col={6} row={6}>
    <pre style={{color:"turquoise"}}>
         Enter Acct #: 
    </pre>
</GridItem>

    
<GridItem col={21} row={6}>
    <Input maxLength={11} className='bms underLine' name='actidin' id='actidin' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={33} row={6}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={37} row={6}>
    <pre style={{color:"neutral"}}>
         (or) 
    </pre>
</GridItem>

    
<GridItem col={46} row={6}>
    <pre style={{color:"turquoise"}}>
         Card #: 
    </pre>
</GridItem>

    
<GridItem col={55} row={6}>
    <Input maxLength={16} className='bms underLine' name='cardnin' id='cardnin' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={72} row={6}>
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
         Type CD: 
    </pre>
</GridItem>

    
<GridItem col={15} row={10}>
    <Input maxLength={2} className='bms underLine' name='ttypcd' id='ttypcd' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={18} row={10}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={23} row={10}>
    <pre style={{color:"turquoise"}}>
         Category CD: 
    </pre>
</GridItem>

    
<GridItem col={36} row={10}>
    <Input maxLength={4} className='bms underLine' name='tcatcd' id='tcatcd' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={41} row={10}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={46} row={10}>
    <pre style={{color:"turquoise"}}>
         Source: 
    </pre>
</GridItem>

    
<GridItem col={54} row={10}>
    <Input maxLength={10} className='bms underLine' name='trnsrc' id='trnsrc' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={65} row={10}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={12}>
    <pre style={{color:"turquoise"}}>
         Description: 
    </pre>
</GridItem>

    
<GridItem col={19} row={12}>
    <Input maxLength={60} className='bms underLine' name='tdesc' id='tdesc' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={80} row={12}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={14}>
    <pre style={{color:"turquoise"}}>
         Amount: 
    </pre>
</GridItem>

    
<GridItem col={14} row={14}>
    <Input maxLength={12} className='bms underLine' name='trnamt' id='trnamt' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={27} row={14}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={31} row={14}>
    <pre style={{color:"turquoise"}}>
         Orig Date: 
    </pre>
</GridItem>

    
<GridItem col={42} row={14}>
    <Input maxLength={10} className='bms underLine' name='torigdt' id='torigdt' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={53} row={14}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={57} row={14}>
    <pre style={{color:"turquoise"}}>
         Proc Date: 
    </pre>
</GridItem>

    
<GridItem col={68} row={14}>
    <Input maxLength={10} className='bms underLine' name='tprocdt' id='tprocdt' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={79} row={14}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={13} row={15}>
    <pre style={{color:"#7faded"}}>
         (-99999999.99) 
    </pre>
</GridItem>

    
<GridItem col={41} row={15}>
    <pre style={{color:"#7faded"}}>
         (YYYY-MM-DD) 
    </pre>
</GridItem>

    
<GridItem col={67} row={15}>
    <pre style={{color:"#7faded"}}>
         (YYYY-MM-DD) 
    </pre>
</GridItem>

    
<GridItem col={6} row={16}>
    <pre style={{color:"turquoise"}}>
         Merchant ID: 
    </pre>
</GridItem>

    
<GridItem col={19} row={16}>
    <Input maxLength={9} className='bms underLine' name='mid' id='mid' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={29} row={16}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={33} row={16}>
    <pre style={{color:"turquoise"}}>
         Merchant Name: 
    </pre>
</GridItem>

    
<GridItem col={48} row={16}>
    <Input maxLength={30} className='bms underLine' name='mname' id='mname' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={79} row={16}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={18}>
    <pre style={{color:"turquoise"}}>
         Merchant City: 
    </pre>
</GridItem>

    
<GridItem col={21} row={18}>
    <Input maxLength={25} className='bms underLine' name='mcity' id='mcity' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={47} row={18}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={53} row={18}>
    <pre style={{color:"turquoise"}}>
         Merchant Zip: 
    </pre>
</GridItem>

    
<GridItem col={67} row={18}>
    <Input maxLength={10} className='bms underLine' name='mzip' id='mzip' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={78} row={18}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={21}>
    <pre style={{color:"turquoise"}}>
         You are about to add this transaction. Please confirm : 
    </pre>
</GridItem>

    
<GridItem col={63} row={21}>
    <Input maxLength={1} className='bms underLine' name='confirm' id='confirm' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={65} row={21}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={66} row={21}>
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
         ENTER=Continue  F3=Back  F4=Clear  F5=Copy Last Tran. 
    </pre>
</GridItem>

    
    </>
  );
}
    