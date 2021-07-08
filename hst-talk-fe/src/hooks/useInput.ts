import { useState, useCallback, FormEvent } from 'react';

const useInput = <T>(
  init: T
): [T, (e: FormEvent<HTMLInputElement>) => void, (value: T) => void] => {
  const [value, setValue] = useState<T>(init);

  const handle = useCallback((e) => {
    setValue(e.target.value);
  }, []);

  return [value, handle, setValue];
};

export default useInput;
