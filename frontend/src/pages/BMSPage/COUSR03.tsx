
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COUSR03() {
    
    type formInput = {
        usridin: string,

    }

    type formOutput = {
        cousr03: string,
cousr3a: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
fname: string,
lname: string,
usrtype: string,
errmsg: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
    {
        usridin: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        cousr03: '',
cousr3a: '',
trnname: '',
title01: '',
curdate: 'mm/dd/yy',
pgmname: '',
title02: '',
curtime: 'hh:mm:ss',
fname: '',
lname: '',
usrtype: '',
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
        httpConfig.domain + '/Cousr03',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COUSR03</title>
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
         Delete User 
    </pre>
</GridItem>

    
<GridItem col={6} row={6}>
    <pre style={{color:"green"}}>
         Enter User ID: 
    </pre>
</GridItem>

    
<GridItem col={21} row={6}>
    <Input maxLength={8} className='bms underLine' name='usridin' id='usridin' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={30} row={6}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={8}>
    <pre style={{color:"yellow"}}>
         ********************************************************************** 
    </pre>
</GridItem>

    
<GridItem col={6} row={11}>
    <pre style={{color:"turquoise"}}>
         First Name: 
    </pre>
</GridItem>

    
<GridItem col={18} row={11}>
    <pre style={{color:"#7faded"}}>
         {receivedData.fname } 
    </pre>
</GridItem>

    
<GridItem col={39} row={11}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={6} row={13}>
    <pre style={{color:"turquoise"}}>
         Last Name: 
    </pre>
</GridItem>

    
<GridItem col={18} row={13}>
    <pre style={{color:"#7faded"}}>
         {receivedData.lname } 
    </pre>
</GridItem>

    
<GridItem col={39} row={13}>
    <pre style={{color:"green"}}>
          
    </pre>
</GridItem>

    
<GridItem col={6} row={15}>
    <pre style={{color:"turquoise"}}>
         User Type:  
    </pre>
</GridItem>

    
<GridItem col={17} row={15}>
    <pre style={{color:"#7faded"}}>
         {receivedData.usrtype } 
    </pre>
</GridItem>

    
<GridItem col={19} row={15}>
    <pre style={{color:"#7faded"}}>
         (A=Admin, U=User) 
    </pre>
</GridItem>

    
<GridItem col={1} row={23}>
    <pre style={{color:"red"}}>
         {receivedData.errmsg } 
    </pre>
</GridItem>

    
<GridItem col={1} row={24}>
    <pre style={{color:"yellow"}}>
         ENTER=Fetch  F3=Back  F4=Clear  F5=Delete 
    </pre>
</GridItem>

    
    </>
  );
}
    