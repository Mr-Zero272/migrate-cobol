import classNames from 'classnames/bind';
import { GridItem } from '../../components/GridSystem';
import styles from './PageNotFound.module.css';
import { Helmet } from 'react-helmet';

const cx = classNames.bind(styles);
export default function PageNotFound() {
  return (
    <>
      <Helmet>
        <title>404 - NOT FOUND</title>
        <meta name='description' content='The URL is not found.' />
        <meta name='keywords' content='404, page not found, denied' />
      </Helmet>
      <GridItem col={30} row={12}>
        <div className={cx('title')}>Page Not Found - 404</div>
      </GridItem>
    </>

    // tag
  );
}
