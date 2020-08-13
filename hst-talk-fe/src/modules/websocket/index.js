let _socket;
// let _enableLogging;

export default {
  connect(host, {onopen, onmessage}) {
    _socket = new WebSocket(host);
    _socket.onopen = onopen;
    _socket.onmessage = onmessage;
  },
  send(messageType, body) {
    if (!_socket) {
      throw new Error('Connection not established!');
    }
    _socket.send(JSON.stringify({messageType, body}));
  },
  disconnect() {
    _socket.close();
    _socket = undefined;
  }
}