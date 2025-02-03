import { GridItem } from '../../components/GridSystem';
import { Helmet } from 'react-helmet';

import { dateFormat, timeFormat } from '../../utils/dateTimeFormat';
import { useEffect, useState } from 'react';

export default function Dashboard() {
  const [currentDateTime, setCurrentDateTime] = useState<Date>(new Date());

  useEffect(() => {
    const timer = setInterval(() => {
      setCurrentDateTime(new Date());
    }, 1000);

    return () => {
      clearInterval(timer);
    };
  }, []);

  return (
    <>
      <Helmet>
        <title>Dashboard</title>
      </Helmet>
      <GridItem row={2} col={1}>
        <p>IDM0190</p>
      </GridItem>
      <GridItem row={2} col={31}>
        <p>{'<< Dashboard Screen >>'}</p>
      </GridItem>
      <GridItem row={1} col={71}>
        <p>
          {dateFormat(currentDateTime)}
          <br />
          {timeFormat(currentDateTime)}
        </p>
      </GridItem>
      <GridItem row={3} col={1} lineBreak>
        <></>
      </GridItem>

      <GridItem row={5} col={6}>
        <p>Put the page content here</p>
      </GridItem>

      <GridItem row={23} col={1} lineBreak>
        <></>
      </GridItem>
    </>
  );
}
