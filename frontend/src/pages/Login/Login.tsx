import { Helmet } from 'react-helmet';

import { useEffect, useState } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import Button from '../../components/Button';
import { GridItem } from '../../components/GridSystem';
import { setRefreshToken } from '../../features/token/refreshTokenSlices';
import { setToken } from '../../features/token/tokenSlices';

import httpConfig from '../../config/httpConfig';
import routeConfig from '../../config/routeConfig';
import { dateFormat, timeFormat } from '../../utils/dateTimeFormat';

export default function Login() {
  const [currentDateTime, setCurrentDateTime] = useState<Date>(new Date());

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentDateTime(new Date());
    }, 1000);

    return () => {
      clearInterval(timer);
    };
  }, []);

  const defaultFormData = {
    username: '',
    password: '',
  };

  const [error, setError] = useState({
    login: '',
  });

  const [formData, setFormData] = useState(defaultFormData);
  const dispatch = useDispatch();

  const navigate = useNavigate();

  const handleLogin = async () => {
    const response = await fetch(httpConfig.domain + httpConfig.auth.signIn, {
      method: 'POST',
      credentials: 'include',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    });

    if (response.ok) {
      const responseData = await response.json();
      dispatch(setToken(responseData.data.token));
      dispatch(setRefreshToken(responseData.data.rf_token));
      navigate(routeConfig.dashboard);
    } else {
      const responseData = await response.json();
      setError({ login: responseData.error });
    }
  };

  return (
    <>
      <Helmet>
        <title>Login</title>
      </Helmet>

      <GridItem row={2} col={1}>
        <p>IDM0190</p>
      </GridItem>
      <GridItem row={2} col={31}>
        <p>{'<< Login Screen >>'}</p>
      </GridItem>
      <GridItem row={1} col={71}>
        <p>
          {dateFormat(currentDateTime)}
          <br />
          {timeFormat(currentDateTime)}
        </p>
      </GridItem>
      <GridItem row={3} col={1} lineBreak>
        <></>
      </GridItem>

      <GridItem row={5} col={22}>
        <label>Username</label>
      </GridItem>
      <GridItem row={5} col={32}>
        <input
          type='text'
          name='username'
          id='username'
          onChange={(x) => (formData.username = x.target.value)}
        />
      </GridItem>

      <GridItem row={6} col={22}>
        <label>Password</label>
      </GridItem>
      <GridItem row={6} col={32}>
        <input
          type='password'
          name='password'
          id='password'
          onChange={(x) => (formData.password = x.target.value)}
        />
      </GridItem>
      <GridItem row={4} col={32}>
        <span>{error.login}</span>
      </GridItem>

      <GridItem row={7} col={32}>
        <Button text='Submit' size='medium' onClick={handleLogin}></Button>
      </GridItem>

      <GridItem row={23} col={1} lineBreak>
        <></>
      </GridItem>
    </>
  );
}
