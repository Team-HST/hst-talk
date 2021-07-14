import { useEffect } from 'react';
import styles from './style.module.css';

interface ToastAlarmProps {
  text: string;
  duration: number;
  onClose: () => void;
}

const ToastAlarm = ({ text, duration, onClose }: ToastAlarmProps) => {
  useEffect(() => {
    const timeout = setTimeout(() => {
      onClose();
    }, duration);

    return () => {
      clearTimeout(timeout);
    };
  }, [duration, onClose]);

  return (
    <div className={styles.wrapper}>
      <span>{text}</span>
    </div>
  );
};

export default ToastAlarm;
