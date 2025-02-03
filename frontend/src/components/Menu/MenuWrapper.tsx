import { type ReactNode } from 'react';

import styles from './Menu.module.css';
import classNames from 'classnames/bind';

type MenuWrapperProps = {
  classes: string;
  children: ReactNode;
};

const cx = classNames.bind(styles);
export default function MenuWrapper({ children, classes }: MenuWrapperProps) {
  return <div className={cx('menu-wrapper', classes)}>{children}</div>;
}
