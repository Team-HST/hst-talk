import ChatUserItem from '../chatUserItem';
import { User } from 'types/user';
import styles from './style.module.css';

interface ChatUserListProps {
  userList: User[];
}

const ChatUserList = ({ userList }: ChatUserListProps) => {
  return (
    <ul className={styles.info_body}>
      {userList.map((user) => (
        <ChatUserItem key={user.id} user={user} />
      ))}
    </ul>
  );
};

export default ChatUserList;
