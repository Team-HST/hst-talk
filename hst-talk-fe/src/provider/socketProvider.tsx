import { useEffect, useState, createContext } from 'react';
import DateUtils from 'utils/DateUtils';

type SocketContextType = {
  socket: WebSocket;
  isConnection: boolean;
};

const URL = 'ws://localhost:8000/ws/chat';
const socket = new WebSocket(URL);
export let SocketContext = createContext<SocketContextType>({ socket, isConnection: false });

interface SocketProvicerProps {
  children: React.ReactNode;
}

const SocketProvider = ({ children }: SocketProvicerProps) => {
  const [isConnection, setIsConnection] = useState<boolean>(false);

  useEffect(() => {
    socket.onopen = () => {
      console.log(`>> Date: ${DateUtils.getDate('YYYY-MM-DD HH:mm:ss')} \n>> Socket Open`);
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
  }, []);

  return (
    <SocketContext.Provider value={{ socket, isConnection }}>{children}</SocketContext.Provider>
  );
};

export default SocketProvider;
