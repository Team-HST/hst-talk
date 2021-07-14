import { useContext } from 'react';
import { SocketContext } from 'provider/socketProvider';

const useSocket = () => {
  const socketContext = useContext(SocketContext);

  return socketContext;
};

export default useSocket;
