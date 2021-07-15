import { User } from 'types/user';
import styles from './style.module.css';

interface ChatUserItemProps {
  user: User;
}

const ChatUserItem = ({ user }: ChatUserItemProps) => {
  return (
    <li className={styles.info_user}>
      <div className={styles.info_user_nm}>@ {user.nickname}</div>
      {user.id === sessionStorage.getItem('id') && <div className={styles.info_user_me}>me</div>}
    </li>
  );
};

export default ChatUserItem;
