import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import { AuthContextProvider } from './context/AuthContextProvider';
import { Cars } from './pages/Cars/Cars';
import { Home } from './pages/Home/Home';
import { ListRequest } from './pages/ListRequest/ListRequest';
import { Request } from './pages/Request/Request';
import { Subscribe } from './pages/Subscribe/Subscribe';
import './styles/global.scss'

function App() {

  return (
    <BrowserRouter>
      <AuthContextProvider>
        <Switch>
          <Route path="/" exact component={Home} />
          <Route path="/subscribe" component={Subscribe} />
          <Route path="/request"  component={Request} />
          <Route path="/cars" component={Cars} />
          <Route path="/request" component={Request} />
          <Route path="/listrequest" component={ListRequest} />
        </Switch>
      </AuthContextProvider>
    </BrowserRouter >
  );
}

export default App;
