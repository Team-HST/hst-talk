import ChatUserList from 'components/chatUserList';
import ChatMessage from 'components/chatMessage';
import styles from 'resources/css/chat.module.css';
import sendIcon from 'resources/images/send.png';

const chatMessageDummy = [
  { type: 'center' },
  { type: 'left', name: 'jemmy', message: 'hello my name' },
  { type: 'right', name: 'sancho', message: 'ohh hi' },
];

const ChatPage = () => {
  return (
    <div className={styles.wrapper}>
      <div className={styles.chat_header}>
        <h2>
          HST <span>Talk</span>
        </h2>
        <div className={styles.chat_end}>
          <i className="fas fa-door-open fa-lg"></i> Close
        </div>
      </div>
      <div className={styles.chat_body}>
        <div className={`${styles.chat_info} ${styles.on}`}>
          <ChatUserList />
          <div className={styles.chat_info_footer}>
            <h3>
              Room Code{' '}
              <i className={`far fa-copy ${styles.chat_info_footer_copy}`} title="code copy"></i>
            </h3>
            <div>hst_yh_sg123dvxw</div>
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
    </div>
  );
};

export default ChatPage;
