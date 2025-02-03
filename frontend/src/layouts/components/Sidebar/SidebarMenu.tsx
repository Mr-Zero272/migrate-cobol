import classNames from 'classnames/bind';
import SidebarItem, { SidebarItemProps } from './SidebarItem';

import styles from './Sidebar.module.css';

export type SidebarMenuProps = {
  items: SidebarItemProps[];
  classes?: string;
};

const cx = classNames.bind(styles);
export default function SidebarMenu({ items, classes }: SidebarMenuProps) {
  return (
    <ul className={cx('menu-wrapper', classes)}>
      {items.map((item, index) => (
        <SidebarItem key={index} text={item.text} to={item.to} isPrivate={item.isPrivate} />
      ))}
    </ul>
  );
}
