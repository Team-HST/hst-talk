import { Switch, Route } from 'react-router-dom';
import AuthRoute from './AuthRoute';
import HomePage from 'pages/HomePage';
import MainPage from 'pages/MainPage';
import ChatPage from 'pages/ChatPage';

function App() {
  return (
    <Switch>
      <Route exact path="/" component={HomePage}></Route>
      <AuthRoute path="/main" component={MainPage}></AuthRoute>
      <AuthRoute path="/chatting" component={ChatPage}></AuthRoute>
    </Switch>
  );
}

export default App;
