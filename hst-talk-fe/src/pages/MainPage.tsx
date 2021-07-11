import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import useSocket from 'hooks/useSocket';
import EnderModal from 'components/enderModal';
import styles from 'resources/css/home.module.css';

const MainPage = () => {
  const [isCreateRoom, setIsCreateRoom] = useState<boolean>(false);
  const socket = useSocket();
  const history = useHistory();

  const onClickCreateRoom = () => {
    socket.send(`{ "messageType": "CREATE_ROOM" }`);
  };

  const onClickEnterRoom = () => {
    setIsCreateRoom(true);
  };

  const onCloseEnterRoom = () => {
    setIsCreateRoom(false);
  };

  const enterRoom = (roomId: String) => {
    // 채팅방 입장
    socket.send(`{ "messageType": "ENTER_ROOM", "roomId": ${roomId}}`);
  };

  socket.onmessage = (event: MessageEvent<any>) => {
    const { messageType, roomId, payload } = JSON.parse(event.data);

    console.log(messageType, roomId, payload);

    // sessionStorage.setItem('roomId', roomId);
    history.push('chatting');
  };

  return (
    <div className={styles.wrapper}>
      <div className={styles.create_room} onClick={onClickCreateRoom}>
        Create Room
      </div>
      <div className={styles.enter_room} onClick={onClickEnterRoom}>
        Enter Room
      </div>
      <EnderModal isDisplay={isCreateRoom} enterRoom={enterRoom} onClose={onCloseEnterRoom} />
    </div>
  );
};

export default MainPage;
