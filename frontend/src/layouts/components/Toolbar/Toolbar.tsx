import { useSelector } from 'react-redux';
import classNames from 'classnames/bind';

import Button from '../../../components/Button';
import { RootState } from '../../../features/store';
import styles from './Toolbar.module.css';

const cx = classNames.bind(styles);
export default function Toolbar() {
  const theme = useSelector((state: RootState) => state.theme);

  return (
    <div
      className={cx(
        'wrapper',
        `bg-secondary-${theme.name}`,
        `border-${theme.name}`
      )}
    >
      <div className={cx('actions')}>
        <Button text='Enter' size='medium'></Button>
        <Button text='PF1' size='medium'></Button>
        <Button text='PF2' size='medium'></Button>
        <Button text='PF3' size='medium'></Button>
        <Button text='PF4' size='medium'></Button>
        <Button text='PF5' size='medium'></Button>
        <Button text='PF6' size='medium'></Button>
        <Button text='PF7' size='medium'></Button>
        <Button text='PF8' size='medium'></Button>
        <Button text='PF9' size='medium'></Button>
        <Button text='PF10' size='medium'></Button>
        <Button text='PF11' size='medium'></Button>
        <Button text='PF12' size='medium'></Button>
      </div>
    </div>
  );
}
