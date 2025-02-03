//import dspfRoutes from '../pages/DSPFPage/dspfRoutes';
import bmsRoutes from './../pages/BMSPage/bmsRoutes';
//import ipfRoutes from './../pages/IPFPage/ipfRoutes';
//import acosRoutes from './../pages/ACOSPage/acosRoutes';
//import ifdRoutes from './../pages/IFDPage/ifdRoutes';

const basedRoutesConfig = {
  home: '/',
  // auth
  login: '/login',
  dashboard: '/admin',
  // user management
  userDetails: '/admin/user/:username',
  userCreate: '/admin/user/create',
  userUpdate: '/admin/user/update/:username',
  userChangePassword: '/admin/user/password/:username',
  userDelete: '/admin/user/delete/:username',
};

export const bmsRoutesConfig = bmsRoutes.map((route) => {
  return {
    [route.name]: route.name,
  };
});

//export const dspfRoutesConfig = dspfRoutes.map((route) => {
//  return {
//    [route.name]: route.name,
//  };
//});
//
//export const ipfRoutesConfig = ipfRoutes.map((route) => {
//  return {
//    [route.name]: route.name,
//  };
//});
//
//export const acosRoutesConfig = acosRoutes.map((route) => {
//  return {
//    [route.name]: route.name,
//  };
//});
//
//export const ifdRoutesConfig = ifdRoutes.map((route) => {
//  return {
//    [route.name]: route.name,
//  };
//});

export default {
  ...basedRoutesConfig,
  ...bmsRoutesConfig,
// ...dspfRoutesConfig,
// ...ipfRoutesConfig,
// ...acosRoutesConfig,
// ...ifdRoutesConfig,
};
