<template>
  <v-app>
    <v-container class="pa-0 fill-height" fluid>
      <v-row class="pa-0 fill-height" align="center" justify="center">
        <v-col class="pa-0 fill-height" cols="6">
          <v-row class="pa-0 ma-0 fill-height" align="center" justify="center">
            <v-btn class="text-h4" color="light-blue" height="100%" tile block x-large dark @click="ui.createRoomPopupVisible = true">Create Room</v-btn>
          </v-row>
        </v-col>
        <v-spacer></v-spacer>
        <v-col class="pa-0 ma-0 fill-height" cols="6">
          <v-row class="pa-0 ma-0 fill-height" align="center" justify="center">
            <v-btn class="text-h4" color="teal darken-5" height="100%" tile block x-large dark>Enter Room</v-btn>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <create-room-popup :visible="ui.createRoomPopupVisible" />
    <v-btn :color="connectionStatus.color" v-text="connectionStatus.text" @click="toggleServerConnection();"></v-btn>
  </v-app>  
</template>

<script>
import ws from '@/modules/websocket';

import CreateRoomPopup from '@/components/popup/CreateRoomPopup';

export default {
  name: 'Home',
  components: { CreateRoomPopup },
  data() {
    return {
      logs: [],
      connected: false,
      ui: {
        createRoomPopupVisible: false
      }      
    }
  },
  computed: {
    connectionStatus() {
      return this.connected ? { color: 'green', text: 'connected!' } : { color: 'red', text: 'disconnected..' }
    }
  },
  created() {
    // this.$router.push('/chat-room');
    this.connectServer();
  },
  methods: {
    connectServer() {
      ws.connect('ws://localhost:8000/ws/chat', {
        onopen: () => {
          this.connected = true;
        },
        onmessage: (data) => {
          console.log(data)
        }
      });      
    },
    disconnectServer() {
      ws.disconnect();
      this.connected = false;
    },
    toggleServerConnection() {
      if (this.connected) {
        this.disconnectServer();
      } else {
        this.connectServer();
      }
    },
    sendMessage(messageType) {
        ws.send(messageType, 'hihi!!');
    }
  }
}
</script>