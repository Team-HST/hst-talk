import styles from 'resources/css/home.module.css';

const MainPage = () => {
  return (
    <div className={styles.wrapper}>
      <div className={styles.create_room}>Create Room</div>
      <div className={styles.enter_room}>Enter Room</div>
    </div>
  );
};

export default MainPage;
