import useInput from 'hooks/useInput';
import React from 'react';
import styles from './style.module.css';

const NicknameInput = () => {
  const [text, setText, changeText] = useInput<string>('');

  const onKeypress = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === 'Enter') {
      // 닉네임전달 및 소켓 연결 처리
      changeText('');
    }
  };

  return (
    <input
      className={styles.input}
      type="text"
      value={text}
      onChange={setText}
      onKeyPress={(e) => onKeypress(e)}
      placeholder="Enter your nickname"
    />
  );
};

export default NicknameInput;
