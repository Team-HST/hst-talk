import useInput from 'hooks/useInput';
import styles from './style.module.css';

interface EnterModalProps {
  isDisplay: boolean;
  enterRoom: (roomId: string) => void;
  onClose: () => void;
}

const EnterModal = ({ isDisplay, enterRoom, onClose }: EnterModalProps) => {
  const [text, setText] = useInput<string>('');

  const onKeypress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      enterRoom(text);
    }
  };
  return (
    <div className={`${styles.backgrond} ${isDisplay ? styles.on : ''}`}>
      <div className={styles.wrapper}>
        <div className={styles.header}>
          <h2>Room code</h2>
          <i className="fas fa-times fa-lg" onClick={onClose}></i>
        </div>
        <input
          type="text"
          placeholder="enter room code"
          value={text}
          onChange={setText}
          onKeyPress={onKeypress}
        />
      </div>
    </div>
  );
};

export default EnterModal;
