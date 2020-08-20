<template>
  <v-app>
    <v-container class="pa-0 fill-height" fluid>
      <v-row class="pa-0 fill-height" align="center" justify="center">
        <v-col class="pa-0 fill-height" cols="6">
          <v-row class="pa-0 ma-0 fill-height" align="center" justify="center">
            <v-btn 
              class="text-h4" 
              color="light-blue" 
              height="100%" 
              tile block x-large dark 
              @click="openPopup('createRoomPopup')"
            >
              Create Room
            </v-btn>
          </v-row>
        </v-col>
        <v-spacer></v-spacer>
        <v-col class="pa-0 ma-0 fill-height" cols="6">
          <v-row class="pa-0 ma-0 fill-height" align="center" justify="center">
            <v-btn 
              class="text-h4" 
              color="teal darken-5" 
              height="100%" 
              tile block x-large dark 
              @click="openPopup('createRoomPopup')">
              Enter Room
            </v-btn>
          </v-row>
        </v-col>
      </v-row>
    </v-container>
    <create-room-popup 
      :isShowing="ui.createRoomPopup.isShowing" 
      @ok="requestCreateChatRoom();"
      @close="closePopup('createRoomPopup')" 
    />
    <v-btn :color="connectionStatus.color" v-text="connectionStatus.text" @click="toggleServerConnection();"></v-btn>
  </v-app>  
</template>

<script>
import ws from '@/modules/websocket';
import { CREATE_ROOM } from '@/modules/websocket/message-types';
import CreateRoomPopup from '@/components/popup/CreateRoomPopup';
import { page } from '@/mixins';


export default {
  name: 'Home',
  components: { CreateRoomPopup },
  mixins: [ page ],
  data() {
    return {
      connected: false
    }
  },
  computed: {
    connectionStatus() {
      return this.connected ? { color: 'green', text: 'connected!' } : { color: 'red', text: 'disconnected..' }
    }
  },
  created() {
    this.initializePopup(['createRoomPopup']);
    this.connectServer();
    ws.on(CREATE_ROOM, (body) => {
      this.movePage(`/chat-room/${body}`);
    });
  },
  methods: {
    connectServer() {
      ws.connect('ws://localhost:8000/ws/chat', {
        onopen: () => {
          this.connected = true;
        },
        enableLogging: true
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
    requestCreateChatRoom() {
      ws.send(CREATE_ROOM);
    }
  }
}
</script>