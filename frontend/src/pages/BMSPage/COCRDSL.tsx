
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COCRDSL() {
    
    type formInput = {
        acctsid: string,
cardsid: string,

    }

    type formOutput = {
        cocrdsl: string,
ccrdsla: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
crdname: string,
crdstcd: string,
expmon: string,
expyear: string,
infomsg: string,
errmsg: string,
fkeys: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
    {
        acctsid: '',
cardsid: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        cocrdsl: '',
ccrdsla: '',
trnname: '',
title01: '',
curdate: 'mm/dd/yy',
pgmname: '',
title02: '',
curtime: 'hh:mm:ss',
crdname: '',
crdstcd: '',
expmon: '',
expyear: '',
infomsg: '',
errmsg: '',
fkeys: 'ENTER=Search Cards  F3=Exit',

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
        httpConfig.domain + '/Cocrdsl',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COCRDSL</title>
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
         View Credit Card Detail 
    </pre>
</GridItem>

    
<GridItem col={23} row={7}>
    <pre style={{color:"turquoise"}}>
         Account Number    : 
    </pre>
</GridItem>

    
<GridItem col={45} row={7}>
    <Input maxLength={11} className='bms underLine' name='acctsid' id='acctsid' type='text' styles={{color:"default"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
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
    <pre >
         {receivedData.crdname } 
    </pre>
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
    <pre >
         {receivedData.crdstcd } 
    </pre>
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
    <pre >
         {receivedData.expmon } 
    </pre>
</GridItem>

    
<GridItem col={28} row={15}>
    <pre >
         / 
    </pre>
</GridItem>

    
<GridItem col={30} row={15}>
    <pre >
         {receivedData.expyear } 
    </pre>
</GridItem>

    
<GridItem col={35} row={15}>
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

    
    </>
  );
}
    