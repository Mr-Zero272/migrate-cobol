import { useEffect, useState } from 'react';
import { GridItem } from '../../../components/GridSystem';
import { dateFormat, timeFormat } from '../../../utils/dateTimeFormat';
import httpConfig from '../../../config/httpConfig';
import axios from 'axios';
import { useSelector } from 'react-redux';
import { RootState } from '../../../features/store';
import { useParams } from 'react-router-dom';

type UserDetailsParams = {
  username?: string;
};

type UserDTO = {
  firstName: '';
  lastName: '';
  username: '';
};

export default function Details() {
  const token = useSelector((state: RootState) => state.token);
  const { username: usernameParam } = useParams<UserDetailsParams>();
  const [currentUser, setCurrentUser] = useState<UserDTO>({
    firstName: '',
    lastName: '',
    username: '',
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
      const response = await axios(
        httpConfig.domain + httpConfig.resources.user + '/' + usernameParam,
        {
          headers: {
            Authorization: token.token,
          },
        }
      );

      if (response.status === 200) {
        setCurrentUser(response.data.data);
      }
    };

    if (usernameParam) {
      fetchCurrentUser();
    }
  }, []);

  return (
    <>
      <GridItem row={2} col={1}>
        <p>IDM0190</p>
      </GridItem>
      <GridItem row={2} col={31}>
        <p>{'<< Details Screen >>'}</p>
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
        <p>Username</p>
      </GridItem>
      <GridItem row={4} col={18}>
        <p>: {currentUser.username}</p>
      </GridItem>

      <GridItem row={5} col={5}>
        <p>First name</p>
      </GridItem>
      <GridItem row={5} col={18}>
      <p>: {currentUser.firstName}</p>
      </GridItem>

      <GridItem row={6} col={5}>
        <p>Last name</p>
      </GridItem>
      <GridItem row={6} col={18}>
      <p>: {currentUser.lastName}</p>
      </GridItem>

      <GridItem row={23} col={1} lineBreak>
        <></>
      </GridItem>
    </>
  );
}
