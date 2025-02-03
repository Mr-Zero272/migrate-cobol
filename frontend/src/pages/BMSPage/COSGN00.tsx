
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COSGN00() {
    
    type formInput = {
        userid: string,
passwd: string,

    }

    type formOutput = {
        cosgn00: string,
cosgn0a: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
applid: string,
sysid: string,
errmsg: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
    {
        userid: '',
passwd: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        cosgn00: '',
cosgn0a: '',
trnname: '',
title01: '',
curdate: 'mm/dd/yy',
pgmname: '',
title02: '',
curtime: 'Ahh:mm:ss',
applid: '',
sysid: '        ',
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
        httpConfig.domain + '/Cosgn00',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COSGN00</title>
    </Helmet>
    
<GridItem col={1} row={1}>
    <pre style={{color:"#7faded"}}>
         Tran : 
    </pre>
</GridItem>

    
<GridItem col={8} row={1}>
    <pre style={{color:"#7faded"}}>
         {receivedData.trnname } 
    </pre>
</GridItem>

    
<GridItem col={21} row={1}>
    <pre style={{color:"yellow"}}>
         {receivedData.title01 } 
    </pre>
</GridItem>

    
<GridItem col={64} row={1}>
    <pre style={{color:"#7faded"}}>
         Date : 
    </pre>
</GridItem>

    
<GridItem col={71} row={1}>
    <pre style={{color:"#7faded"}}>
         {receivedData.curdate } 
    </pre>
</GridItem>

    
<GridItem col={1} row={2}>
    <pre style={{color:"#7faded"}}>
         Prog : 
    </pre>
</GridItem>

    
<GridItem col={8} row={2}>
    <pre style={{color:"#7faded"}}>
         {receivedData.pgmname } 
    </pre>
</GridItem>

    
<GridItem col={21} row={2}>
    <pre style={{color:"yellow"}}>
         {receivedData.title02 } 
    </pre>
</GridItem>

    
<GridItem col={64} row={2}>
    <pre style={{color:"#7faded"}}>
         Time : 
    </pre>
</GridItem>

    
<GridItem col={71} row={2}>
    <pre style={{color:"#7faded"}}>
         {receivedData.curtime } 
    </pre>
</GridItem>

    
<GridItem col={1} row={3}>
    <pre style={{color:"#7faded"}}>
         AppID: 
    </pre>
</GridItem>

    
<GridItem col={8} row={3}>
    <pre style={{color:"#7faded"}}>
         {receivedData.applid } 
    </pre>
</GridItem>

    
<GridItem col={64} row={3}>
    <pre style={{color:"#7faded"}}>
         SysID: 
    </pre>
</GridItem>

    
<GridItem col={71} row={3}>
    <pre style={{color:"#7faded"}}>
         {receivedData.sysid } 
    </pre>
</GridItem>

    
<GridItem col={6} row={5}>
    <pre style={{color:"neutral"}}>
         This is a Credit Card Demo Application for Mainframe Modernization 
    </pre>
</GridItem>

    
<GridItem col={21} row={7}>
    <pre style={{color:"#7faded"}}>
         +========================================+ 
    </pre>
</GridItem>

    
<GridItem col={21} row={8}>
    <pre style={{color:"#7faded"}}>
         |%%%%%%%  NATIONAL RESERVE NOTE  %%%%%%%%| 
    </pre>
</GridItem>

    
<GridItem col={21} row={9}>
    <pre style={{color:"#7faded"}}>
         |%(1)  THE UNITED STATES OF KICSLAND (1)%| 
    </pre>
</GridItem>

    
<GridItem col={21} row={10}>
    <pre style={{color:"#7faded"}}>
         |%$$              ___       ********  $$%| 
    </pre>
</GridItem>

    
<GridItem col={21} row={11}>
    <pre style={{color:"#7faded"}}>
         |%$    {"{x}"}       (o o)                 $%| 
    </pre>
</GridItem>

    
<GridItem col={21} row={12}>
    <pre style={{color:"#7faded"}}>
         |%$     ******  (  V  )      O N E     $%| 
    </pre>
</GridItem>

    
<GridItem col={21} row={13}>
    <pre style={{color:"#7faded"}}>
         |%(1)          ---m-m---             (1)%| 
    </pre>
</GridItem>

    
<GridItem col={21} row={14}>
    <pre style={{color:"#7faded"}}>
         |%%‾‾‾‾‾‾‾‾‾‾‾ ONE DOLLAR ‾‾‾‾‾‾‾‾‾‾‾‾‾%%| 
    </pre>
</GridItem>

    
<GridItem col={21} row={15}>
    <pre style={{color:"#7faded"}}>
         +========================================+ 
    </pre>
</GridItem>

    
<GridItem col={16} row={17}>
    <pre style={{color:"turquoise"}}>
         Type your User ID and Password, then press ENTER: 
    </pre>
</GridItem>

    
<GridItem col={29} row={19}>
    <pre style={{color:"turquoise"}}>
         User ID     : 
    </pre>
</GridItem>

    
<GridItem col={43} row={19}>
    <Input maxLength={8} className='bms' name='userid' id='userid' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={52} row={19}>
    <pre style={{color:"green"}}>
          
    </pre>
</GridItem>

    
<GridItem col={52} row={19}>
    <pre style={{color:"#7faded"}}>
         (8 Char) 
    </pre>
</GridItem>

    
<GridItem col={29} row={20}>
    <pre style={{color:"turquoise"}}>
         Password    : 
    </pre>
</GridItem>

    
<GridItem col={43} row={20}>
    <Input maxLength={8} className='bms' name='passwd' id='passwd' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={52} row={20}>
    <pre style={{color:"green"}}>
          
    </pre>
</GridItem>

    
<GridItem col={52} row={20}>
    <pre style={{color:"#7faded"}}>
         (8 Char) 
    </pre>
</GridItem>

    
<GridItem col={61} row={20}>
    <Input maxLength={1} className='bms'   type='text'    />
</GridItem>
    
<GridItem col={63} row={20}>
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
         ENTER=Sign-on  F3=Exit 
    </pre>
</GridItem>

    
    </>
  );
}
    