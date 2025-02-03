import { ChangeEvent, useEffect, useState } from 'react';
import { GridItem } from '../../../components/GridSystem';
import { toast } from 'react-toastify';
import axios from 'axios';

import { useParams } from 'react-router-dom';
import httpConfig from '../../../config/httpConfig';
import { useSelector } from 'react-redux';
import { type RootState } from '../../../features/store';
import Button from '../../../components/Button';
import { dateFormat, timeFormat } from '../../../utils/dateTimeFormat';
import Input from '../../../components/Input';

type CreateRouteParams = {
  username?: string;
};

type CreateUserDTO = {
  firstName: '';
  lastName: '';
  username: '';
  password: '';
};

type ErrorMessage = {
  firstName: string;
  lastName: string;
  username: string;
  password: string;
  hasError: boolean;
};

export default function Create() {
  const token = useSelector((state: RootState) => state.token);
  const { username: usernameParam } = useParams<CreateRouteParams>();
  const [currentUser, setCurrentUser] = useState<CreateUserDTO>({
    firstName: '',
    lastName: '',
    username: '',
    password: '',
  });
  const [errorMessage, setErrorMessage] = useState<ErrorMessage>({
    firstName: '',
    lastName: '',
    username: '',
    password: '',
    hasError: false,
  });

  const [currentDateTime, setCurrentDateTime] = useState<Date>(new Date());

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentDateTime(new Date());
    }, 1000);

    return () => {
      clearInterval(timer);
    };
  }, []);

  useEffect(() => {
    const fetchCurrentUser = async () => {
      try {
        const response = await axios(
          httpConfig.domain + httpConfig.resources.user + '/' + usernameParam,
          {
            headers: {
              Authorization: token.token,
            },
          }
        );
        setCurrentUser(response.data.data);
      } catch (error) {
        console.log(error);
      }
    };

    if (usernameParam) {
      fetchCurrentUser();
    }
  }, []);

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const fieldName = e.target.name;
    const value = e.target.value;
    setCurrentUser((state) => ({ ...state, [fieldName]: value }));
  };

  const handleSubmit = async () => {
    let hasError = false;
    for (const key in currentUser) {
      if (usernameParam && key === 'password') {
        continue;
      }
      if (!currentUser[key]) {
        setErrorMessage((state) => ({
          ...state,
          [key]: key + ' is required',
        }));
        hasError = true;
      } else {
        setErrorMessage((state) => ({
          ...state,
          [key]: '',
        }));
      }
    }

    setErrorMessage((state) => ({
      ...state,
      hasError: hasError,
    }));

    if (hasError) {
      return;
    }

    // handle submit ...
    // create case
    if (!usernameParam) {
      let response;
      try {
        response = await axios.post(
          httpConfig.domain + httpConfig.resources.user,
          { ...currentUser },
          {
            headers: {
              Authorization: token.token,
            },
          }
        );

        toast.success('Created user successfully!');
        setCurrentUser((_state) => ({
          firstName: '',
          lastName: '',
          password: '',
          username: '',
        }));
      } catch (error: any) {
        if (error.response) {
          if (error.response.status === 409) {
            toast.error('Username already existed');
          } else if (error.response.status === 406) {
            toast.error('Weak password');
          } else {
            toast.error('Something went wrong!');
          }
        }
      }

      // update case
    } else {
      try {
        const response = await axios.put(
          httpConfig.domain + httpConfig.resources.user,
          { ...currentUser },
          {
            headers: {
              Authorization: token.token,
            },
          }
        );

        toast.success('Update user successfully');
      } catch (error: any) {
        toast.error('Something went wrong!');
        console.log(error);
      }
    }
  };

  return (
    <>
      <GridItem row={2} col={1}>
        <p>IDM0190</p>
      </GridItem>
      <GridItem row={2} col={31}>
        <p>{usernameParam ? '<< Update Screen >>' : '<< Insert Screen >>'}</p>
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

      <GridItem row={4} col={5}>
        <label htmlFor='username'>Username</label>
      </GridItem>
      <GridItem row={4} col={18}>
        <Input
          maxLength={24}
          type='text'
          id='username'
          name='username'
          value={currentUser?.username}
          onChange={handleChange}
          disabled={!!usernameParam}
        />
      </GridItem>
      <GridItem row={4} col={45}>
        {errorMessage.username}
      </GridItem>

      <GridItem row={5} col={5}>
        <label htmlFor='firstName'>First name</label>
      </GridItem>
      <GridItem row={5} col={18}>
        <Input
          type='text'
          id='firstName'
          name='firstName'
          maxLength={24}
          value={currentUser?.firstName}
          onChange={handleChange}
        />
      </GridItem>
      <GridItem row={5} col={45}>
        <span>{errorMessage.firstName}</span>
      </GridItem>

      <GridItem row={6} col={5}>
        <label htmlFor='lastName'>Last name</label>
      </GridItem>
      <GridItem row={6} col={18}>
        <Input
          maxLength={24}
          type='text'
          id='lastName'
          name='lastName'
          value={currentUser?.lastName}
          onChange={handleChange}
        />
      </GridItem>
      <GridItem row={6} col={45}>
        <span>{errorMessage.lastName}</span>
      </GridItem>

      {!usernameParam && (
        <>
          {' '}
          <GridItem row={7} col={5}>
            <label htmlFor='password'>Password</label>
          </GridItem>
          <GridItem row={7} col={18}>
            <Input
              maxLength={24}
              type='password'
              id='password'
              name='password'
              value={currentUser?.password}
              onChange={handleChange}
            />
          </GridItem>
          <GridItem row={7} col={45}>
            <span>{errorMessage.password}</span>
          </GridItem>
        </>
      )}

      <GridItem row={8} col={18}>
        <Button text='Submit' size='medium' onClick={handleSubmit}></Button>
      </GridItem>

      <GridItem row={23} col={1} lineBreak>
        <></>
      </GridItem>
    </>
  );
}
