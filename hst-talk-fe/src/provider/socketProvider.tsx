import { useEffect, createContext } from 'react';
import DateUtils from 'utils/DateUtils';

const URL = 'ws://localhost:8000/ws/chat';
const socket = new WebSocket(URL);
export let SocketContext = createContext<WebSocket>(socket);

interface SocketProvicerProps {
  children: React.ReactNode;
}

const SocketProvider = ({ children }: SocketProvicerProps) => {
  useEffect(() => {
    socket.onopen = () => {
      console.log(`>> Date: ${DateUtils.getDate('YYYY-MM-DD HH:mm:ss')} \n>> Socket Open`);
    };

    socket.onerror = (event: Event) => {
      console.error(
        `>> Date: ${DateUtils.getDate('YYYY-MM-DD HH:mm:ss')} \n>> Socket Error: `,
        event
      );
    };

    socket.onclose = () => {
      console.log(`>> Date: ${DateUtils.getDate('YYYY-MM-DD HH:mm:ss')} \n>> Socket Close`);
    };
  }, []);

  return <SocketContext.Provider value={socket}>{children}</SocketContext.Provider>;
};

export default SocketProvider;
