import { type ElementType } from 'react';
import bmsRoutes from '../pages/BMSPage/bmsRoutes';
//import dspfRoutes from '../pages/DSPFPage/dspfRoutes';
//import ipfRoutes from '../pages/IPFPage/ipfRoutes';
//import acosRoutes from '../pages/ACOSPage/acosRoutes';
//import ifdRoutes from '../pages/IFDPage/ifdRoutes';
import routeConfig from '../config/routeConfig';
import Home from '../pages/Home';
import Login from '../pages/Login';

import Dashboard from '../pages/Dashboard';
import {
  UserDetails,
  UserCreate,
  UserChangePassword,
} from '../pages/UserManagement';
import DefaultLayout from '../layouts/DefaultLayout';

type Route = {
  path: string;
  component: ElementType;
  layout: ElementType;
};

const publicRoute: Route[] = [
  { path: routeConfig.home, component: Home, layout: DefaultLayout },
  { path: routeConfig.login, component: Login, layout: DefaultLayout },
];

bmsRoutes.forEach((route) => {
  publicRoute.push({
    path: route.name,
    component: route.component,
    layout: DefaultLayout,
  });
});

//dspfRoutes.forEach((route) => {
//  publicRoute.push({
//    path: route.name,
//    component: route.component,
//    layout: DefaultLayout,
//  });
//});
//
//ipfRoutes.forEach((route) => {
//  publicRoute.push({
//    path: route.name,
//    component: route.component,
//    layout: DefaultLayout,
//  });
//});
//
//acosRoutes.forEach((route) => {
//  publicRoute.push({
//    path: route.name,
//    component: route.component,
//    layout: DefaultLayout,
//  });
//});
//
//ifdRoutes.forEach((route) => {
//  publicRoute.push({
//    path: route.name,
//    component: route.component,
//    layout: DefaultLayout,
//  });
//});
//
const privateRoutes: Route[] = [
  { path: routeConfig.dashboard, component: Dashboard, layout: DefaultLayout },
  // user management routes
  {
    path: routeConfig.userDetails,
    component: UserDetails,
    layout: DefaultLayout,
  },
  {
    path: routeConfig.userCreate,
    component: UserCreate,
    layout: DefaultLayout,
  },
  {
    path: routeConfig.userUpdate,
    component: UserCreate,
    layout: DefaultLayout,
  },
  {
    path: routeConfig.userChangePassword,
    component: UserChangePassword,
    layout: DefaultLayout,
  },
];

export { publicRoute, privateRoutes };
