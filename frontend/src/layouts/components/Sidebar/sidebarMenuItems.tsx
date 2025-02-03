import { type SidebarItemProps } from './SidebarItem';
import routeConfig, {
  bmsRoutesConfig,
//  dspfRoutesConfig,
//  ipfRoutesConfig,
//  acosRoutesConfig,
//  ifdRoutesConfig
} from '../../../config/routeConfig';

// default menu items
const menuItems: SidebarItemProps[] = [
  {
    text: 'Insert',
    to: routeConfig.userCreate,
    isPrivate: true,
  },
  {
    text: 'View',
    to: routeConfig.userDetails.replace(':username', 'johndoe123admin'),
    isPrivate: true,
  },
  {
    text: 'Update',
    to: routeConfig.userUpdate.replace(':username', 'johndoe123admin'),
    isPrivate: true,
  },
];

// bms menu items
const bmsMenuItems: SidebarItemProps[] = bmsRoutesConfig.map((route) => {
  let item: SidebarItemProps = { text: '', to: '', isPrivate: false };
  for (const key in route) {
    item.text = key;
    item.to = '/' + route[key];
  }

  return item;
});

//// dspf menu items
//const dspfMenuItems: SidebarItemProps[] = dspfRoutesConfig.map((route) => {
//  let item: SidebarItemProps = { text: '', to: '', isPrivate: false };
//  for (const key in route) {
//    item.text = key;
//    item.to = '/' + route[key];
//  }
//
//  return item;
//});
//
//// ipf menu items
//const ipfMenuItems: SidebarItemProps[] = ipfRoutesConfig.map((route) => {
//  let item: SidebarItemProps = { text: '', to: '', isPrivate: false };
//  for (const key in route) {
//    item.text = key;
//    item.to = '/' + route[key];
//  }
//
//  return item;
//});
//
//// acos menu items
//const acosMenuItems: SidebarItemProps[] = acosRoutesConfig.map((route) => {
//  let item: SidebarItemProps = { text: '', to: '', isPrivate: false };
//  for (const key in route) {
//    item.text = key;
//    item.to = '/' + route[key];
//  }
//
//  return item;
//});

//// ifd menu items
//const ifdMenuItems: SidebarItemProps[] = ifdRoutesConfig.map((route) => {
//  let item: SidebarItemProps = { text: '', to: '', isPrivate: false };
//  for (const key in route) {
//    item.text = key;
//    item.to = '/' + route[key];
//  }
//
//  return item;
//});

//export default [...menuItems, ...bmsMenuItems, ...ipfMenuItems, ...acosMenuItems, ...ifdMenuItems];
 export default [...menuItems, ...bmsMenuItems];
