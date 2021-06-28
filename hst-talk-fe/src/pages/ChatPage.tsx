import styles from 'resources/css/chat.module.css';

const ChatPage = () => {
  return (
    <div className={styles.wrapper}>
      <div className={`${styles.chat_info} ${styles.on}`}>
        <div className={styles.chat_info_header}>
          <h2>HST </h2>
          <span>Talk</span>
        </div>
        <div className={styles.chat_info_body}>
          <div>🟢 김영후이</div>
          <div>🟢 이현규이</div>
        </div>
        <div className={styles.chat_info_footer}>
          <h3>
            Room Code{' '}
            <i className={`far fa-copy ${styles.chat_info_footer_copy}`} title="code copy"></i>
          </h3>
          <div>hst_yh_sg123dvxw</div>
        </div>
      </div>
      <div className={styles.chat_box}></div>
    </div>
  );
};

export default ChatPage;
