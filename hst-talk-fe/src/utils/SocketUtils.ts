const SocketUtils = {
  getSendMessage: (MessageType: string, roomId?: string, payload?: string) => {
    return `{"messageType": "${MessageType}" ${roomId ? `, "roomId" : "${roomId}"` : ''} ${
      payload ? `, "payload" : "${payload}"` : ''
    }}`;
  },
};

export default SocketUtils;
