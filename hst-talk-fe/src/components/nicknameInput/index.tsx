import { useHistory } from 'react-router-dom';
import SocketConstants from 'constants/SocketConstants';
import useSocket from 'hooks/useSocket';
import useInput from 'hooks/useInput';
import styles from './style.module.css';

const NicknameInput = () => {
  const [text, setText] = useInput<string>('');
  const { socket } = useSocket();
  const history = useHistory();

  const onKeypress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      // 닉네임전달 및 소켓 연결 처리
      socket.send(
        `{ "messageType": "${SocketConstants.Message.INIT_USER_INFO}", "roomId": null, "payload": "${text}" }`
      );
    }
  };

  socket.onmessage = (event: MessageEvent<any>) => {
    const { payload } = JSON.parse(event.data);

    sessionStorage.setItem('talkName', text);
    sessionStorage.setItem('id', payload);

    history.push('/main');
  };

  return (
    <input
      className={styles.input}
      type="text"
      value={text}
      onChange={setText}
      onKeyPress={(e) => onKeypress(e)}
      placeholder="Enter your nickname"
    />
  );
};

export default NicknameInput;
