import { useContext } from 'react';
import { SocketContext } from 'provider/socketProvider';

const useSocket = () => {
  const socket = useContext(SocketContext);

  return socket;
};

export default useSocket;
