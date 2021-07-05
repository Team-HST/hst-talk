import moment from 'moment';

const DateUtils = {
  getDate: (format: string = 'YYYY-MM-DD'): string => {
    return moment().format(format);
  },
};

export default DateUtils;
