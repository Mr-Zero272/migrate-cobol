
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COACTUP() {
    
    type formInput = {
        acctsid: string,
acsttus: string,
opnyear: string,
opnmon: string,
opnday: string,
acrdlim: string,
expyear: string,
expmon: string,
expday: string,
acshlim: string,
risyear: string,
rismon: string,
risday: string,
acurbal: string,
acrcycr: string,
aaddgrp: string,
acrcydb: string,
acstnum: string,
actssn1: string,
actssn2: string,
actssn3: string,
dobyear: string,
dobmon: string,
dobday: string,
acstfco: string,
acsfnam: string,
acsmnam: string,
acslnam: string,
acsadl1: string,
acsstte: string,
acsadl2: string,
acszipc: string,
acscity: string,
acsctry: string,
acsph1a: string,
acsph1b: string,
acsph1c: string,
acsgovt: string,
acsph2a: string,
acsph2b: string,
acsph2c: string,
acseftc: string,
acspflg: string,

    }

    type formOutput = {
        coactup: string,
cactupa: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
infomsg: string,
errmsg: string,
fkeys: string,
fkey05: string,
fkey12: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
    {
        acctsid: '',
acsttus: '',
opnyear: '',
opnmon: '',
opnday: '',
acrdlim: '',
expyear: '',
expmon: '',
expday: '',
acshlim: '',
risyear: '',
rismon: '',
risday: '',
acurbal: '',
acrcycr: '',
aaddgrp: '',
acrcydb: '',
acstnum: '',
actssn1: '',
actssn2: '',
actssn3: '',
dobyear: '',
dobmon: '',
dobday: '',
acstfco: '',
acsfnam: '',
acsmnam: '',
acslnam: '',
acsadl1: '',
acsstte: '',
acsadl2: '',
acszipc: '',
acscity: '',
acsctry: '',
acsph1a: '',
acsph1b: '',
acsph1c: '',
acsgovt: '',
acsph2a: '',
acsph2b: '',
acsph2c: '',
acseftc: '',
acspflg: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        coactup: '',
cactupa: '',
trnname: '',
title01: '',
curdate: 'mm/dd/yy',
pgmname: '',
title02: '',
curtime: 'hh:mm:ss',
infomsg: '',
errmsg: '',
fkeys: 'ENTER=Process F3=Exit',
fkey05: 'F5=Save',
fkey12: 'F12=Cancel',

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
        httpConfig.domain + '/Coactup',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COACTUP</title>
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

    
<GridItem col={33} row={4}>
    <pre style={{color:"neutral"}}>
         Update Account 
    </pre>
</GridItem>

    
<GridItem col={19} row={5}>
    <pre style={{color:"turquoise"}}>
         Account Number : 
    </pre>
</GridItem>

    
<GridItem col={38} row={5}>
    <Input maxLength={11} className='bms underLine' name='acctsid' id='acctsid' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={50} row={5}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={57} row={5}>
    <pre style={{color:"turquoise"}}>
         Active Y/N:  
    </pre>
</GridItem>

    
<GridItem col={70} row={5}>
    <Input maxLength={1} className='bms underLine' name='acsttus' id='acsttus' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={72} row={5}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={6}>
    <pre style={{color:"turquoise"}}>
         Opened : 
    </pre>
</GridItem>

    
<GridItem col={17} row={6}>
    <Input maxLength={4} className='bms underLine' name='opnyear' id='opnyear' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={22} row={6}>
    <pre >
         - 
    </pre>
</GridItem>

    
<GridItem col={24} row={6}>
    <Input maxLength={2} className='bms underLine' name='opnmon' id='opnmon' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={27} row={6}>
    <pre >
         - 
    </pre>
</GridItem>

    
<GridItem col={29} row={6}>
    <Input maxLength={2} className='bms underLine' name='opnday' id='opnday' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={32} row={6}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={39} row={6}>
    <pre style={{color:"turquoise"}}>
         Credit Limit        : 
    </pre>
</GridItem>

    
<GridItem col={61} row={6}>
    <Input maxLength={15} className='bms underLine' name='acrdlim' id='acrdlim' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={77} row={6}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={7}>
    <pre style={{color:"turquoise"}}>
         Expiry : 
    </pre>
</GridItem>

    
<GridItem col={17} row={7}>
    <Input maxLength={4} className='bms underLine' name='expyear' id='expyear' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={22} row={7}>
    <pre >
         - 
    </pre>
</GridItem>

    
<GridItem col={24} row={7}>
    <Input maxLength={2} className='bms underLine' name='expmon' id='expmon' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={27} row={7}>
    <pre >
         - 
    </pre>
</GridItem>

    
<GridItem col={29} row={7}>
    <Input maxLength={2} className='bms underLine' name='expday' id='expday' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={32} row={7}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={39} row={7}>
    <pre style={{color:"turquoise"}}>
         Cash credit Limit   : 
    </pre>
</GridItem>

    
<GridItem col={61} row={7}>
    <Input maxLength={15} className='bms underLine' name='acshlim' id='acshlim' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={77} row={7}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={8}>
    <pre style={{color:"turquoise"}}>
         Reissue: 
    </pre>
</GridItem>

    
<GridItem col={17} row={8}>
    <Input maxLength={4} className='bms underLine' name='risyear' id='risyear' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={22} row={8}>
    <pre >
         - 
    </pre>
</GridItem>

    
<GridItem col={24} row={8}>
    <Input maxLength={2} className='bms underLine' name='rismon' id='rismon' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={27} row={8}>
    <pre >
         - 
    </pre>
</GridItem>

    
<GridItem col={29} row={8}>
    <Input maxLength={2} className='bms underLine' name='risday' id='risday' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={32} row={8}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={39} row={8}>
    <pre style={{color:"turquoise"}}>
         Current Balance     : 
    </pre>
</GridItem>

    
<GridItem col={61} row={8}>
    <Input maxLength={15} className='bms underLine' name='acurbal' id='acurbal' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={77} row={8}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={39} row={9}>
    <pre style={{color:"turquoise"}}>
         Current Cycle Credit: 
    </pre>
</GridItem>

    
<GridItem col={61} row={9}>
    <Input maxLength={15} className='bms underLine' name='acrcycr' id='acrcycr' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={77} row={9}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={10}>
    <pre style={{color:"turquoise"}}>
         Account Group: 
    </pre>
</GridItem>

    
<GridItem col={23} row={10}>
    <Input maxLength={10} className='bms underLine' name='aaddgrp' id='aaddgrp' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={34} row={10}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={39} row={10}>
    <pre style={{color:"turquoise"}}>
         Current Cycle Debit : 
    </pre>
</GridItem>

    
<GridItem col={61} row={10}>
    <Input maxLength={15} className='bms underLine' name='acrcydb' id='acrcydb' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={77} row={10}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={32} row={11}>
    <pre style={{color:"neutral"}}>
         Customer Details 
    </pre>
</GridItem>

    
<GridItem col={8} row={12}>
    <pre style={{color:"turquoise"}}>
         Customer id  : 
    </pre>
</GridItem>

    
<GridItem col={23} row={12}>
    <Input maxLength={9} className='bms underLine' name='acstnum' id='acstnum' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={33} row={12}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={49} row={12}>
    <pre style={{color:"turquoise"}}>
         SSN: 
    </pre>
</GridItem>

    
<GridItem col={55} row={12}>
    <Input maxLength={3} className='bms underLine' name='actssn1' id='actssn1' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={59} row={12}>
    <pre >
         - 
    </pre>
</GridItem>

    
<GridItem col={61} row={12}>
    <Input maxLength={2} className='bms underLine' name='actssn2' id='actssn2' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={64} row={12}>
    <pre >
         - 
    </pre>
</GridItem>

    
<GridItem col={66} row={12}>
    <Input maxLength={4} className='bms underLine' name='actssn3' id='actssn3' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={71} row={12}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={8} row={13}>
    <pre style={{color:"turquoise"}}>
         Date of birth: 
    </pre>
</GridItem>

    
<GridItem col={23} row={13}>
    <Input maxLength={4} className='bms underLine' name='dobyear' id='dobyear' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={28} row={13}>
    <pre >
         - 
    </pre>
</GridItem>

    
<GridItem col={30} row={13}>
    <Input maxLength={2} className='bms underLine' name='dobmon' id='dobmon' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={33} row={13}>
    <pre >
         - 
    </pre>
</GridItem>

    
<GridItem col={35} row={13}>
    <Input maxLength={2} className='bms underLine' name='dobday' id='dobday' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={38} row={13}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={49} row={13}>
    <pre style={{color:"turquoise"}}>
         FICO Score: 
    </pre>
</GridItem>

    
<GridItem col={62} row={13}>
    <Input maxLength={3} className='bms underLine' name='acstfco' id='acstfco' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={66} row={13}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={1} row={14}>
    <pre style={{color:"turquoise"}}>
         First Name 
    </pre>
</GridItem>

    
<GridItem col={28} row={14}>
    <pre style={{color:"turquoise"}}>
         Middle Name:  
    </pre>
</GridItem>

    
<GridItem col={55} row={14}>
    <pre style={{color:"turquoise"}}>
         Last Name :  
    </pre>
</GridItem>

    
<GridItem col={1} row={15}>
    <Input maxLength={25} className='bms underLine' name='acsfnam' id='acsfnam' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={27} row={15}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={28} row={15}>
    <Input maxLength={25} className='bms underLine' name='acsmnam' id='acsmnam' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={54} row={15}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={55} row={15}>
    <Input maxLength={25} className='bms underLine' name='acslnam' id='acslnam' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={1} row={16}>
    <pre style={{color:"turquoise"}}>
         Address: 
    </pre>
</GridItem>

    
<GridItem col={10} row={16}>
    <Input maxLength={50} className='bms underLine' name='acsadl1' id='acsadl1' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={61} row={16}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={63} row={16}>
    <pre style={{color:"turquoise"}}>
         State  
    </pre>
</GridItem>

    
<GridItem col={73} row={16}>
    <Input maxLength={2} className='bms underLine' name='acsstte' id='acsstte' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={76} row={16}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={10} row={17}>
    <Input maxLength={50} className='bms underLine' name='acsadl2' id='acsadl2' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={61} row={17}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={63} row={17}>
    <pre style={{color:"turquoise"}}>
         Zip 
    </pre>
</GridItem>

    
<GridItem col={73} row={17}>
    <Input maxLength={5} className='bms underLine' name='acszipc' id='acszipc' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={79} row={17}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={1} row={18}>
    <pre style={{color:"turquoise"}}>
         City  
    </pre>
</GridItem>

    
<GridItem col={10} row={18}>
    <Input maxLength={50} className='bms underLine' name='acscity' id='acscity' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={61} row={18}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={63} row={18}>
    <pre style={{color:"turquoise"}}>
         Country 
    </pre>
</GridItem>

    
<GridItem col={73} row={18}>
    <Input maxLength={3} className='bms underLine' name='acsctry' id='acsctry' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={77} row={18}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={1} row={19}>
    <pre style={{color:"turquoise"}}>
         Phone 1: 
    </pre>
</GridItem>

    
<GridItem col={10} row={19}>
    <Input maxLength={3} className='bms underLine' name='acsph1a' id='acsph1a' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={14} row={19}>
    <Input maxLength={3} className='bms underLine' name='acsph1b' id='acsph1b' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={18} row={19}>
    <Input maxLength={4} className='bms underLine' name='acsph1c' id='acsph1c' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={23} row={19}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={24} row={19}>
    <pre style={{color:"turquoise"}}>
         Government Issued Id Ref    :  
    </pre>
</GridItem>

    
<GridItem col={58} row={19}>
    <Input maxLength={20} className='bms underLine' name='acsgovt' id='acsgovt' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={79} row={19}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={1} row={20}>
    <pre style={{color:"turquoise"}}>
         Phone 2: 
    </pre>
</GridItem>

    
<GridItem col={10} row={20}>
    <Input maxLength={3} className='bms underLine' name='acsph2a' id='acsph2a' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={14} row={20}>
    <Input maxLength={3} className='bms underLine' name='acsph2b' id='acsph2b' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={18} row={20}>
    <Input maxLength={4} className='bms underLine' name='acsph2c' id='acsph2c' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={23} row={20}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={24} row={20}>
    <pre style={{color:"turquoise"}}>
         EFT Account Id:  
    </pre>
</GridItem>

    
<GridItem col={41} row={20}>
    <Input maxLength={10} className='bms underLine' name='acseftc' id='acseftc' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={52} row={20}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={53} row={20}>
    <pre style={{color:"turquoise"}}>
         Primary Card Holder Y/N: 
    </pre>
</GridItem>

    
<GridItem col={78} row={20}>
    <Input maxLength={1} className='bms underLine' name='acspflg' id='acspflg' type='text'   onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={80} row={20}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={23} row={22}>
    <pre style={{color:"neutral"}}>
         {receivedData.infomsg } 
    </pre>
</GridItem>

    
<GridItem col={69} row={22}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={1} row={1}>
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
         {receivedData.fkeys } 
    </pre>
</GridItem>

    
<GridItem col={23} row={24}>
    <pre style={{color:"yellow"}}>
         {receivedData.fkey05 } 
    </pre>
</GridItem>

    
<GridItem col={31} row={24}>
    <pre style={{color:"yellow"}}>
         {receivedData.fkey12 } 
    </pre>
</GridItem>

    
    </>
  );
}
    