import { UserInfo } from 'types/chat';
import styles from './style.module.css';

interface ChatUserItemProps {
  user: UserInfo;
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
