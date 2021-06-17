import Vue from 'vue';

let _socket;
let _enableLogging;

const EventBus = new Vue();

export default {
  connect(host, {onopen, enableLogging}) {
    _enableLogging = enableLogging;
    _socket = new WebSocket(host);
    _socket.onopen = onopen;
    _socket.onmessage = (message) => {
      const messageData = JSON.parse(message.data);
      EventBus.$emit(messageData.messageType, messageData);
      this.log(`Receive message type: ${messageData.messageType}, payload: ${messageData.payload}`);
    };
    this.log(`connected server on ${host}`);    
  },
  disconnect() {
    _socket.close();
    _socket = undefined;
  },
  send(messageType, roomId, payload) {
    if (!_socket) {
      throw new Error('Connection not established!');
    }
    let message = JSON.stringify({messageType, roomId, payload});
    this.log(`Send message ${message}`)
    _socket.send(message);
  },
  on(messageType, handler) {
    EventBus.$on(messageType, handler);
  },
  log(message) {
    if (_enableLogging) {
      console.log(`[WS Connector] ${message}`);
    }
  }
}