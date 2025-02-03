
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COCRDUP() {
    
    type formInput = {
        acctsid: string,
cardsid: string,
crdname: string,
crdstcd: string,
expmon: string,
expyear: string,

    }

    type formOutput = {
        cocrdup: string,
ccrdupa: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
expday: string,
infomsg: string,
errmsg: string,
fkeys: string,
fkeysc: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
    {
        acctsid: '',
cardsid: '',
crdname: '',
crdstcd: '',
expmon: '',
expyear: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        cocrdup: '',
ccrdupa: '',
trnname: '',
title01: '',
curdate: 'mm/dd/yy',
pgmname: '',
title02: '',
curtime: 'hh:mm:ss',
expday: '',
infomsg: '',
errmsg: '',
fkeys: 'ENTER=Process F3=Exit',
fkeysc: 'F5=Save F12=Cancel',

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
        httpConfig.domain + '/Cocrdup',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COCRDUP</title>
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
         Update Credit Card Details 
    </pre>
</GridItem>

    
<GridItem col={23} row={7}>
    <pre style={{color:"turquoise"}}>
         Account Number    : 
    </pre>
</GridItem>

    
<GridItem col={45} row={7}>
    <Input maxLength={11} className='bms underLine' name='acctsid' id='acctsid' type='text' styles={{color:"default"}} disabled  />
</GridItem>
    
<GridItem col={57} row={7}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={23} row={8}>
    <pre style={{color:"turquoise"}}>
         Card Number       : 
    </pre>
</GridItem>

    
<GridItem col={45} row={8}>
    <Input maxLength={16} className='bms underLine' name='cardsid' id='cardsid' type='text' styles={{color:"default"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={62} row={8}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={4} row={11}>
    <pre style={{color:"turquoise"}}>
         Name on card      : 
    </pre>
</GridItem>

    
<GridItem col={25} row={11}>
    <Input maxLength={50} className='bms underLine' name='crdname' id='crdname' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={76} row={11}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={4} row={13}>
    <pre style={{color:"turquoise"}}>
         Card Active Y/N   :  
    </pre>
</GridItem>

    
<GridItem col={25} row={13}>
    <Input maxLength={1} className='bms underLine' name='crdstcd' id='crdstcd' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={27} row={13}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={4} row={15}>
    <pre style={{color:"turquoise"}}>
         Expiry Date       :  
    </pre>
</GridItem>

    
<GridItem col={25} row={15}>
    <Input maxLength={2} className='bms underLine' name='expmon' id='expmon' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={28} row={15}>
    <pre >
         / 
    </pre>
</GridItem>

    
<GridItem col={30} row={15}>
    <Input maxLength={4} className='bms underLine' name='expyear' id='expyear' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={35} row={15}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={36} row={15}>
    <pre >
         {receivedData.expday } 
    </pre>
</GridItem>

    
<GridItem col={39} row={15}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={25} row={20}>
    <pre style={{color:"neutral"}}>
         {receivedData.infomsg } 
    </pre>
</GridItem>

    
<GridItem col={1} row={23}>
    <pre style={{color:"red"}}>
         {receivedData.errmsg } 
    </pre>
</GridItem>

    
<GridItem col={1} row={24}>
    <pre style={{color:"yellow"}}>
         {receivedData.fkeys } 
    </pre>
</GridItem>

    
<GridItem col={23} row={24}>
    <pre style={{color:"yellow"}}>
         {receivedData.fkeysc } 
    </pre>
</GridItem>

    
    </>
  );
}
    