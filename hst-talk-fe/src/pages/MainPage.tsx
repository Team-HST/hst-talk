import { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import useSocket from 'hooks/useSocket';
import SocketConstants from 'constants/SocketConstants';
import EnderModal from 'components/enderModal';
import styles from 'resources/css/home.module.css';

const MainPage = () => {
  const [isCreateRoom, setIsCreateRoom] = useState<boolean>(false);
  const { socket } = useSocket();
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

  const enterRoom = (roomId: string) => {
    // 채팅방 입장
    socket.send(`{ "messageType": "${SocketConstants.Message.ENTER_ROOM}", "roomId": "${roomId}"}`);
    sessionStorage.setItem('roomId', roomId);
  };

  socket.onmessage = (event: MessageEvent<any>) => {
    const { messageType, roomId } = JSON.parse(event.data);

    switch (messageType) {
      case SocketConstants.Message.SYSTEM_ERROR:
        alert('생성되지 않은 방 코드입니다.');
        sessionStorage.removeItem('roomId');
        return;
      case SocketConstants.Message.CREATE_ROOM:
        sessionStorage.setItem('roomId', roomId);
        break;
    }

    history.push('/chatting');
  };

  return (
    <div className={styles.wrapper}>
      <div className={styles.create_room} onClick={onClickCreateRoom}>
        Create Room
      </div>
      <div className={styles.enter_room} onClick={onClickEnterRoom}>
        Enter Room
      </div>
      <EnderModal
        isDisplay={isCreateRoom}
        enterRoom={enterRoom}
        closeEnterRoom={onCloseEnterRoom}
      />
    </div>
  );
};

export default MainPage;
