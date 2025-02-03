export const dateFormat = function (date: Date, display_dot = false) {
  // Get individual date components
  const year = date.getFullYear().toString().slice(-2);
  const month = ('0' + (date.getMonth() + 1)).slice(-2);
  const day = ('0' + date.getDate()).slice(-2);
  return display_dot ? `${year}.${month}.${day}` : `${year}/${month}/${day}`;;
};

export const timeFormat = function (date: Date) {
  const hours = date.getHours();
  const minutes = ('0' + date.getMinutes()).slice(-2);
  const seconds = ('0' + date.getSeconds()).slice(-2);

  return `${hours}:${minutes}:${seconds}`;
};
