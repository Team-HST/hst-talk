import { useState, useEffect, useRef } from 'react';
import { useHistory } from 'react-router-dom';
import useSocket from 'hooks/useSocket';
import { UserInfo } from 'types/chat';

import ChatUserList from 'components/chatUserList';
import ChatMessage from 'components/chatMessage';
import SocketConstants from 'constants/SocketConstants';
import ToastAlarm from 'components/toastAlarm';
import styles from 'resources/css/chat.module.css';
import sendIcon from 'resources/images/send.png';

const chatMessageDummy = [
  { type: 'center' },
  { type: 'left', name: 'jemmy', message: 'hello my name' },
  { type: 'right', name: 'sancho', message: 'ohh hi' },
];

// TODO: 뒤로가기 시 처리
const ChatPage = () => {
  const { socket, isConnection } = useSocket();
  const history = useHistory();
  const [isChatInfo, setIsChatInfo] = useState<boolean>(true);
  const [roomCode, setRoomCode] = useState<string>('');
  const [userList, setUserList] = useState<UserInfo[]>([]);
  const [isToast, setIsToast] = useState<boolean>(false);

  const onClickChatInfo = () => {
    setIsChatInfo(!isChatInfo);
  };

  const onClickClose = () => {
    sessionStorage.removeItem('roomId');
    history.push('/main');
  };

  // 룸 코드 클립보드 복사
  const onClickClipBoard = () => {
    navigator.clipboard.writeText(roomCode);
    setIsToast(true);
  };

  const closeToast = () => {
    setIsToast(false);
  };

  socket.onmessage = (event: MessageEvent<any>) => {
    const { messageType, roomId, payload } = JSON.parse(event.data);

    console.log('Type: ', messageType, 'Payload: ', payload);
    switch (messageType) {
      case SocketConstants.Message.GET_ROOM_MEMBER_LIST:
        console.log('get Room');
        setUserList(payload.participants);
        break;
      case SocketConstants.Message.SYSTEM_ERROR:
        if (payload.indexOf('Can not find room') > -1) {
          alert('이미 종료 된 방 정보입니다.');
          history.push('/main');
        }
        break;
    }
  };

  useEffect(() => {
    const roomId = sessionStorage.getItem('roomId');

    if (roomId) {
      setRoomCode(roomId);

      if (socket.readyState === 1) {
        socket.send(
          `{"messageType": "${SocketConstants.Message.GET_ROOM_MEMBER_LIST}", "roomId": "${roomId}"}`
        );
      }
    } else {
      history.push('/main');
    }
  }, [history, socket, isConnection]);

  return (
    <div className={styles.wrapper}>
      <div className={styles.chat_header}>
        <h2>
          <i className="fas fa-align-justify" onClick={onClickChatInfo}></i> HST <span>Talk</span>
        </h2>
        <div className={styles.chat_end} onClick={onClickClose}>
          <i className="fas fa-door-open fa-lg"></i> Close
        </div>
      </div>
      <div className={styles.chat_body}>
        <div className={`${styles.chat_info} ${isChatInfo ? styles.on : ''}`}>
          <ChatUserList userList={userList} />
          <div className={styles.chat_info_footer}>
            <h3>
              Room Code{' '}
              <i
                className={`far fa-copy ${styles.chat_info_footer_copy}`}
                title="code copy"
                onClick={onClickClipBoard}
              ></i>
            </h3>
            <div>{roomCode}</div>
          </div>
        </div>
        <div className={styles.chat_box}>
          <div className={styles.chat_box_body}>
            {chatMessageDummy.map((message) => (
              <ChatMessage chat={message} />
            ))}
          </div>
          <div className={styles.chat_box_input}>
            <input type="text" placeholder="Ender Message" />
            <img src={sendIcon} alt="전송(send)" />
          </div>
        </div>
      </div>
      {isToast && (
        <ToastAlarm text="Clipboard copy complete" duration={2000} onClose={closeToast} />
      )}
    </div>
  );
};

export default ChatPage;
