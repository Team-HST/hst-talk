import { createContext } from 'react';
import { useEffect, useState } from 'react';
import DateUtils from 'utils/DateUtils';

export const SocketContext = createContext<WebSocket | null>(null);

interface SocketProvicerProps {
  children: React.ReactNode;
}

const SocketProvider = ({ children }: SocketProvicerProps) => {
  const [socket, setSocket] = useState<WebSocket | null>(null);

  useEffect(() => {
    const URL = 'ws://localhost:8000/ws/chat';
    const socket = new WebSocket(URL);

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

    setSocket(socket);
  }, []);

  return <SocketContext.Provider value={socket}>{children}</SocketContext.Provider>;
};

export default SocketProvider;
