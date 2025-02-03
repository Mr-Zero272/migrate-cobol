import { type ReactNode } from 'react';
import './GlobalStyles.css';

type GlobalStylesProps = {
  children: ReactNode;
};

export default function GlobalStyles({ children }: GlobalStylesProps) {
  return children;
}
