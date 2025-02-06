import axios from 'axios';
import { useEffect, useState } from 'react';
import { Helmet } from 'react-helmet';
import { GridItem } from '../../components/GridSystem';
import httpConfig from '../../config/httpConfig';

export default function COUSR02() {
  type formInput = {
    secUsrId: string;
    secUsrFname: string;
    secUsrLname: string;
    secUsrPwd: string;
    secUsrType: string;
  };
  type formOutput = {
    cousr02: string;
    cousr2a: string;
    trnname: string;
    title01: string;
    curdate: string;
    pgmname: string;
    title02: string;
    curtime: string;
    errmsg: string;
  };

  const [userId, setUserId] = useState<string>('');
  const [error, setError] = useState<string>('');
  const [initialData, setInitialData] = useState<formInput | null>(null);
  const [currentTime, setCurrentTime] = useState<string>('');
  const [formData, setFormData] = useState<formInput>({
    secUsrId: '',
    secUsrFname: '',
    secUsrLname: '',
    secUsrPwd: '',
    secUsrType: ''
  });
  const [receivedData, _] = useState<formOutput>({
    cousr02: '',
    cousr2a: '',
    trnname: '',
    title01: '',
    curdate: 'mm/dd/yy',
    pgmname: '',
    title02: '',
    curtime: 'hh:mm:ss',
    errmsg: ''
  });

  const getUserById = async (userId) => {
    try {
      const response = await axios.post(
        `${httpConfig.domain}/sec-user-data/get-by-id`,
        { secUsrId: userId }
      );
      if (response.data.status === 'success') {
        setFormData(response.data.data);
        setInitialData(response.data.data);
        setError('');
      }
    } catch (error) {
      setError((error as any)?.response?.data.message);
      setFormData({
        secUsrId: '',
        secUsrFname: '',
        secUsrLname: '',
        secUsrPwd: '',
        secUsrType: ''
      });
    }
  };

  useEffect(() => {
    const updateCurrentTime = () => {
      const now = new Date();
      const formattedTime = now.toLocaleTimeString('vi-VI');
      setCurrentTime(formattedTime);
    };
    const timer = setInterval(updateCurrentTime, 1000);
    return () => clearInterval(timer);
  }, []);

  const updateUserData = async (data: formInput) => {
    if (!data.secUsrId) return setError('User ID can NOT be empty...');
    if (data.secUsrId.length !== 8)
      return setError('User ID must be exactly 8 characters...');
    if (!data.secUsrFname) return setError('First Name can NOT be empty...');
    if (!data.secUsrLname) return setError('Last Name can NOT be empty...');
    if (!data.secUsrPwd) return setError('Password can NOT be empty...');
    if (data.secUsrPwd.length !== 8)
      return setError('Password must be exactly 8 characters...');
    if (!data.secUsrType) return setError('User Type can NOT be empty...');

    if (JSON.stringify(data) === JSON.stringify(initialData)) {
      return setError('No data changes to update...');
    }

    try {
      await axios.put(`${httpConfig.domain}/sec-user-data`, data);
      setUserId('');
      setFormData({
        secUsrId: '',
        secUsrFname: '',
        secUsrLname: '',
        secUsrPwd: '',
        secUsrType: ''
      });
      setError('');
    } catch (error) {
      setError((error as any)?.response?.data.message);
    }
  };

  const handleKeyInputId = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter' && userId.length === 8) {
      getUserById(userId);
    }
  };

  useEffect(() => {
    const handleKeyDown = (event: KeyboardEvent) => {
      switch (event.key) {
        case 'F4':
          event.preventDefault();
          setUserId('');
          setError('');
          setFormData({
            secUsrId: '',
            secUsrFname: '',
            secUsrLname: '',
            secUsrPwd: '',
            secUsrType: ''
          });
          break;
        case 'F5':
          event.preventDefault();
          updateUserData(formData);
          break;
        default:
          break;
      }
    };
    document.addEventListener('keydown', handleKeyDown);
    return () => {
      document.removeEventListener('keydown', handleKeyDown);
    };
  }, [formData]);

  const handleInputChange = (
    e: React.ChangeEvent<HTMLInputElement>,
    field: keyof formInput
  ) => {
    setFormData({
      ...formData,
      [field]: e.target.value
    });
  };

  return (
    <>
      <Helmet>
        <title>COUSR02</title>
      </Helmet>

      <GridItem
        col={1}
        row={1}
      >
        <pre style={{ color: '#7faded' }}>Tran:</pre>
      </GridItem>

      <GridItem
        col={7}
        row={1}
      >
        <pre style={{ color: '#7faded' }}>{receivedData.trnname}</pre>
      </GridItem>

      <GridItem
        col={21}
        row={1}
      >
        <pre style={{ color: 'yellow' }}>{receivedData.title01}</pre>
      </GridItem>

      <GridItem
        col={65}
        row={1}
      >
        <pre style={{ color: '#7faded' }}>Date:</pre>
      </GridItem>

      <GridItem
        col={71}
        row={1}
      >
        <pre style={{ color: '#7faded' }}>
          {new Date().toLocaleDateString('vi-VN') || receivedData.curdate}
        </pre>
      </GridItem>

      <GridItem
        col={1}
        row={2}
      >
        <pre style={{ color: '#7faded' }}>Prog:</pre>
      </GridItem>

      <GridItem
        col={7}
        row={2}
      >
        <pre style={{ color: '#7faded' }}>{receivedData.pgmname}</pre>
      </GridItem>

      <GridItem
        col={21}
        row={2}
      >
        <pre style={{ color: 'yellow' }}>{receivedData.title02}</pre>
      </GridItem>

      <GridItem
        col={65}
        row={2}
      >
        <pre style={{ color: '#7faded' }}>Time:</pre>
      </GridItem>

      <GridItem
        col={71}
        row={2}
      >
        <pre style={{ color: '#7faded' }}>
          {currentTime || receivedData.curtime}
        </pre>
      </GridItem>

      <GridItem
        col={35}
        row={4}
      >
        <pre style={{ color: 'neutral' }}>Update User</pre>
      </GridItem>

      <GridItem
        col={6}
        row={6}
      >
        <pre style={{ color: 'green' }}>Enter User ID:</pre>
      </GridItem>

      <GridItem
        col={21}
        row={6}
      >
        <input
          type='text'
          maxLength={8}
          className='bms underLine'
          value={userId}
          onKeyDown={handleKeyInputId}
          onChange={(e) => setUserId(e.target.value)}
          style={{ color: 'green', width: '90px' }}
        />
      </GridItem>

      <GridItem
        col={30}
        row={6}
      >
        <pre></pre>
      </GridItem>

      <GridItem
        col={6}
        row={8}
      >
        <pre style={{ color: 'yellow' }}>
          **********************************************************************
        </pre>
      </GridItem>

      <GridItem
        col={6}
        row={11}
      >
        <pre style={{ color: 'turquoise' }}>First Name:</pre>
      </GridItem>

      <GridItem
        col={18}
        row={11}
      >
        <input
          maxLength={20}
          className='bms underLine'
          value={formData.secUsrFname}
          onChange={(e) => handleInputChange(e, 'secUsrFname')}
          type='text'
          style={{ color: 'green' }}
        />
      </GridItem>

      <GridItem
        col={39}
        row={11}
      >
        <pre></pre>
      </GridItem>

      <GridItem
        col={45}
        row={11}
      >
        <pre style={{ color: 'turquoise' }}>Last Name:</pre>
      </GridItem>

      <GridItem
        col={56}
        row={11}
      >
        <input
          maxLength={20}
          className='bms underLine'
          onChange={(e) => handleInputChange(e, 'secUsrLname')}
          value={formData.secUsrLname}
          type='text'
          style={{ color: 'green' }}
        />
      </GridItem>

      <GridItem
        col={77}
        row={11}
      >
        <pre style={{ color: 'green' }}></pre>
      </GridItem>

      <GridItem
        col={6}
        row={13}
      >
        <pre style={{ color: 'turquoise' }}>Password:</pre>
      </GridItem>

      <GridItem
        col={16}
        row={13}
      >
        <input
          maxLength={8}
          className='bms underLine'
          onChange={(e) => handleInputChange(e, 'secUsrPwd')}
          value={formData.secUsrPwd}
          type='text'
          style={{ color: 'green', width: '80px' }}
        />
      </GridItem>

      <GridItem
        col={25}
        row={13}
      >
        <pre style={{ color: '#7faded' }}>(8 Char)</pre>
      </GridItem>

      <GridItem
        col={6}
        row={15}
      >
        <pre style={{ color: 'turquoise' }}>User Type:</pre>
      </GridItem>

      <GridItem
        col={17}
        row={15}
      >
        <input
          maxLength={1}
          className='bms underLine'
          onChange={(e) => handleInputChange(e, 'secUsrType')}
          value={formData.secUsrType}
          type='text'
          style={{ color: 'green', width: '20px' }}
        />
      </GridItem>

      <GridItem
        col={19}
        row={15}
      >
        <pre style={{ color: '#7faded' }}>(A=Admin, U=User)</pre>
      </GridItem>

      <GridItem
        col={1}
        row={23}
      >
        <pre style={{ color: 'red' }}>{error}</pre>
      </GridItem>

      <GridItem
        col={1}
        row={24}
      >
        <pre style={{ color: 'yellow' }}>
          ENTER=Fetch F3=Save && Exit F4=Clear F5=Save F12=Cancel
        </pre>
      </GridItem>
    </>
  );
}