import classNames from 'classnames/bind';
import { type ReactNode } from 'react';

import styles from './GridSystem.module.css';

type GridMatrixProps = {
  children: ReactNode;
};

const cx = classNames.bind(styles);
export default function GridMatrix({ children }: GridMatrixProps) {
  return <div className={cx('grid-matrix')}>{children}</div>;
}
