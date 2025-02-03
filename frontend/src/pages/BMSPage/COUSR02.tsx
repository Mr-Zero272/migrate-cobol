
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COUSR02() {
    
    type formInput = {
        usridin: string,
fname: string,
lname: string,
passwd: string,
usrtype: string,

    }

    type formOutput = {
        cousr02: string,
cousr2a: string,
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
        usridin: '',
fname: '',
lname: '',
passwd: '',
usrtype: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        cousr02: '',
cousr2a: '',
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
        httpConfig.domain + '/Cousr02',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COUSR02</title>
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
         Update User 
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
    <Input maxLength={20} className='bms underLine' name='fname' id='fname' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={39} row={11}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={45} row={11}>
    <pre style={{color:"turquoise"}}>
         Last Name: 
    </pre>
</GridItem>

    
<GridItem col={56} row={11}>
    <Input maxLength={20} className='bms underLine' name='lname' id='lname' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={77} row={11}>
    <pre style={{color:"green"}}>
          
    </pre>
</GridItem>

    
<GridItem col={6} row={13}>
    <pre style={{color:"turquoise"}}>
         Password: 
    </pre>
</GridItem>

    
<GridItem col={16} row={13}>
    <Input maxLength={8} className='bms underLine' name='passwd' id='passwd' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={25} row={13}>
    <pre style={{color:"#7faded"}}>
         (8 Char) 
    </pre>
</GridItem>

    
<GridItem col={6} row={15}>
    <pre style={{color:"turquoise"}}>
         User Type:  
    </pre>
</GridItem>

    
<GridItem col={17} row={15}>
    <Input maxLength={1} className='bms underLine' name='usrtype' id='usrtype' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
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
         ENTER=Fetch  F3=Save&&Exit  F4=Clear  F5=Save  F12=Cancel 
    </pre>
</GridItem>

    
    </>
  );
}
    