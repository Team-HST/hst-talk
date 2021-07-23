import { useState, useEffect, useRef } from 'react';
import { useHistory } from 'react-router-dom';
import useSocket from 'hooks/useSocket';
import useInput from 'hooks/useInput';
import SocketUtils from 'utils/SocketUtils';
import { User } from 'types/user';
import { Chat } from 'types/chat';
import copy from 'copy-to-clipboard';

import ChatUserList from 'components/chatUserList';
import ChatMessage from 'components/chatMessage';
import SocketConstants from 'constants/SocketConstants';
import ToastAlarm from 'components/toastAlarm';
import styles from 'resources/css/chat.module.css';
import sendIcon from 'resources/images/send.png';

const ChatPage = () => {
  const { socket, isConnection } = useSocket();
  const history = useHistory();
  const chatBodyEl = useRef<HTMLDivElement>(null);
  const [message, setMessage, changeMessage] = useInput<string>('');
  const [chatList, setChatList] = useState<Chat[]>([]);
  const [isChatInfo, setIsChatInfo] = useState<boolean>(true);
  const [roomCode, setRoomCode] = useState<string>('');
  const [userList, setUserList] = useState<User[]>([]);
  const [isToast, setIsToast] = useState<boolean>(false);

  const onClickChatInfo = () => {
    setIsChatInfo(!isChatInfo);
  };

  // 룸 코드 클립보드 복사
  const onClickClipBoard = () => {
    setIsToast(true);
    copy(roomCode);
  };

  const closeToast = () => {
    setIsToast(false);
  };

  const onClickClose = () => {
    socket.send(SocketUtils.getSendMessage(SocketConstants.Protocol.LEAVE_ROOM, roomCode));

    history.push('/main');
  };

  const onKeypress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      // 닉네임전달 및 소켓 연결 처리
      socket.send(SocketUtils.getSendMessage(SocketConstants.Protocol.CHAT, roomCode, message));

      changeMessage('');
    }
  };

  const onClickSendMessage = () => {
    socket.send(SocketUtils.getSendMessage(SocketConstants.Protocol.CHAT, roomCode, message));

    changeMessage('');
  };

  socket.onmessage = (event: MessageEvent<any>) => {
    const { messageType, payload } = JSON.parse(event.data);

    switch (messageType) {
      case SocketConstants.Protocol.GET_ROOM_MEMBER_LIST:
        setUserList(payload.participants);
        break;
      case SocketConstants.Protocol.CHAT:
        const type = payload.senderNickname === 'SYSTEM' ? 'SYSTEM' : 'USER';
        const message: Chat = {
          type,
          message: payload.message,
          me: payload.me,
          senderNickname: payload.senderNickname,
          sendAt: payload.sendAt,
        };

        setChatList((state) => state.concat([message]));
        break;
      case SocketConstants.Protocol.SYSTEM_ERROR:
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
          SocketUtils.getSendMessage(SocketConstants.Protocol.GET_ROOM_MEMBER_LIST, roomId)
        );
      }
    } else {
      history.push('/main');
    }
  }, [history, socket, isConnection]);

  useEffect(() => {
    if (chatBodyEl.current) {
      const { scrollHeight, clientHeight } = chatBodyEl.current;
      chatBodyEl.current.scrollTop = scrollHeight - clientHeight;
    }
  }, [chatList]);

  useEffect(() => {
    const enterMessage: Chat = { type: 'ENTER' };

    setChatList((state) => state.concat([enterMessage]));

    return () => {
      sessionStorage.removeItem('roomId');
    };
  }, []);

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
          <div className={styles.chat_box_body} ref={chatBodyEl}>
            {chatList.map((chat, index) => (
              <ChatMessage key={index} chat={chat} />
            ))}
          </div>
          <div className={styles.chat_box_input}>
            <input
              type="text"
              value={message}
              onChange={setMessage}
              onKeyPress={onKeypress}
              placeholder="Ender Message"
            />
            <img src={sendIcon} onClick={onClickSendMessage} alt="전송(send)" />
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
