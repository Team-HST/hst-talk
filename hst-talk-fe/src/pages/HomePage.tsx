import { useEffect } from 'react';
import NicknameInput from 'components/nicknameInput';
import styles from 'resources/css/main.module.css';

const HomePage = () => {
  useEffect(() => {
    sessionStorage.removeItem('talkName');
    sessionStorage.removeItem('id');
    sessionStorage.removeItem('roomId');
  }, []);

  return (
    <div className={styles.wrapper}>
      <div className={styles.title}>
        <b>HST</b>
        .TOY_PROJECT
      </div>
      <div className={styles.banner}>
        <h1>WELLCOME</h1>
        <h1>HST TALK</h1>
      </div>
      <div className={styles.nickname_input}>
        <NicknameInput />
      </div>
    </div>
  );
};

export default HomePage;
