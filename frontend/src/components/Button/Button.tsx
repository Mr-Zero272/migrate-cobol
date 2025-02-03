import { ComponentPropsWithoutRef, forwardRef, type ReactNode } from 'react';
import classNames from 'classnames/bind';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { type IconDefinition } from '@fortawesome/free-solid-svg-icons';

import styles from './Button.module.css';
import { NavLink, type NavLinkProps } from 'react-router-dom';
import { useSelector } from 'react-redux';
import { type RootState } from '../../features/store';

type AnchorProps = NavLinkProps & {
  to?: string;
  text: string;
};

type ButtonProps = ComponentPropsWithoutRef<'button'> & {
  text?: string;
  noBackground?: boolean;
};

type CombinedButtonProps = (AnchorProps | ButtonProps) & {
  size: 'small' | 'medium' | 'large' | 'block';
  icon?: IconDefinition;
  iconTextSpace?: 'small' | 'medium' | 'large';
  isActive?: boolean;
  classes?: string;
};

function isAnchorElement(
  props: ButtonProps | AnchorProps
): props is AnchorProps {
  return 'to' in props;
}

const cx = classNames.bind(styles);
const Button = forwardRef<HTMLButtonElement, CombinedButtonProps>(function (
  props: CombinedButtonProps,
  ref
) {
  const theme = useSelector((state: RootState) => state.theme);

  let iconComponenet: ReactNode;
  if (props.icon) {
    iconComponenet = <FontAwesomeIcon icon={props.icon} />;
  }

  if (isAnchorElement(props)) {
    return (
      <NavLink
        to={props.to}
        className={cx(
          `btn-${props.size}`,
          'btn-link',
          `text-${theme.name}`,
          `${props.isActive && `active-link-${theme.name}`}`,
          `${props.classes}`
        )}
      >
        {iconComponenet}
        <span className={cx(`icon-text-spacing-${props.iconTextSpace}`)}>
          {props.text}
        </span>
      </NavLink>
    );
  }

  return (
    <button
      onClick={props.onClick}
      className={cx(
        `btn-${props.size}`,
        `${props.noBackground ? '' : 'btn-' + theme.name}`,
        `${props.classes}`
      )}
      ref={ref}
    >
      {iconComponenet}
      <span className={cx(`icon-text-spacing-${props.iconTextSpace}`)}>
        {props.text}
      </span>
    </button>
  );
});

export default Button;
