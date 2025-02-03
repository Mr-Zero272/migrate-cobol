import { faDisplay } from '@fortawesome/free-solid-svg-icons';
import { useLocation } from 'react-router-dom';
import Button from '../../../components/Button';

export type SidebarItemProps = {
  text: string;
  to: string;
  isPrivate: Boolean;
};

export default function SidebarItem({ text, to, isPrivate }: SidebarItemProps) {
  const location = useLocation();
  const isActive = location.pathname === to;

  return (
    <li className={isPrivate ? 'd-none' : ''}>
      <Button
        to={to}
        text={text}
        size='block'
        icon={faDisplay}
        iconTextSpace='medium'
        isActive={isActive}
      />
    </li>
  );
}
