import React from 'react';
import ReactDOM from 'react-dom/client';
import { Provider } from 'react-redux';
import { PersistGate } from 'redux-persist/integration/react';
import 'react-toastify/dist/ReactToastify.css';

import { persistor, store } from './features/store.tsx';
import GlobalStyles from './components/GlobalStyles';
import App from './App.tsx';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <Provider store={store}>
      <PersistGate loading={null} persistor={persistor}>
        <GlobalStyles>
          <App />
        </GlobalStyles>
      </PersistGate>
    </Provider>
  </React.StrictMode>
);
