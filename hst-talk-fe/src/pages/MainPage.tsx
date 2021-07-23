import { useState } from 'react';
import { useHistory } from 'react-router-dom';
import useSocket from 'hooks/useSocket';
import SocketConstants from 'constants/SocketConstants';
import EnderModal from 'components/enderModal';
import SocketUtils from 'utils/SocketUtils';
import styles from 'resources/css/main.module.css';

const MainPage = () => {
  const [isCreateRoom, setIsCreateRoom] = useState<boolean>(false);
  const { socket } = useSocket();
  const history = useHistory();

  const onClickCreateRoom = () => {
    socket.send(SocketUtils.getSendMessage(SocketConstants.Protocol.CREATE_ROOM));
  };

  const onClickEnterRoom = () => {
    setIsCreateRoom(true);
  };

  const onCloseEnterRoom = () => {
    setIsCreateRoom(false);
  };

  const enterRoom = (roomId: string) => {
    // 채팅방 입장
    socket.send(SocketUtils.getSendMessage(SocketConstants.Protocol.ENTER_ROOM, roomId));
    sessionStorage.setItem('roomId', roomId);
  };

  socket.onmessage = (event: MessageEvent<any>) => {
    const { messageType, roomId } = JSON.parse(event.data);

    switch (messageType) {
      case SocketConstants.Protocol.SYSTEM_ERROR:
        alert('생성되지 않은 방 코드입니다.');
        sessionStorage.removeItem('roomId');
        return;
      case SocketConstants.Protocol.CREATE_ROOM:
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
