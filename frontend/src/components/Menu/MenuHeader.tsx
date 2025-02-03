import { faChevronLeft } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import classNames from 'classnames/bind';

type MenuHeaderProps = {
  title: string;
  onBack: () => void;
};

import styles from './Menu.module.css';

const cx = classNames.bind(styles);

export default function MenuHeader({ title, onBack }: MenuHeaderProps) {
  return (
    <header className={cx('menu-header')}>
      <button className={cx('back-btn')} onClick={onBack}>
        <FontAwesomeIcon icon={faChevronLeft} />
      </button>
      <div className={cx('menu-header-title')}>
        <h4>{title}</h4>
      </div>
    </header>
  );
}
