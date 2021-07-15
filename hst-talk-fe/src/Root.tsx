import { BrowserRouter } from 'react-router-dom';
import SocketProvider from 'provider/socketProvider';
import App from 'components/App';

const Root = () => {
  return (
    <BrowserRouter>
      <SocketProvider>
        <App />
      </SocketProvider>
    </BrowserRouter>
  );
};

export default Root;
