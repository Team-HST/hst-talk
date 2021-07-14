import ChatUserItem from '../chatUserItem';
import { UserInfo } from 'types/chat';
import styles from './style.module.css';

interface ChatUserListProps {
  userList: UserInfo[];
}

const ChatUserList = ({ userList }: ChatUserListProps) => {
  return (
    <ul className={styles.info_body}>
      {userList.map((user) => (
        <ChatUserItem user={user} />
      ))}
    </ul>
  );
};

export default ChatUserList;
