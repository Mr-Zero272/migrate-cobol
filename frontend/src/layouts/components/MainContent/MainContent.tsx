import classNames from 'classnames/bind';
import { type ReactNode } from 'react';

import styles from './MainContent.module.css';
import { useSelector } from 'react-redux';
import { RootState } from '../../../features/store';
import { GridMatrix } from '../../../components/GridSystem';

type MainContentProps = {
  classes?: string;
  children: ReactNode;
};

const cx = classNames.bind(styles);
export default function MainContent({ classes, children }: MainContentProps) {
  const theme = useSelector((state: RootState) => state.theme);

  return (
    <div
      className={cx(
        'wrapper',
        `bg-secondary-${theme.name}`,
        `border-${theme.name}`,
        classes
      )}
    >
      <GridMatrix>{children}</GridMatrix>
    </div>
  );
}
