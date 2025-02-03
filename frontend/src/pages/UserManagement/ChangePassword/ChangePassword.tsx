import { ChangeEvent, useEffect, useState } from 'react';
import { GridItem } from '../../../components/GridSystem';
import { toast } from 'react-toastify';
import axios from 'axios';

import { useParams } from 'react-router-dom';
import httpConfig from '../../../config/httpConfig';
import { useSelector } from 'react-redux';
import { type RootState } from '../../../features/store';
import Button from '../../../components/Button';

type CreateRouteParams = {
  username?: string;
};

type ChangePasswordDTO = {
  username: '';
  password: '';
};

type ErrorMessage = {
  password: string;
  hasError: boolean;
};

export default function ChangePassword() {
  const token = useSelector((state: RootState) => state.token);
  const { username: usernameParam } = useParams<CreateRouteParams>();
  const [currentUser, setCurrentUser] = useState<ChangePasswordDTO>({
    username: '',
    password: '',
  });
  const [errorMessage, setErrorMessage] = useState<ErrorMessage>({
    password: '',
    hasError: false,
  });

  useEffect(() => {
    const fetchCurrentUser = async () => {
      const response = await axios(
        httpConfig.domain + httpConfig.resources.user + '/' + usernameParam,
        {
          headers: {
            Authorization: token.token,
          },
        }
      );

      if (response.status === 200) {
        setCurrentUser((_state) => ({
          username: response.data.data.username,
          password: response.data.data.password,
        }));
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
    try {
      await axios.put(
        httpConfig.domain + httpConfig.resources.user + '/password',
        { ...currentUser },
        {
          headers: {
            Authorization: token.token,
          },
        }
      );

      toast.success('Update user password successfully');
      setCurrentUser((state) => ({ username: state.username, password: '' }));
    } catch (error: any) {
      if (error.response.status === 406) {
        toast.error('Weak password');
      } else {
        toast.error('Something went wrong!');
      }
      console.log(error);
    }
  };

  return (
    <>
      <GridItem row={2} col={36}>
        <h3>Update password</h3>
      </GridItem>

      <GridItem row={4} col={19}>
        <label htmlFor='username'>Username</label>
      </GridItem>
      <GridItem row={4} col={31}>
        <input
          type='text'
          id='username'
          name='username'
          value={currentUser?.username}
          onChange={handleChange}
          disabled
        />
      </GridItem>

      <GridItem row={5} col={19}>
        <label htmlFor='password'>Password</label>
      </GridItem>
      <GridItem row={5} col={31}>
        <input
          type='password'
          id='password'
          name='password'
          value={currentUser?.password}
          onChange={handleChange}
        />
      </GridItem>
      <GridItem row={5} col={50}>
        <span>{errorMessage.password}</span>
      </GridItem>

      <GridItem row={6} col={31}>
        <Button text='Submit' size='medium' onClick={handleSubmit}></Button>
      </GridItem>
    </>
  );
}
