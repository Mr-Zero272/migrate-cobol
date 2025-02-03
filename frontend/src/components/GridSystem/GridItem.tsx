import { type ReactNode } from 'react';
import { useSelector } from 'react-redux';

import { type RootState } from '../../features/store';

export enum GridEnum {
  COL_COUNT = 80,
  ROW_COUNT = 24,
}

type GridItemProps = {
  row: number;
  rowEnd?: number;
  col: number;
  colEnd?: number;
  lineBreak?: boolean;
  classes?: string;
  children: ReactNode;
};

export default function GridItem({
  row,
  rowEnd,
  col,
  colEnd,
  lineBreak,
  classes,
  children,
}: GridItemProps) {
  const theme = useSelector((state: RootState) => state.theme);

  return (
    <div
      className={`grid-item row-${row} ${
        rowEnd ? 'row-end-' + rowEnd : ''
      } col-${col} ${colEnd ? 'col-end-' + colEnd : ''} ${
        classes ? classes : ''
      } ${lineBreak ? `line-break-${theme.name}` : ''}`}
    >
      {children}
    </div>
  );
}
