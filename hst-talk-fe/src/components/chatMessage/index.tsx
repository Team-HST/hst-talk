import styles from './style.module.css';

interface ChatMessageProps {
  chat: { type: string; name?: string; message?: string };
}

const ChatMessage = ({ chat }: ChatMessageProps) => {
  if (chat.type === 'center') {
    return (
      <div className={styles.chat_message_center}>
        <h3>Start joining chat 😀</h3>
        <span>2020-02-03 11:11:11</span>
      </div>
    );
  } else if (chat.type === 'left') {
    return (
      <div className={styles.chat_message_left}>
        <div className={styles.chat_message_user}>
          <h3>{chat.name}</h3>
          <span>21:20:11</span>
        </div>
        <div className={styles.chat_message}>
          긴그으으으으으으으으으으으으으으으으으으으으으으으으으으을
        </div>
      </div>
    );
  } else {
    return (
      <div className={styles.chat_message_right}>
        <div className={styles.chat_message_user}>
          <h3>{chat.name}</h3>
          <span>21:20:11</span>
        </div>
        <div className={styles.chat_message}>{chat.message}</div>
      </div>
    );
  }
};

export default ChatMessage;
