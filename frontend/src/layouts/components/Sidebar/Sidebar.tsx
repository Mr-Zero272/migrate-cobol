import classNames from 'classnames/bind';

import { useSelector } from 'react-redux';
import SidebarMenu from './SidebarMenu';
import { type RootState } from '../../../features/store';

import styles from './Sidebar.module.css';
import { useState } from 'react';
import sidebarMenuItems from './sidebarMenuItems';

type SidebarProps = {
  classes?: string;
};

const cx = classNames.bind(styles);
export default function Sidebar({ classes }: SidebarProps) {
  const theme = useSelector((state: RootState) => state.theme);
  const token = useSelector((state: RootState) => state.token);
  const [showMenu, setShowMenu] = useState(true);

  const handleToggleSidebar = () => {
    setShowMenu((state) => !state);
  };

  return (
    <div className={cx('wrapper', `bg-secondary-${theme.name}`, `border-${theme.name}`, classes)}>
      <p className={cx('title')} onClick={handleToggleSidebar}>
        Sidebar
      </p>
      {showMenu && <SidebarMenu items={sidebarMenuItems} />}
    </div>
  );
}
