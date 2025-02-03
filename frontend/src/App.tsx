import { BrowserRouter, Route, Routes } from "react-router-dom";
import { privateRoutes, publicRoute } from "./routes";
import { useSelector } from "react-redux";

import { type RootState } from "./features/store";
import DefaultLayout from "./layouts/DefaultLayout";
import PageNotFound from "./pages/PageNotFound";
function App() {
  const token = useSelector((state: RootState) => state.token);

  return (
    <BrowserRouter>
      <Routes>
        {publicRoute.map((route, index) => {
          const Page = route.component;
          let Layout = route.layout;

          return (
            <Route
              key={index}
              path={route.path}
              element={
                <Layout>
                  <Page />
                </Layout>
              }
            />
          );
        })}

        {token.token &&
          privateRoutes.map((route, index) => {
            const Page = route.component;
            let Layout = route.layout;

            return (
              <Route
                key={index}
                path={route.path}
                element={
                  <Layout>
                    <Page />
                  </Layout>
                }
              />
            );
          })}
        <Route
          path={"*"}
          element={
            <DefaultLayout>
              <PageNotFound />
            </DefaultLayout>
          }
        />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
