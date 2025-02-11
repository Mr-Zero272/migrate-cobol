
import { type ChangeEvent, useState, type KeyboardEvent } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
<<<<<<< refs/remotes/origin/khanglt6
import Input from '../../components/Input';
=======
import { useNavigate } from 'react-router-dom';
>>>>>>> local

export default function COUSR01() {
    
    type formInput = {
        fname: string,
lname: string,
userid: string,
passwd: string,
usrtype: string,

    }

    type formOutput = {
        cousr01: string,
cousr1a: string,
trnname: string,
title01: string,
curdate: string,
pgmname: string,
title02: string,
curtime: string,
errmsg: string,

    }
    
    const [formData, setFormData] = useState<formInput>(
<<<<<<< refs/remotes/origin/khanglt6
    {
        fname: '',
lname: '',
userid: '',
passwd: '',
usrtype: '',

    });
    const [receivedData, setReceivedData] = useState<formOutput>(
     {
        cousr01: '',
cousr1a: '',
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
=======
        {
            secUsrFname: '',
            secUsrLname: '',
            secUsrId: '',
            secUsrPwd: '',
            secUsrType: '',
        });

        const navigate = useNavigate();

    // State lưu trữ dữ liệu nhận từ server
    const [receivedData, setReceivedData] = useState<formOutput>(
        {
            cousr01: '',
            cousr1a: '',
            trnname: 'CU01',
            title01: '',
            curdate: 'mm/dd/yy',   // Mặc định là giá trị placeholder
            pgmname: 'COUSR01',
            title02: '',
            curtime: 'hh:mm:ss',   // Mặc định là giá trị placeholder
            errmsg: '',
        });

    // State lưu lỗi và trạng thái lỗi
    const [error, setError] = useState<string>('');
    const [isError, setIsError] = useState(false);

    // Hàm lấy giờ hiện tại theo định dạng HH:MM:SS
    const getCurrentTime = (): string => {
        const now = new Date();
        return now.toLocaleTimeString("en-US", { hour12: false });
    };

        useEffect(() => {
            const handleKeyDown = (event) => {
            switch (event.key) {
                case 'F3':
                event.preventDefault();
                navigate("/COADM01");
                break;
                default:
                break;
            }
            };
            document.addEventListener('keydown', handleKeyDown);
            return () => {
            document.removeEventListener('keydown', handleKeyDown);
            };
        }, []);

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


    // Hàm xử lý sự kiện khi nhập vào input
    const handleInputChange = (
        e: React.ChangeEvent<HTMLInputElement>,
        field: keyof formInput
    ) => {
        setFormData({
            ...formData,
            [field]: e.target.value
        });
>>>>>>> local
    };

    const handleSubmit = async (event: KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') {
        for (const key in formData) {
        if (!formData[key]) {
            return;
        }
        }

        const response = await axios.post(
        httpConfig.domain + '/Cousr01',
        formData
        );

        setReceivedData(_state => response.data);
    }
    };
    
  return (
    <>
     
    <Helmet>
        <title>COUSR01</title>
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
         Add User 
    </pre>
</GridItem>

    
<GridItem col={6} row={8}>
    <pre style={{color:"turquoise"}}>
         First Name: 
    </pre>
</GridItem>

    
<GridItem col={18} row={8}>
    <Input maxLength={20} className='bms underLine' name='fname' id='fname' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={39} row={8}>
    <pre >
          
    </pre>
</GridItem>

    
<GridItem col={45} row={8}>
    <pre style={{color:"turquoise"}}>
         Last Name: 
    </pre>
</GridItem>

    
<GridItem col={56} row={8}>
    <Input maxLength={20} className='bms underLine' name='lname' id='lname' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={77} row={8}>
    <pre style={{color:"green"}}>
          
    </pre>
</GridItem>

    
<GridItem col={6} row={11}>
    <pre style={{color:"turquoise"}}>
         User ID: 
    </pre>
</GridItem>

    
<GridItem col={15} row={11}>
    <Input maxLength={8} className='bms underLine' name='userid' id='userid' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={24} row={11}>
    <pre style={{color:"#7faded"}}>
         (8 Char) 
    </pre>
</GridItem>

    
<GridItem col={45} row={11}>
    <pre style={{color:"turquoise"}}>
         Password: 
    </pre>
</GridItem>

    
<GridItem col={55} row={11}>
    <Input maxLength={8} className='bms underLine' name='passwd' id='passwd' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={64} row={11}>
    <pre style={{color:"#7faded"}}>
         (8 Char) 
    </pre>
</GridItem>

    
<GridItem col={6} row={14}>
    <pre style={{color:"turquoise"}}>
         User Type:  
    </pre>
</GridItem>

    
<GridItem col={17} row={14}>
    <Input maxLength={1} className='bms underLine' name='usrtype' id='usrtype' type='text' styles={{color:"green"}}  onChange={handleInputChange} onKeyDown={handleSubmit}/>
</GridItem>
    
<GridItem col={19} row={14}>
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
         ENTER=Add User  F3=Back  F4=Clear  F12=Exit 
    </pre>
</GridItem>

    
    </>
  );
}
    