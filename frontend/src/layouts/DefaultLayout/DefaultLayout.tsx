import { type ReactNode } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import classNames from 'classnames/bind';
import { faGear, faPaintBrush } from '@fortawesome/free-solid-svg-icons';
import { ToastContainer } from 'react-toastify';

import { type RootState } from '../../features/store';
import Header from './../components/Header';
import Footer from './../components/Footer';
import Sidebar from './../components/Sidebar';
import MainContent from '../components/MainContent';
import Button from '../../components/Button';
import styles from './DefaultLayout.module.css';
import Menu from '../../components/Menu/Menu';
import { MenuItemProps } from '../../components/Menu/MenuItem';
import Toolbar from '../components/Toolbar';
import { changeTheme, type Theme } from '../../features/themes/themesSlices';
import 'react-toastify/dist/ReactToastify.css';

type DefaultLayoutProps = {
  children: ReactNode;
};

const cx = classNames.bind(styles);
export default function DefaultLayout({ children }: DefaultLayoutProps) {
  const dispatch = useDispatch();
  const theme = useSelector((state: RootState) => state.theme);

  const handleClickMenuItems = (item: MenuItemProps) => {
    switch (item.type) {
      case 'theme':
        dispatch(changeTheme(item.code as Theme));
        break;
      default:
        break;
    }
  };

  const menuItems: MenuItemProps[] = [
    {
      text: 'Theme',
      icon: faPaintBrush,
      nestedItems: {
        text: 'Theme',
        data: [
          {
            text: 'Default',
            type: 'theme',
            code: 'default',
          },
          {
            text: 'Mainframe white',
            type: 'theme',
            code: 'mainframe-white',
          },
          {
            text: 'Mainframe green',
            type: 'theme',
            code: 'mainframe-green',
          },
          {
            text: 'Mainframe yellow',
            type: 'theme',
            code: 'mainframe-yellow',
          },
          {
            text: 'Mainframe aqua',
            type: 'theme',
            code: 'mainframe-aqua',
          },
        ],
      },
    },
  ];

  return (
    <div
      className={cx(
        'wrapper',
        `bg-primary-${theme.name}`,
        `text-${theme.name}`,
        theme.name
      )}
    >
      <Header />
      <main className={cx('container')}>
        <Sidebar />
        <div className={cx('content')}>
          <MainContent>{children}</MainContent>
          <Toolbar />
        </div>
        <Menu
          placement='left-end'
          items={menuItems}
          onClick={handleClickMenuItems}
        >
          <Button
            icon={faGear}
            classes={cx('setting-button')}
            size='large'
          ></Button>
        </Menu>
      </main>
      <Footer />
      <ToastContainer
        position='bottom-right'
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme='light'
      />
    </div>
  );
}
