import { GridItem } from "../../components/GridSystem";
import { Helmet } from "react-helmet";

export default function Home() {
  return (
    <>
     <Helmet>
        <title>Home</title>
      </Helmet>
      <GridItem row={2} col={2}>
        <h2>Homepage</h2>
      </GridItem>
    </>
  );
}
