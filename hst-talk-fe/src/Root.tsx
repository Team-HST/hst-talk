import { BrowserRouter } from 'react-router-dom';
import SocketProvider from 'provider/socketProvider';
import App from 'components/App';

const Root = () => {
  return (
    <SocketProvider>
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </SocketProvider>
  );
};

export default Root;
