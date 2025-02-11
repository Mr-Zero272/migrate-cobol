import { useState, useEffect } from 'react';
import { Helmet } from 'react-helmet';
import axios from 'axios';
import httpConfig from '../../config/httpConfig';

import { GridItem } from '../../components/GridSystem';
import { useNavigate } from 'react-router-dom';

export default function COUSR01() {

    // Định nghĩa kiểu dữ liệu cho input form
    type formInput = {
        secUsrFname: string,  // First Name
        secUsrLname: string,  // Last Name
        secUsrId: string,     // User ID (8 ký tự)
        secUsrPwd: string,    // Password (8 ký tự)
        secUsrType: string,   // Loại User (Admin/User)
    }

    // Định nghĩa kiểu dữ liệu cho output từ server
    type formOutput = {
        cousr01: string,
        cousr1a: string,
        trnname: string,
        title01: string,
        curdate: string,   // Ngày hiện tại (MM/DD/YY)
        pgmname: string,
        title02: string,
        curtime: string,  // Giờ hiện tại (HH:MM:SS)
        errmsg: string,   // Thông báo lỗi (nếu có)
    }

    // State lưu trữ dữ liệu input từ form
    const [formData, setFormData] = useState<formInput>(
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
            trnname: '',
            title01: '',
            curdate: 'mm/dd/yy',   // Mặc định là giá trị placeholder
            pgmname: '',
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
    };

    // Hàm xử lý khi submit form
    const handleSubmit = async (data: formInput) => {
        console.log("object" + data);

        // Kiểm tra validation trước khi gửi request
        if (!data.secUsrId) {
            return setError('User ID can NOT be empty...');
        }

        if (data.secUsrId.length !== 8) {
            return setError('User ID must be exactly 8 characters');
        }

        if (!data.secUsrFname) return setError('First Name can NOT be empty...');

        if (!data.secUsrLname) return setError('Last Name can NOT be empty...');

        if (!data.secUsrPwd) {
            return setError('Password can NOT be empty...');
        }

        if (data.secUsrPwd.length !== 8) {
            return setError('Password must be exactly 8 characters');
        }

        if (!data.secUsrType) return setError('User Type can NOT be empty...');

        try {
            // Gửi request API tạo user
            const response = await axios.post(
                `${httpConfig.domain}/sec-user-data`,
                data
            );

            // Cập nhật thông báo từ server (thành công)
            setError(response?.data.message);
            setIsError(false);
            console.log(response);
        } catch (error) {
            // Cập nhật thông báo lỗi từ server (nếu có lỗi)
            setError((error as any)?.response?.data.message);
            setIsError(true);
            console.error('Error fetching user:', error);
        }
    };

    // useEffect để lắng nghe sự kiện nhấn phím "Enter"
    useEffect(() => {
        const handleKeyDown = (event) => {
            switch (event.key) {
                case 'Enter':
                    event.preventDefault();
                    handleSubmit(formData);
                    console.log("formData" + formData);
                    break;
                default:
                    break;
            }
        };

        // Đăng ký sự kiện bàn phím
        document.addEventListener('keydown', handleKeyDown);

        // Cleanup: Gỡ bỏ sự kiện khi component unmount
        return () => {
            document.removeEventListener('keydown', handleKeyDown);
        };
    }, [formData]);

    return (
        <>
            <Helmet>
                <title>COUSR01</title>
            </Helmet>

            {/* Hiển thị thông tin trang */}
            <GridItem col={1} row={1}>
                <pre style={{ color: "#7faded" }}>
                    Tran:
                </pre>
            </GridItem>


            <GridItem col={7} row={1}>
                <pre style={{ color: "#7faded" }}>
                    {receivedData.trnname}
                </pre>
            </GridItem>


            <GridItem col={21} row={1}>
                <pre style={{ color: "yellow" }}>
                    {receivedData.title01}
                </pre>
            </GridItem>


            <GridItem col={65} row={1}>
                <pre style={{ color: "#7faded" }}>
                    Date:
                </pre>
            </GridItem>


            <GridItem col={71} row={1}>
                <pre style={{ color: "#7faded" }}>
                    {receivedData.curdate}
                </pre>
            </GridItem>


            <GridItem col={1} row={2}>
                <pre style={{ color: "#7faded" }}>
                    Prog:
                </pre>
            </GridItem>


            <GridItem col={7} row={2}>
                <pre style={{ color: "#7faded" }}>
                    {receivedData.pgmname}
                </pre>
            </GridItem>


            <GridItem col={21} row={2}>
                <pre style={{ color: "yellow" }}>
                    {receivedData.title02}
                </pre>
            </GridItem>


            <GridItem col={65} row={2}>
                <pre style={{ color: "#7faded" }}>
                    Time:
                </pre>
            </GridItem>


            <GridItem col={71} row={2}>
                <pre style={{ color: "#7faded" }}>
                    {receivedData.curtime}
                </pre>
            </GridItem>


            <GridItem col={35} row={4}>
                <pre style={{ color: "neutral" }}>
                    Add User
                </pre>
            </GridItem>


            <GridItem col={6} row={8}>
                <pre style={{ color: "turquoise" }}>
                    First Name:
                </pre>
            </GridItem>


            <GridItem col={18} row={8}>
                <input maxLength={20} className='bms underLine' name='secUsrFname' id='secUsrFname' type='text' style={{ color: "green" }}
                    onChange={(e) => handleInputChange(e, 'secUsrFname')} />
            </GridItem>

            <GridItem col={39} row={8}>
                <pre >

                </pre>
            </GridItem>


            <GridItem col={45} row={8}>
                <pre style={{ color: "turquoise" }}>
                    Last Name:
                </pre>
            </GridItem>


            <GridItem col={56} row={8}>
                <input maxLength={20} className='bms underLine' name='secUsrLname' id='secUsrLname' type='text' style={{ color: "green" }} onChange={(e) => handleInputChange(e, 'secUsrLname')} />
            </GridItem>

            <GridItem col={77} row={8}>
                <pre style={{ color: "green" }}>

                </pre>
            </GridItem>


            <GridItem col={6} row={11}>
                <pre style={{ color: "turquoise" }}>
                    User ID:
                </pre>
            </GridItem>


            <GridItem col={15} row={11}>
                <input maxLength={8} className='bms underLine' name='secUsrId' id='secUsrId' type='text' style={{ color: "green", width: "35%" }} onChange={(e) => handleInputChange(e, 'secUsrId')} />
            </GridItem>

            <GridItem col={24} row={11}>
                <pre style={{ color: "#7faded" }}>
                    (8 Char)
                </pre>
            </GridItem>


            <GridItem col={45} row={11}>
                <pre style={{ color: "turquoise" }}>
                    Password:
                </pre>
            </GridItem>


            <GridItem col={55} row={11}>
                <input maxLength={8} className='bms underLine' name='secUsrPwd' id='secUsrPwd' type='text' style={{ color: "green", width: "35%" }} onChange={(e) => handleInputChange(e, 'secUsrPwd')} />
            </GridItem>

            <GridItem col={64} row={11}>
                <pre style={{ color: "#7faded" }}>
                    (8 Char)
                </pre>
            </GridItem>


            <GridItem col={6} row={14}>
                <pre style={{ color: "turquoise" }}>
                    User Type:
                </pre>
            </GridItem>


            <GridItem col={17} row={14}>
                <input maxLength={1} className='bms underLine' name='secUsrType' id='secUsrType' type='text' style={{ color: "green", width: "5%" }} onChange={(e) => handleInputChange(e, 'secUsrType')} />
            </GridItem>

            <GridItem col={19} row={14}>
                <pre style={{ color: "#7faded" }}>
                    (A=Admin, U=User)
                </pre>
            </GridItem>


            {/* Hiển thị thông báo lỗi */}
            <GridItem col={1} row={23}>
                <pre style={{ color: isError ? "red" : "green" }}>
                    {error}
                    {/* {receivedData.errmsg} */}
                </pre>
            </GridItem>


            <GridItem col={1} row={24}>
                <pre style={{ color: "yellow" }}>
                    ENTER=Add User  F3=Back  F4=Clear  F12=Exit
                </pre>
            </GridItem>

        </>
    );
}
