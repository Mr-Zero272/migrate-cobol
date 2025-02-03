import Tippy, { type TippyProps } from '@tippyjs/react';
import classNames from 'classnames/bind';
import 'tippy.js/dist/tippy.css';

import MenuItem, { type MenuItemProps } from './MenuItem';
import MenuWrapper from './MenuWrapper';
import styles from './Menu.module.css';
import { ReactNode, useState } from 'react';
import MenuHeader from './MenuHeader';
import { useSelector } from 'react-redux';
import { type RootState } from '../../features/store';

export type MenuProps = {
  items: MenuItemProps[];
  onClick: (item: MenuItemProps) => void;
} & TippyProps;

const cx = classNames.bind(styles);
export default function Menu({
  children,
  hideOnClick,
  placement,
  items,
  onClick,
}: MenuProps) {
  const theme = useSelector((state: RootState) => state.theme);
  const [history, setHistory] = useState([
    { text: 'default', data: { text: 'Menu', data: items } },
  ]);
  const current = history[history.length - 1];

  const renderMenuContent = () => {
    return (
      <MenuWrapper classes={cx(`bg-${theme.name}`, `text-${theme.name}`)}>
        {history.length > 1 && (
          <MenuHeader title={current.text} onBack={handleBack} />
        )}
        {current.data.data.map((item, index) => {
          const isParent = !!item.nestedItems;

          return (
            <MenuItem
              icon={item.icon}
              text={item.text}
              key={index}
              to={item.to}
              onClick={() => {
                if (isParent) {
                  if (item.nestedItems) {
                    const newHistory = {
                      text: item.text,
                      data: item.nestedItems,
                    };
                    setHistory((prev) => [...prev, newHistory]);
                  }
                } else {
                  onClick(item);
                }
              }}
            />
          );
        })}
      </MenuWrapper>
    );
  };

  const handleBack = () => {
    setHistory((prev) => prev.slice(0, prev.length - 1));
  };

  const handleReset = () => {
    setHistory((prev) => prev.slice(0, 1));
  };

  return (
    <Tippy
      interactive
      delay={[0, 800]}
      hideOnClick={hideOnClick}
      placement={placement}
      content={renderMenuContent()}
      onHide={handleReset}
    >
      {children}
    </Tippy>
  );
}
