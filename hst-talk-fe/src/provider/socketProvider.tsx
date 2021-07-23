import { useEffect, useState, createContext } from 'react';
import { useHistory } from 'react-router-dom';
import DateUtils from 'utils/DateUtils';

type SocketContextType = {
  socket: WebSocket;
  isConnection: boolean;
};

const URL = `ws://${process.env.REACT_APP_WEB_SOCKET_IP}:${process.env.REACT_APP_WEB_SOCKET_PORT}/ws/chat`;
const socket = new WebSocket(URL);
export let SocketContext = createContext<SocketContextType>({ socket, isConnection: false });

interface SocketProvicerProps {
  children: React.ReactNode;
}

const SocketProvider = ({ children }: SocketProvicerProps) => {
  const [isConnection, setIsConnection] = useState<boolean>(false);
  const history = useHistory();

  useEffect(() => {
    socket.onopen = () => {
      console.log(`>> Date: ${DateUtils.getDate('YYYY-MM-DD HH:mm:ss')} \n>> Socket Open`);
      console.log('>> Session Initialize');
      sessionStorage.removeItem('id');
      sessionStorage.removeItem('talkName');
      sessionStorage.removeItem('roomId');
      history.push('/');
      setIsConnection(true);
    };

    socket.onerror = (event: Event) => {
      console.error(
        `>> Date: ${DateUtils.getDate('YYYY-MM-DD HH:mm:ss')} \n>> Socket Error: `,
        event
      );
    };

    socket.onclose = () => {
      console.log(`>> Date: ${DateUtils.getDate('YYYY-MM-DD HH:mm:ss')} \n>> Socket Close`);
      setIsConnection(false);
    };
  }, [history]);

  return (
    <SocketContext.Provider value={{ socket, isConnection }}>{children}</SocketContext.Provider>
  );
};

export default SocketProvider;
