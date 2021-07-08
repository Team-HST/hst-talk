import { useHistory } from 'react-router-dom';
import useSocket from 'hooks/useSocket';
import EnderModal from 'components/enderModal';
import styles from 'resources/css/home.module.css';

const MainPage = () => {
  const socket = useSocket();
  const history = useHistory();

  const onClickCreateRoom = () => {
    socket.send(`{ "messageType": "CREATE_ROOM" }`);
  };

  socket.onmessage = (event: MessageEvent<any>) => {
    const { roomId } = JSON.parse(event.data);

    sessionStorage.setItem('roomId', roomId);
    history.push('chatting');
  };

  return (
    <div className={styles.wrapper}>
      <div className={styles.create_room} onClick={onClickCreateRoom}>
        Create Room
      </div>
      <div className={styles.enter_room}>Enter Room</div>
      <EnderModal />
    </div>
  );
};

export default MainPage;
