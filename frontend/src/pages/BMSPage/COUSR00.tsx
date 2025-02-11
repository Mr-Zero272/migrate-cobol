<<<<<<< refs/remotes/origin/khanglt6

import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import Input from '../../components/Input';

export default function COUSR00() {
    
    type formInput = {
        usridin: string,
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
        cousr00: string,
cousr0a: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
pagenum: string,
usrid01: string,
fname01: string,
lname01: string,
utype01: string,
usrid02: string,
fname02: string,
lname02: string,
utype02: string,
usrid03: string,
fname03: string,
lname03: string,
utype03: string,
usrid04: string,
fname04: string,
lname04: string,
utype04: string,
usrid05: string,
fname05: string,
lname05: string,
utype05: string,
usrid06: string,
fname06: string,
lname06: string,
utype06: string,
usrid07: string,
fname07: string,
lname07: string,
utype07: string,
usrid08: string,
fname08: string,
lname08: string,
utype08: string,
usrid09: string,
fname09: string,
lname09: string,
utype09: string,
usrid10: string,
fname10: string,
lname10: string,
utype10: string,
errmsg: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
    {
        usridin: '',
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
        cousr00: '',
cousr0a: '',
trnname: '',
title01: '',
curdate: 'mm/dd/yy',
pgmname: '',
title02: '',
curtime: 'hh:mm:ss',
pagenum: ' ',
usrid01: ' ',
fname01: ' ',
lname01: ' ',
utype01: ' ',
usrid02: ' ',
fname02: ' ',
lname02: ' ',
utype02: ' ',
usrid03: ' ',
fname03: ' ',
lname03: ' ',
utype03: ' ',
usrid04: ' ',
fname04: ' ',
lname04: ' ',
utype04: ' ',
usrid05: ' ',
fname05: ' ',
lname05: ' ',
utype05: ' ',
usrid06: ' ',
fname06: ' ',
lname06: ' ',
utype06: ' ',
usrid07: ' ',
fname07: ' ',
lname07: ' ',
utype07: ' ',
usrid08: ' ',
fname08: ' ',
lname08: ' ',
utype08: ' ',
usrid09: ' ',
fname09: ' ',
lname09: ' ',
utype09: ' ',
usrid10: ' ',
fname10: ' ',
lname10: ' ',
utype10: ' ',
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
        httpConfig.domain + '/Cousr00',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COUSR00</title>
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
         List Users 
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
         Search User ID: 
    </pre>
</GridItem>

    
<GridItem col={21} row={6}>
    <Input maxLength={8} className='bms underLine' name='usridin' id='usridin' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={30} row={6}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={5} row={8}>
    <pre style={{color:"neutral"}}>
         Sel 
    </pre>
</GridItem>

    
<GridItem col={12} row={8}>
    <pre style={{color:"neutral"}}>
         User ID  
    </pre>
</GridItem>

    
<GridItem col={24} row={8}>
    <pre style={{color:"neutral"}}>
              First Name      
    </pre>
</GridItem>

    
<GridItem col={48} row={8}>
    <pre style={{color:"neutral"}}>
              Last Name       
    </pre>
</GridItem>

    
<GridItem col={72} row={8}>
    <pre style={{color:"neutral"}}>
         Type 
    </pre>
</GridItem>

    
<GridItem col={5} row={9}>
    <pre style={{color:"neutral"}}>
         --- 
    </pre>
</GridItem>

    
<GridItem col={12} row={9}>
    <pre style={{color:"neutral"}}>
         -------- 
    </pre>
</GridItem>

    
<GridItem col={24} row={9}>
    <pre style={{color:"neutral"}}>
         -------------------- 
    </pre>
</GridItem>

    
<GridItem col={48} row={9}>
    <pre style={{color:"neutral"}}>
         -------------------- 
    </pre>
</GridItem>

    
<GridItem col={72} row={9}>
    <pre style={{color:"neutral"}}>
         ---- 
    </pre>
</GridItem>

    
<GridItem col={6} row={10}>
    <Input maxLength={1} className='bms underLine' name='sel0001' id='sel0001' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={8} row={10}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={12} row={10}>
    <pre style={{color:"#7faded"}}>
         {receivedData.usrid01 } 
    </pre>
</GridItem>

    
<GridItem col={24} row={10}>
    <pre style={{color:"#7faded"}}>
         {receivedData.fname01 } 
    </pre>
</GridItem>

    
<GridItem col={48} row={10}>
    <pre style={{color:"#7faded"}}>
         {receivedData.lname01 } 
    </pre>
</GridItem>

    
<GridItem col={73} row={10}>
    <pre style={{color:"#7faded"}}>
         {receivedData.utype01 } 
    </pre>
</GridItem>

    
<GridItem col={6} row={11}>
    <Input maxLength={1} className='bms underLine' name='sel0002' id='sel0002' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={8} row={11}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={12} row={11}>
    <pre style={{color:"#7faded"}}>
         {receivedData.usrid02 } 
    </pre>
</GridItem>

    
<GridItem col={24} row={11}>
    <pre style={{color:"#7faded"}}>
         {receivedData.fname02 } 
    </pre>
</GridItem>

    
<GridItem col={48} row={11}>
    <pre style={{color:"#7faded"}}>
         {receivedData.lname02 } 
    </pre>
</GridItem>

    
<GridItem col={73} row={11}>
    <pre style={{color:"#7faded"}}>
         {receivedData.utype02 } 
    </pre>
</GridItem>

    
<GridItem col={6} row={12}>
    <Input maxLength={1} className='bms underLine' name='sel0003' id='sel0003' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={8} row={12}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={12} row={12}>
    <pre style={{color:"#7faded"}}>
         {receivedData.usrid03 } 
    </pre>
</GridItem>

    
<GridItem col={24} row={12}>
    <pre style={{color:"#7faded"}}>
         {receivedData.fname03 } 
    </pre>
</GridItem>

    
<GridItem col={48} row={12}>
    <pre style={{color:"#7faded"}}>
         {receivedData.lname03 } 
    </pre>
</GridItem>

    
<GridItem col={73} row={12}>
    <pre style={{color:"#7faded"}}>
         {receivedData.utype03 } 
    </pre>
</GridItem>

    
<GridItem col={6} row={13}>
    <Input maxLength={1} className='bms underLine' name='sel0004' id='sel0004' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={8} row={13}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={12} row={13}>
    <pre style={{color:"#7faded"}}>
         {receivedData.usrid04 } 
    </pre>
</GridItem>

    
<GridItem col={24} row={13}>
    <pre style={{color:"#7faded"}}>
         {receivedData.fname04 } 
    </pre>
</GridItem>

    
<GridItem col={48} row={13}>
    <pre style={{color:"#7faded"}}>
         {receivedData.lname04 } 
    </pre>
</GridItem>

    
<GridItem col={73} row={13}>
    <pre style={{color:"#7faded"}}>
         {receivedData.utype04 } 
    </pre>
</GridItem>

    
<GridItem col={6} row={14}>
    <Input maxLength={1} className='bms underLine' name='sel0005' id='sel0005' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={8} row={14}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={12} row={14}>
    <pre style={{color:"#7faded"}}>
         {receivedData.usrid05 } 
    </pre>
</GridItem>

    
<GridItem col={24} row={14}>
    <pre style={{color:"#7faded"}}>
         {receivedData.fname05 } 
    </pre>
</GridItem>

    
<GridItem col={48} row={14}>
    <pre style={{color:"#7faded"}}>
         {receivedData.lname05 } 
    </pre>
</GridItem>

    
<GridItem col={73} row={14}>
    <pre style={{color:"#7faded"}}>
         {receivedData.utype05 } 
    </pre>
</GridItem>

    
<GridItem col={6} row={15}>
    <Input maxLength={1} className='bms underLine' name='sel0006' id='sel0006' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={8} row={15}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={12} row={15}>
    <pre style={{color:"#7faded"}}>
         {receivedData.usrid06 } 
    </pre>
</GridItem>

    
<GridItem col={24} row={15}>
    <pre style={{color:"#7faded"}}>
         {receivedData.fname06 } 
    </pre>
</GridItem>

    
<GridItem col={48} row={15}>
    <pre style={{color:"#7faded"}}>
         {receivedData.lname06 } 
    </pre>
</GridItem>

    
<GridItem col={73} row={15}>
    <pre style={{color:"#7faded"}}>
         {receivedData.utype06 } 
    </pre>
</GridItem>

    
<GridItem col={6} row={16}>
    <Input maxLength={1} className='bms underLine' name='sel0007' id='sel0007' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={8} row={16}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={12} row={16}>
    <pre style={{color:"#7faded"}}>
         {receivedData.usrid07 } 
    </pre>
</GridItem>

    
<GridItem col={24} row={16}>
    <pre style={{color:"#7faded"}}>
         {receivedData.fname07 } 
    </pre>
</GridItem>

    
<GridItem col={48} row={16}>
    <pre style={{color:"#7faded"}}>
         {receivedData.lname07 } 
    </pre>
</GridItem>

    
<GridItem col={73} row={16}>
    <pre style={{color:"#7faded"}}>
         {receivedData.utype07 } 
    </pre>
</GridItem>

    
<GridItem col={6} row={17}>
    <Input maxLength={1} className='bms underLine' name='sel0008' id='sel0008' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={8} row={17}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={12} row={17}>
    <pre style={{color:"#7faded"}}>
         {receivedData.usrid08 } 
    </pre>
</GridItem>

    
<GridItem col={24} row={17}>
    <pre style={{color:"#7faded"}}>
         {receivedData.fname08 } 
    </pre>
</GridItem>

    
<GridItem col={48} row={17}>
    <pre style={{color:"#7faded"}}>
         {receivedData.lname08 } 
    </pre>
</GridItem>

    
<GridItem col={73} row={17}>
    <pre style={{color:"#7faded"}}>
         {receivedData.utype08 } 
    </pre>
</GridItem>

    
<GridItem col={6} row={18}>
    <Input maxLength={1} className='bms underLine' name='sel0009' id='sel0009' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={8} row={18}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={12} row={18}>
    <pre style={{color:"#7faded"}}>
         {receivedData.usrid09 } 
    </pre>
</GridItem>

    
<GridItem col={24} row={18}>
    <pre style={{color:"#7faded"}}>
         {receivedData.fname09 } 
    </pre>
</GridItem>

    
<GridItem col={48} row={18}>
    <pre style={{color:"#7faded"}}>
         {receivedData.lname09 } 
    </pre>
</GridItem>

    
<GridItem col={73} row={18}>
    <pre style={{color:"#7faded"}}>
         {receivedData.utype09 } 
    </pre>
</GridItem>

    
<GridItem col={6} row={19}>
    <Input maxLength={1} className='bms underLine' name='sel0010' id='sel0010' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={8} row={19}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={12} row={19}>
    <pre style={{color:"#7faded"}}>
         {receivedData.usrid10 } 
    </pre>
</GridItem>

    
<GridItem col={24} row={19}>
    <pre style={{color:"#7faded"}}>
         {receivedData.fname10 } 
    </pre>
</GridItem>

    
<GridItem col={48} row={19}>
    <pre style={{color:"#7faded"}}>
         {receivedData.lname10 } 
    </pre>
</GridItem>

    
<GridItem col={73} row={19}>
    <pre style={{color:"#7faded"}}>
         {receivedData.utype10 } 
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
    
=======
import React, {
  type ChangeEvent,
  useState,
  type KeyboardEvent,
  useEffect,
  useRef,
} from "react";

import { Helmet } from "react-helmet";

import axios from "axios";

import httpConfig from "../../config/httpConfig";

import { GridItem } from "../../components/GridSystem";

import Input from "../../components/Input";

import { useNavigate } from "react-router-dom";

export default function COUSR00() {
  type formInput = {
    usridin: string;
    sel0001: string;
    sel0002: string;
    sel0003: string;
    sel0004: string;
    sel0005: string;
    sel0006: string;
    sel0007: string;
    sel0008: string;
    sel0009: string;
    sel0010: string;
  };

  type formOutput = {
    cousr00: string;
    cousr0a: string;
    trnname: string;
    title01: string;
    curdate: string;
    pgmname: string;
    title02: string;
    curtime: string;
    pagenum: string;
    usrid01: string;
    fname01: string;
    lname01: string;
    utype01: string;
    usrid02: string;
    fname02: string;
    lname02: string;
    utype02: string;
    usrid03: string;
    fname03: string;
    lname03: string;
    utype03: string;
    usrid04: string;
    fname04: string;
    lname04: string;
    utype04: string;
    usrid05: string;
    fname05: string;
    lname05: string;
    utype05: string;
    usrid06: string;
    fname06: string;

    lname06: string;

    utype06: string;

    usrid07: string;

    fname07: string;

    lname07: string;

    utype07: string;

    usrid08: string;

    fname08: string;

    lname08: string;

    utype08: string;

    usrid09: string;

    fname09: string;

    lname09: string;

    utype09: string;

    usrid10: string;

    fname10: string;

    lname10: string;

    utype10: string;

    errmsg: string;
  };

  const [formData, setFormData] = useState<formInput>({
    usridin: "",

    sel0001: "",

    sel0002: "",

    sel0003: "",

    sel0004: "",

    sel0005: "",

    sel0006: "",

    sel0007: "",

    sel0008: "",

    sel0009: "",

    sel0010: "",
  });

  const [receivedData, setReceivedData] = useState<formOutput>({
    cousr00: "",

    cousr0a: "",

    trnname: "CU00",

    title01: "",

    curdate: "mm/dd/yy",

    pgmname: "COUSR00",

    title02: "",

    curtime: "hh:mm:ss",

    pagenum: " ",

    usrid01: " ",

    fname01: " ",

    lname01: " ",

    utype01: " ",

    usrid02: " ",

    fname02: " ",

    lname02: " ",

    utype02: " ",

    usrid03: " ",

    fname03: " ",

    lname03: " ",

    utype03: " ",

    usrid04: " ",

    fname04: " ",

    lname04: " ",

    utype04: " ",

    usrid05: " ",

    fname05: " ",

    lname05: " ",

    utype05: " ",

    usrid06: " ",

    fname06: " ",

    lname06: " ",

    utype06: " ",

    usrid07: " ",

    fname07: " ",

    lname07: " ",

    utype07: " ",

    usrid08: " ",

    fname08: " ",

    lname08: " ",

    utype08: " ",

    usrid09: " ",

    fname09: " ",

    lname09: " ",

    utype09: " ",

    usrid10: " ",

    fname10: " ",

    lname10: " ",

    utype10: " ",

    errmsg: "",
  });

  // const [userData, setUserData] = useState<User[]>([]);

  const [currentPage, setCurrentPage] = useState(1);
  const [selSelect, setselSelect] = useState<string>("");
  const [idSelect, setidSelect] = useState<string>("");

  //const [value, setValue] = useState("");
  const handleInputChange = async (e: ChangeEvent<HTMLInputElement>) => {
    setFormData((state) => {
      return {
        ...state,

        [e.target.name]: e.target.value,
      };
    });
  };

  useEffect(() => {
    const handleKeyDown = (event) => {
      switch (event.key) {
        case "F3":
          event.preventDefault();
          navigate("/COADM01");
          break;

        default:
          break;
      }
    };

    document.addEventListener("keydown", handleKeyDown);
    return () => {
      document.removeEventListener("keydown", handleKeyDown);
    };
  }, []);

  const F8Click = () => {
    initData();
    setCurrentPage(currentPage + 1);
    setReceivedData((pre) => ({
      ...pre,
      errmsg: "",
    }));
    // console.log(currentPage);
    // handlePage(currentPage + 1);
  };

  const handleFormData = () => {
    const setFormDataWithUsridin = (selValue, usridValue) => {
      // setValue(selValue);
      setFormData((prev) => ({
        ...prev,
        usridin: usridValue,
      }));
    };

    switch (true) {
      case formData.sel0001.trim().length > 0:
        setFormDataWithUsridin(formData.sel0001, receivedData.usrid01);
        setselSelect(formData.sel0001);
        setidSelect(receivedData.usrid01);
        break;
      case formData.sel0002.trim().length > 0:
        setFormDataWithUsridin(formData.sel0002, receivedData.usrid02);
        setselSelect(formData.sel0002);
        setidSelect(receivedData.usrid02);
        break;
      case formData.sel0003.trim().length > 0:
        setFormDataWithUsridin(formData.sel0003, receivedData.usrid03);
        setselSelect(formData.sel0003);
        setidSelect(receivedData.usrid03);
        break;
      case formData.sel0004.trim().length > 0:
        setFormDataWithUsridin(formData.sel0004, receivedData.usrid04);
        setselSelect(formData.sel0004);
        setidSelect(receivedData.usrid04);
        break;
      case formData.sel0005.trim().length > 0:
        setFormDataWithUsridin(formData.sel0005, receivedData.usrid05);
        setselSelect(formData.sel0005);
        setidSelect(receivedData.usrid05);
        break;
      case formData.sel0006.trim().length > 0:
        setFormDataWithUsridin(formData.sel0006, receivedData.usrid06);
        setselSelect(formData.sel0006);
        setidSelect(receivedData.usrid06);
        break;
      case formData.sel0007.trim().length > 0:
        setFormDataWithUsridin(formData.sel0007, receivedData.usrid07);
        setselSelect(formData.sel0007);
        setidSelect(receivedData.usrid07);
        break;
      case formData.sel0008.trim().length > 0:
        setFormDataWithUsridin(formData.sel0008, receivedData.usrid08);
        setselSelect(formData.sel0008);
        setidSelect(receivedData.usrid08);
        break;
      case formData.sel0009.trim().length > 0:
        setFormDataWithUsridin(formData.sel0009, receivedData.usrid09);
        setselSelect(formData.sel0009);
        setidSelect(receivedData.usrid09);
        break;
      case formData.sel0010.trim().length > 0:
        setFormDataWithUsridin(formData.sel0010, receivedData.usrid10);
        setselSelect(formData.sel0010);
        setidSelect(receivedData.usrid10);
        break;
      default:
        setFormData((prev) => ({
          ...prev,
          usridin: "",
        }));
    }
  };

  // const dispatch = useDispatch()

  const navigate = useNavigate();
  useEffect(() => {
    const handleKeyDown = (event) => {
      if (event.key === "F7") {
        event.preventDefault();
        if (currentPage > 1) {
          initData();
          handlePage(currentPage - 1);
          setReceivedData((pre) => ({
            ...pre,
            errmsg: "",
          }));
        } else {
          //console.log("sss", currentPage);
          setReceivedData((pre) => ({
            ...pre,
            errmsg: "You are already at the top of the page...",
          }));
        }
      }
      if (event.key === "F8") {
        event.preventDefault();
        F8Click();
      }
    };

    document.addEventListener("keydown", handleKeyDown);

    return () => {
      document.removeEventListener("keydown", handleKeyDown);
    };
  }, [currentPage]);
  const handleSubmit = async (event: KeyboardEvent<HTMLInputElement>) => {
    if (event.key === "Enter") {
      // console.log(usridin);
      // handleFormData
      //console.log(formData.usridin);
      handleFormData();
      if (selSelect.trim() !== "" && idSelect.trim() !== "") {
        switch (selSelect.toLowerCase()) {
          case "u":
            navigate("/COUSR02", {
              state: { usridin: formData.usridin, fromCOUSR00: true },
            });
            break;
          case "d":
            navigate("/COUSR03", {
              state: { usridin: formData.usridin, fromCOUSR00: true },
            });
            break;
          default:
            //  receivedData.errmsg = "Can't commit other";
            setReceivedData((prev) => {
              return {
                ...prev,
                errmsg: "Invalid selection. Valid values are U and D",
              };
            });
            setFormData((prev) => ({
              ...prev,
              usridin: "",
            }));
            break;
        }

        //handleFormData();
      }
    }
  };

  //console.log(formData.usridin);

  // Hàm lấy giờ hiện tại theo định dạng HH:MM:SS

  const getCurrentTime = (): string => {
    const now = new Date();

    return now.toLocaleTimeString("en-US", { hour12: false });
  };

  // Hàm lấy ngày hiện tại theo định dạng MM/DD/YY

  const getCurrentDate = (): string => {
    const now = new Date();

    const month = String(now.getMonth() + 1).padStart(2, "0"); // Lấy tháng (0-based index)

    const day = String(now.getDate()).padStart(2, "0");

    const year = String(now.getFullYear()).slice(-2); // Lấy 2 số cuối của năm

    return `${month}/${day}/${year}`;
  };

  // useEffect để cập nhật ngày và giờ khi component mount

  useEffect(() => {
    // Cập nhật ngày và giờ ngay khi component render

    setReceivedData((prev) => ({
      ...prev,

      curtime: getCurrentTime(),

      curdate: getCurrentDate(),
    }));

    // Thiết lập interval để cập nhật giờ mỗi giây

    const interval = setInterval(() => {
      setReceivedData((prev) => ({
        ...prev,

        curtime: getCurrentTime(),
      }));
    }, 1000); // Cập nhật mỗi giây

    return () => clearInterval(interval); // Xóa interval khi unmount
  }, []);

  useEffect(() => {
    handleFormData();
  }, [
    formData.sel0001,

    formData.sel0002,

    formData.sel0003,

    formData.sel0004,

    formData.sel0005,

    formData.sel0006,

    formData.sel0007,

    formData.sel0008,

    formData.sel0009,

    formData.sel0010,
  ]);
  const initData = async () => {
    clearAllInputs();
   // console.log("sssssssssssssssssssss")
    setFormData((prev) => ({
      
      usridin: "",

      sel0001: "",

      sel0002: "",

      sel0003: "",

      sel0004: "",

      sel0005: "",

      sel0006: "",

      sel0007: "",

      sel0008: "",

      sel0009: "",

      sel0010: "",
    }));
    //console.log(formData.sel0001)
  };

  const fetchData = async (page) => {
    // Xóa dữ liệu cũ trong localStorage
   
    // localStorage.removeItem('formData');

    try {
      const response = await fetch(
        `${httpConfig.domain}/sec-user-data/${page}`
      );
      const apiData = await response.json();
      //console.log(" page", page);
      //console.log(" api", apiData.data.length);
      // const transformedData = transformData(apiData);

      setReceivedData((prevState) => ({
        ...prevState,

        pagenum: currentPage.toString(),

        usrid01: apiData.data[0]?.secUsrId || " ",

        fname01: apiData.data[0]?.secUsrFname.trim() || " ",

        lname01: apiData.data[0]?.secUsrLname.trim() || " ",

        utype01: apiData.data[0]?.secUsrType || " ",

        usrid02: apiData.data[1]?.secUsrId || " ",

        fname02: apiData.data[1]?.secUsrFname.trim() || " ",

        lname02: apiData.data[1]?.secUsrLname.trim() || " ",

        utype02: apiData.data[1]?.secUsrType || " ",

        usrid03: apiData.data[2]?.secUsrId || " ",

        fname03: apiData.data[2]?.secUsrFname.trim() || " ",

        lname03: apiData.data[2]?.secUsrLname.trim() || " ",

        utype03: apiData.data[2]?.secUsrType || " ",

        usrid04: apiData.data[3]?.secUsrId || " ",

        fname04: apiData.data[3]?.secUsrFname.trim() || " ",

        lname04: apiData.data[3]?.secUsrLname.trim() || " ",

        utype04: apiData.data[3]?.secUsrType || " ",

        usrid05: apiData.data[4]?.secUsrId || " ",

        fname05: apiData.data[4]?.secUsrFname.trim() || " ",

        lname05: apiData.data[4]?.secUsrLname.trim() || " ",

        utype05: apiData.data[4]?.secUsrType || " ",

        usrid06: apiData.data[5]?.secUsrId || " ",

        fname06: apiData.data[5]?.secUsrFname.trim() || " ",

        lname06: apiData.data[5]?.secUsrLname.trim() || " ",

        utype06: apiData.data[5]?.secUsrType || " ",

        usrid07: apiData.data[6]?.secUsrId || " ",

        fname07: apiData.data[6]?.secUsrFname.trim() || " ",

        lname07: apiData.data[6]?.secUsrLname.trim() || " ",

        utype07: apiData.data[6]?.secUsrType || " ",

        usrid08: apiData.data[7]?.secUsrId || " ",

        fname08: apiData.data[7]?.secUsrFname.trim() || " ",

        lname08: apiData.data[7]?.secUsrLname.trim() || " ",

        utype08: apiData.data[7]?.secUsrType || " ",

        usrid09: apiData.data[8]?.secUsrId || " ",

        fname09: apiData.data[8]?.secUsrFname.trim() || " ",

        lname09: apiData.data[8]?.secUsrLname.trim() || " ",

        utype09: apiData.data[8]?.secUsrType || " ",

        usrid10: apiData.data[9]?.secUsrId || " ",

        fname10: apiData.data[9]?.secUsrFname.trim() || " ",

        lname10: apiData.data[9]?.secUsrLname.trim() || " ",

        utype10: apiData.data[9]?.secUsrType || " ",

        // errmsg: "",
      }));
      //console.log(" api", apiData.data);
      // console.log(" api", apiData.data.length);
      if (apiData.data.length == 0) {
        setCurrentPage(currentPage - 1);

        // console.log(currentPage);

        setReceivedData((prevState) => ({
          ...prevState,

          errmsg: apiData.message,
        }));

        // localStorage.setItem("formData", JSON.stringify(transformedData));

        // setReceivedData(transformedData);
      }
    } catch (error) {
      console.error("Error fetching data:", error);
    }

    // console.log(receivedData.errmsg);
  };
  const inputRefs = useRef(Array.from({ length: 11 }, () => React.createRef<HTMLInputElement>()));

  const clearAllInputs = () => {
    inputRefs.current.forEach(ref => {
      if (ref.current) {
        ref.current.value = '';
      }
    });
  };
  // const clearInput = () => {
  //   if (inputRef.current) {
  //       inputRef.current.value = '';
  //   }
  // };
  useEffect(() => {
    fetchData(currentPage);
  }, [currentPage]);

  const handlePage = (newPage) => {
    setCurrentPage(newPage);
  };

  return (
    <>
      <Helmet>
        <title>COUSR00</title>
      </Helmet>

      <GridItem col={1} row={1}>
        <pre style={{ color: "#7faded" }}>Tran:</pre>
      </GridItem>

      <GridItem col={7} row={1}>
        <pre style={{ color: "#7faded" }}>{receivedData.trnname}</pre>
      </GridItem>

      <GridItem col={21} row={1}>
        <pre style={{ color: "yellow" }}>{receivedData.title01}</pre>
      </GridItem>

      <GridItem col={65} row={1}>
        <pre style={{ color: "#7faded" }}>Date:</pre>
      </GridItem>

      <GridItem col={71} row={1}>
        <pre style={{ color: "#7faded" }}>{receivedData.curdate}</pre>
      </GridItem>

      <GridItem col={1} row={2}>
        <pre style={{ color: "#7faded" }}>Prog:</pre>
      </GridItem>

      <GridItem col={7} row={2}>
        <pre style={{ color: "#7faded" }}>{receivedData.pgmname}</pre>
      </GridItem>

      <GridItem col={21} row={2}>
        <pre style={{ color: "yellow" }}>{receivedData.title02}</pre>
      </GridItem>

      <GridItem col={65} row={2}>
        <pre style={{ color: "#7faded" }}>Time:</pre>
      </GridItem>

      <GridItem col={71} row={2}>
        <pre style={{ color: "#7faded" }}>{receivedData.curtime}</pre>
      </GridItem>

      <GridItem col={35} row={4}>
        <pre style={{ color: "neutral" }}>List Users</pre>
      </GridItem>

      <GridItem col={65} row={4}>
        <pre style={{ color: "turquoise" }}>Page:</pre>
      </GridItem>

      <GridItem col={71} row={4}>
        <pre style={{ color: "#7faded" }}>{receivedData.pagenum}</pre>
      </GridItem>

      <GridItem col={5} row={6}>
        <pre style={{ color: "turquoise" }}>Search User ID:</pre>
      </GridItem>

      <GridItem col={21} row={6}>
        <input
          autoFocus
          maxLength={8}
          className="bms underLine"
          name="usridin"
          id="usridin"
          type="text"
          autoComplete="off"
          style={{ color: "green", width: "80px" }}
          // onChange={handleInputChange}

          onKeyDown={handleSubmit}
          value={formData.usridin}
        />
      </GridItem>

      <GridItem col={30} row={6}>
        <pre></pre>
      </GridItem>

      <GridItem col={5} row={8}>
        <pre style={{ color: "neutral" }}>Sel</pre>
      </GridItem>

      <GridItem col={12} row={8}>
        <pre style={{ color: "neutral" }}>User ID</pre>
      </GridItem>

      <GridItem col={24} row={8}>
        <pre style={{ color: "neutral" }}>First Name</pre>
      </GridItem>

      <GridItem col={48} row={8}>
        <pre style={{ color: "neutral" }}>Last Name</pre>
      </GridItem>

      <GridItem col={72} row={8}>
        <pre style={{ color: "neutral" }}>Type</pre>
      </GridItem>

      <GridItem col={5} row={9}>
        <pre style={{ color: "neutral" }}>---</pre>
      </GridItem>

      <GridItem col={12} row={9}>
        <pre style={{ color: "neutral" }}>--------</pre>
      </GridItem>

      <GridItem col={24} row={9}>
        <pre style={{ color: "neutral" }}>--------------------</pre>
      </GridItem>

      <GridItem col={48} row={9}>
        <pre style={{ color: "neutral" }}>--------------------</pre>
      </GridItem>

      <GridItem col={72} row={9}>
        <pre style={{ color: "neutral" }}>----</pre>
      </GridItem>

      <GridItem col={6} row={10}>
        <input
          maxLength={1}
          ref={inputRefs.current[1]}
          className="bms underLine"
          name="sel0001"
          id="sel0001"
          type="text"
          autoComplete="off"
          style={{ color: "green", width: "10px" }}
          onChange={handleInputChange}
          onKeyDown={handleSubmit}
        />
      </GridItem>

      <GridItem col={8} row={10}>
        <pre></pre>
      </GridItem>

      <GridItem col={12} row={10}>
        <pre style={{ color: "#7faded" }}>{receivedData.usrid01}</pre>
      </GridItem>

      <GridItem col={24} row={10}>
        <pre style={{ color: "#7faded" }}>{receivedData.fname01}</pre>
      </GridItem>

      <GridItem col={48} row={10}>
        <pre style={{ color: "#7faded" }}>{receivedData.lname01}</pre>
      </GridItem>

      <GridItem col={73} row={10}>
        <pre style={{ color: "#7faded" }}>{receivedData.utype01}</pre>
      </GridItem>

      <GridItem col={6} row={11}>
        <input
          maxLength={1}
          className="bms underLine"
          name="sel0002"
          ref={inputRefs.current[2]}
          id="sel0002"
          type="text"
          autoComplete="off"
          style={{ color: "green", width: "10px" }}
          onChange={handleInputChange}
          onKeyDown={handleSubmit}
        />
      </GridItem>

      <GridItem col={8} row={11}>
        <pre></pre>
      </GridItem>

      <GridItem col={12} row={11}>
        <pre style={{ color: "#7faded" }}>{receivedData.usrid02}</pre>
      </GridItem>

      <GridItem col={24} row={11}>
        <pre style={{ color: "#7faded" }}>{receivedData.fname02}</pre>
      </GridItem>

      <GridItem col={48} row={11}>
        <pre style={{ color: "#7faded" }}>{receivedData.lname02}</pre>
      </GridItem>

      <GridItem col={73} row={11}>
        <pre style={{ color: "#7faded" }}>{receivedData.utype02}</pre>
      </GridItem>

      <GridItem col={6} row={12}>
        <input
          maxLength={1}
          className="bms underLine"
          ref={inputRefs.current[3]}
          name="sel0003"
          id="sel0003"
          type="text"
          autoComplete="off"
          style={{ color: "green", width: "10px" }}
          onChange={handleInputChange}
          onKeyDown={handleSubmit}
        />
      </GridItem>

      <GridItem col={8} row={12}>
        <pre></pre>
      </GridItem>

      <GridItem col={12} row={12}>
        <pre style={{ color: "#7faded" }}>{receivedData.usrid03}</pre>
      </GridItem>

      <GridItem col={24} row={12}>
        <pre style={{ color: "#7faded" }}>{receivedData.fname03}</pre>
      </GridItem>

      <GridItem col={48} row={12}>
        <pre style={{ color: "#7faded" }}>{receivedData.lname03}</pre>
      </GridItem>

      <GridItem col={73} row={12}>
        <pre style={{ color: "#7faded" }}>{receivedData.utype03}</pre>
      </GridItem>

      <GridItem col={6} row={13}>
        <input
          maxLength={1}
          className="bms underLine"
          ref={inputRefs.current[4]}
          name="sel0004"
          id="sel0004"
          type="text"
          autoComplete="off"
          style={{ color: "green", width: "10px" }}
          onChange={handleInputChange}
          onKeyDown={handleSubmit}
        />
      </GridItem>

      <GridItem col={8} row={13}>
        <pre></pre>
      </GridItem>

      <GridItem col={12} row={13}>
        <pre style={{ color: "#7faded" }}>{receivedData.usrid04}</pre>
      </GridItem>

      <GridItem col={24} row={13}>
        <pre style={{ color: "#7faded" }}>{receivedData.fname04}</pre>
      </GridItem>

      <GridItem col={48} row={13}>
        <pre style={{ color: "#7faded" }}>{receivedData.lname04}</pre>
      </GridItem>

      <GridItem col={73} row={13}>
        <pre style={{ color: "#7faded" }}>{receivedData.utype04}</pre>
      </GridItem>

      <GridItem col={6} row={14}>
        <input
          maxLength={1}
          className="bms underLine"
          ref={inputRefs.current[5]}
          name="sel0005"
          id="sel0005"
          type="text"
          autoComplete="off"
          style={{ color: "green", width: "10px" }}
          onChange={handleInputChange}
          onKeyDown={handleSubmit}
        />
      </GridItem>

      <GridItem col={8} row={14}>
        <pre></pre>
      </GridItem>

      <GridItem col={12} row={14}>
        <pre style={{ color: "#7faded" }}>{receivedData.usrid05}</pre>
      </GridItem>

      <GridItem col={24} row={14}>
        <pre style={{ color: "#7faded" }}>{receivedData.fname05}</pre>
      </GridItem>

      <GridItem col={48} row={14}>
        <pre style={{ color: "#7faded" }}>{receivedData.lname05}</pre>
      </GridItem>

      <GridItem col={73} row={14}>
        <pre style={{ color: "#7faded" }}>{receivedData.utype05}</pre>
      </GridItem>

      <GridItem col={6} row={15}>
        <input
          maxLength={1}
          className="bms underLine"
          ref={inputRefs.current[6]}
          name="sel0006"
          id="sel0006"
          type="text"
          autoComplete="off"
          style={{ color: "green", width: "10px" }}
          onChange={handleInputChange}
          onKeyDown={handleSubmit}
        />
      </GridItem>

      <GridItem col={8} row={15}>
        <pre></pre>
      </GridItem>

      <GridItem col={12} row={15}>
        <pre style={{ color: "#7faded" }}>{receivedData.usrid06}</pre>
      </GridItem>

      <GridItem col={24} row={15}>
        <pre style={{ color: "#7faded" }}>{receivedData.fname06}</pre>
      </GridItem>

      <GridItem col={48} row={15}>
        <pre style={{ color: "#7faded" }}>{receivedData.lname06}</pre>
      </GridItem>

      <GridItem col={73} row={15}>
        <pre style={{ color: "#7faded" }}>{receivedData.utype06}</pre>
      </GridItem>

      <GridItem col={6} row={16}>
        <input
          maxLength={1}
          className="bms underLine"
          ref={inputRefs.current[7]}
          name="sel0007"
          id="sel0007"
          type="text"
          autoComplete="off"
          style={{ color: "green", width: "10px" }}
          onChange={handleInputChange}
          onKeyDown={handleSubmit}
        />
      </GridItem>

      <GridItem col={8} row={16}>
        <pre></pre>
      </GridItem>

      <GridItem col={12} row={16}>
        <pre style={{ color: "#7faded" }}>{receivedData.usrid07}</pre>
      </GridItem>

      <GridItem col={24} row={16}>
        <pre style={{ color: "#7faded" }}>{receivedData.fname07}</pre>
      </GridItem>

      <GridItem col={48} row={16}>
        <pre style={{ color: "#7faded" }}>{receivedData.lname07}</pre>
      </GridItem>

      <GridItem col={73} row={16}>
        <pre style={{ color: "#7faded" }}>{receivedData.utype07}</pre>
      </GridItem>

      <GridItem col={6} row={17}>
        <input
          maxLength={1}
          className="bms underLine"
          ref={inputRefs.current[8]}
          name="sel0008"
          id="sel0008"
          type="text"
          autoComplete="off"
          style={{ color: "green", width: "10px" }}
          onChange={handleInputChange}
          onKeyDown={handleSubmit}
        />
      </GridItem>

      <GridItem col={8} row={17}>
        <pre></pre>
      </GridItem>

      <GridItem col={12} row={17}>
        <pre style={{ color: "#7faded" }}>{receivedData.usrid08}</pre>
      </GridItem>

      <GridItem col={24} row={17}>
        <pre style={{ color: "#7faded" }}>{receivedData.fname08}</pre>
      </GridItem>

      <GridItem col={48} row={17}>
        <pre style={{ color: "#7faded" }}>{receivedData.lname08}</pre>
      </GridItem>

      <GridItem col={73} row={17}>
        <pre style={{ color: "#7faded" }}>{receivedData.utype08}</pre>
      </GridItem>

      <GridItem col={6} row={18}>
        <input
          maxLength={1}
          className="bms underLine"
          ref={inputRefs.current[9]}
          name="sel0009"
          id="sel0009"
          type="text"
          autoComplete="off"
          style={{ color: "green", width: "10px" }}
          onChange={handleInputChange}
          onKeyDown={handleSubmit}
        />
      </GridItem>

      <GridItem col={8} row={18}>
        <pre></pre>
      </GridItem>

      <GridItem col={12} row={18}>
        <pre style={{ color: "#7faded" }}>{receivedData.usrid09}</pre>
      </GridItem>

      <GridItem col={24} row={18}>
        <pre style={{ color: "#7faded" }}>{receivedData.fname09}</pre>
      </GridItem>

      <GridItem col={48} row={18}>
        <pre style={{ color: "#7faded" }}>{receivedData.lname09}</pre>
      </GridItem>

      <GridItem col={73} row={18}>
        <pre style={{ color: "#7faded" }}>{receivedData.utype09}</pre>
      </GridItem>

      <GridItem col={6} row={19}>
        <input
          maxLength={1}
          className="bms underLine"
          ref={inputRefs.current[10]}
          name="sel0010"
          id="sel0010"
          type="text"
          autoComplete="off"
          style={{ color: "green", width: "10px" }}
          onChange={handleInputChange}
          onKeyDown={handleSubmit}
        />
      </GridItem>

      <GridItem col={8} row={19}>
        <pre></pre>
      </GridItem>

      <GridItem col={12} row={19}>
        <pre style={{ color: "#7faded" }}>{receivedData.usrid10}</pre>
      </GridItem>

      <GridItem col={24} row={19}>
        <pre style={{ color: "#7faded" }}>{receivedData.fname10}</pre>
      </GridItem>

      <GridItem col={48} row={19}>
        <pre style={{ color: "#7faded" }}>{receivedData.lname10}</pre>
      </GridItem>

      <GridItem col={73} row={19}>
        <pre style={{ color: "#7faded" }}>{receivedData.utype10}</pre>
      </GridItem>

      <GridItem col={12} row={21}>
        <pre style={{ color: "neutral" }}>Type</pre>
      </GridItem>

      <GridItem col={1} row={23}>
        <pre style={{ color: "red" }}>{receivedData.errmsg}</pre>
      </GridItem>

      <GridItem col={1} row={24}>
        <pre style={{ color: "yellow" }}>
          ENTER=Continue F3=Back F7=Backward F8=Forward
        </pre>
      </GridItem>
    </>
  );
}
>>>>>>> local
