import moment from 'moment';

const DateUtils = {
  getDate: (format: string = 'YYYY-MM-DD', strDate?: string): string => {
    return moment(strDate ? strDate : null).format(format);
  },
};

export default DateUtils;
