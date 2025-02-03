import { faHouse, faKey, faUser } from '@fortawesome/free-solid-svg-icons';
import classNames from 'classnames/bind';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

import Button from '../../../components/Button';
import routeConfig from '../../../config/routeConfig';
import { type RootState } from '../../../features/store';
import { resetState } from '../../../features/token/tokenSlices';
import styles from './Header.module.css';

const cx = classNames.bind(styles);
export default function Header() {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const theme = useSelector((state: RootState) => state.theme);
  const token = useSelector((state: RootState) => state.token);

  const handleLogout = () => {
    dispatch(resetState());
    navigate(routeConfig.login);
  };

  return (
    <header
      className={cx(
        'wrapper',
        `header-bg-${theme.name}`,
      )}
    >
      <div className={cx('actions')}>
        <Button
          to={routeConfig.home}
          text='Home'
          size='small'
          icon={faHouse}
          iconTextSpace='medium'
        ></Button>
        <Button
          to={'/account'}
          text='Account'
          size='small'
          icon={faUser}
          iconTextSpace='medium'
        ></Button>
        {token.token ? (
          <Button onClick={handleLogout} text='Logout' size='small'></Button>
        ) : (
          <Button
            icon={faKey}
            iconTextSpace='medium'
            to={routeConfig.login}
            text='Login'
            size='small'
          ></Button>
        )}
      </div>
    </header>
  );
}
