import { Switch, Route } from 'react-router-dom';
import { Helmet } from 'react-helmet';
import AuthRoute from './AuthRoute';
import HomePage from 'pages/HomePage';
import MainPage from 'pages/MainPage';
import ChatPage from 'pages/ChatPage';

function App() {
  return (
    <>
      <Helmet>
        <title>HST TALK</title>
        <meta
          name="viewport"
          content="initial-scale=1.0, user-scalable=no, maximum-scale=1, width=device-width"
        />
      </Helmet>
      <Switch>
        <Route exact path="/" component={HomePage}></Route>
        <AuthRoute path="/main" component={MainPage}></AuthRoute>
        <AuthRoute path="/chatting" component={ChatPage}></AuthRoute>
      </Switch>
    </>
  );
}

export default App;
