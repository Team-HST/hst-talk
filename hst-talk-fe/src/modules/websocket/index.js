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
      EventBus.$emit(messageData.messageType, messageData.body);
      this.log(`Receive message type: ${messageData.messageType}, body: ${messageData.body}`);    
    };
    this.log(`connected server on ${host}`);    
  },
  disconnect() {
    _socket.close();
    _socket = undefined;
  },
  send(messageType, body) {
    if (!_socket) {
      throw new Error('Connection not established!');
    }
    _socket.send(JSON.stringify({messageType, body}));
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