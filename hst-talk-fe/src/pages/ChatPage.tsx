import styles from 'resources/css/chat.module.css';

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
          <div className={styles.chat_info_body}>
            <div className={styles.chat_info_user}>
              <div className={styles.chat_info_user_nm}>@ 김영후이dwdwdwdwdwdw</div>
              <div className={styles.chat_info_user_me}>me</div>
            </div>
            <div className={styles.chat_info_user}>
              <div className={styles.chat_info_user_nm}>@ 이현규</div>
            </div>
          </div>
          <div className={styles.chat_info_footer}>
            <h3>
              Room Code{' '}
              <i className={`far fa-copy ${styles.chat_info_footer_copy}`} title="code copy"></i>
            </h3>
            <div>hst_yh_sg123dvxw</div>
          </div>
        </div>
        <div className={styles.chat_box}>
          <div className={styles.chat_box_body}>dwdwdw</div>
        </div>
      </div>
    </div>
  );
};

export default ChatPage;
