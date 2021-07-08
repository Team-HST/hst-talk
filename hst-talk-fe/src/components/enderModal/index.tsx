import styles from './style.module.css';

const EnterModal = () => {
  return (
    <div className={styles.backgrond}>
      <div className={styles.wrapper}>
        <div className={styles.header}>
          <h2>Room code</h2>
          <i className="fas fa-times fa-lg"></i>
        </div>
        <input type="text" placeholder="enter room code" />
      </div>
    </div>
  );
};

export default EnterModal;
