import classNames from 'classnames/bind';

import styles from './Footer.module.css';
import { useSelector } from 'react-redux';
import { RootState } from '../../../features/store';

const cx = classNames.bind(styles);
export default function Footer() {
  const theme = useSelector((state: RootState) => state.theme);

  const currentYear = new Date().getFullYear();
  return (
    <footer className={cx('wrapper', `header-bg-${theme.name}`)}>
      <p>&copy; {currentYear} FPT Software. All rights reserved.</p>
    </footer>
  );
}
