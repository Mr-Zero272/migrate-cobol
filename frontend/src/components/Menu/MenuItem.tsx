import { type ReactNode } from 'react';
import { type IconDefinition } from '@fortawesome/free-solid-svg-icons';

import styles from './Menu.module.css';
import classNames from 'classnames/bind';
import Button from '../Button';
import { Link } from 'react-router-dom';

export type MenuItemProps = {
  text: string;
  to?: string;
  type?: string;
  code?: string;
  nestedItems?: { text: string; data: MenuItemProps[] };
  icon?: IconDefinition;
  onClick?: () => void;
};

const cx = classNames.bind(styles);
export default function MenuItem({ text, to, icon, onClick }: MenuItemProps) {
  if (to) {
    return (
      <Link className={cx('menu-item')} to={to}>
        {text}
      </Link>
    );
  }

  return (
    <button className={cx('menu-item')} onClick={onClick}>
      {text}
    </button>
  );
}
