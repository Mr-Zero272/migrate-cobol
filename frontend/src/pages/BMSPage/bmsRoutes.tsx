
import { type ElementType } from 'react';
import COACTUP from "./COACTUP"
import COACTVW from "./COACTVW"
import COADM01 from "./COADM01"
import COBIL00 from "./COBIL00"
import COCRDLI from "./COCRDLI"
import COCRDSL from "./COCRDSL"
import COCRDUP from "./COCRDUP"
import COMEN01 from "./COMEN01"
import CORPT00 from "./CORPT00"
import COSGN00 from "./COSGN00"
import COTRN00 from "./COTRN00"
import COTRN01 from "./COTRN01"
import COTRN02 from "./COTRN02"
import COUSR00 from "./COUSR00"
import COUSR01 from "./COUSR01"
import COUSR02 from "./COUSR02"
import COUSR03 from "./COUSR03"

type BMSRoutes = {
  name: string;
  component: ElementType;
}[];

const bmsRoutes: BMSRoutes = [{name:"COACTUP",component:COACTUP},{name:"COACTVW",component:COACTVW},{name:"COADM01",component:COADM01},{name:"COBIL00",component:COBIL00},{name:"COCRDLI",component:COCRDLI},{name:"COCRDSL",component:COCRDSL},{name:"COCRDUP",component:COCRDUP},{name:"COMEN01",component:COMEN01},{name:"CORPT00",component:CORPT00},{name:"COSGN00",component:COSGN00},{name:"COTRN00",component:COTRN00},{name:"COTRN01",component:COTRN01},{name:"COTRN02",component:COTRN02},{name:"COUSR00",component:COUSR00},{name:"COUSR01",component:COUSR01},{name:"COUSR02",component:COUSR02},{name:"COUSR03",component:COUSR03}];

export default bmsRoutes;
