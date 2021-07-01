import ChatUserItem from '../chatUserItem';
import styles from './style.module.css';

const userDummy = ['김영후이', '이현규0808', '임프리잉'];

const ChatUserList = () => {
  return (
    <ul className={styles.info_body}>
      {userDummy.map((user) => (
        <ChatUserItem name={user} />
      ))}
    </ul>
  );
};

export default ChatUserList;
