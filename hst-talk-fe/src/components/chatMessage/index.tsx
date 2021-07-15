import DataUtils from 'utils/DateUtils';
import { Chat } from 'types/chat';
import styles from './style.module.css';

interface ChatMessageProps {
  chat: Chat;
}

const ChatMessage = ({ chat }: ChatMessageProps) => {
  if (chat.type === 'ENTER') {
    return (
      <div className={styles.chat_message_center}>
        <h3>Start joining chat 😀</h3>
      </div>
    );
  } else if (chat.type === 'SYSTEM') {
    return (
      <div className={styles.chat_message_center}>
        <h4>{chat.message}</h4>
        <span>{DataUtils.getDate('YYYY-MM-DD HH:mm:ss', chat.sendAt)}</span>
      </div>
    );
  } else {
    return (
      <div className={chat.me ? styles.chat_message_left : styles.chat_message_right}>
        <div className={styles.chat_message_user}>
          <h3>{chat.senderNickname}</h3>
          <span>{DataUtils.getDate('YYYY-MM-DD HH:mm:ss', chat.sendAt)}</span>
        </div>
        <div className={styles.chat_message}>{chat.message}</div>
      </div>
    );
  }
};

export default ChatMessage;
