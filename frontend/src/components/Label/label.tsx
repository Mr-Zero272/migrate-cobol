import { type CSSProperties, type ComponentPropsWithoutRef } from 'react';
import { useSelector } from 'react-redux';
import { type RootState } from '../../features/store';
 
type LabelProps = {
  maxLength?: number;
  classes?: string;
  styles?: CSSProperties;
  children?: string;
} & ComponentPropsWithoutRef<'pre'>;
 
export default function Label(props: LabelProps) {
  const theme = useSelector((state: RootState) => state.theme);

  const length = props.maxLength || 0;

  let minusPx = 0;
  if (props.className?.includes("acos")) {
    minusPx = 1;
    if (props.className?.includes("textVerticalRight")) {
      minusPx = -1;
    }
  }
  

  return (
    <pre
      {...props}
      className={`label-${theme.name} ${!props.className?.includes('acos') ? props.className : (length > 0 ? props.className : '')}`}
      style={{
        display: 'inline-flex',
        ...(length > 0 ? { width: `calc(${length}ch + ${minusPx}px)` } : {}),
        ...props.style,
      }}
    />
  );
}