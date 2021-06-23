import { Route, Redirect } from 'react-router-dom';

interface AuthRouteProps {
  component: React.ComponentType<any>;
  path: string;
}

const AuthRoute = ({ component, ...rest }: AuthRouteProps) => {
  const talkName = sessionStorage.getItem('talkName');

  return true ? <Route {...rest} component={component} /> : <Redirect to="/" />;
};

export default AuthRoute;
