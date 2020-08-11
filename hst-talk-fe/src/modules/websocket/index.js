export default {
  socket: null,
  connect(host, {onopen, onmessage}) {
    this.socket = new WebSocket(host);
    this.socket.onopen = onopen;
    this.socket.onmessage = onmessage;
  },
  send(messageType, body) {
    if (!this.socket) {
      throw new Error('Connection not established!');
    }
    console.log(messageType, body)
    this.socket.send(JSON.stringify({messageType, body}));
  }
}