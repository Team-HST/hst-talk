import styles from './style.module.css';

interface ChatUserItemProps {
  name: string;
}

const ChatUserItem = ({ name }: ChatUserItemProps) => {
  return (
    <li className={styles.info_user}>
      <div className={styles.info_user_nm}>@ {name}</div>
      <div className={styles.info_user_me}>me</div>
    </li>
  );
};

export default ChatUserItem;
