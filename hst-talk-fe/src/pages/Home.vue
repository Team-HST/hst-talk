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
  </v-app>  
</template>

<script>
import { mapState, mapMutations } from 'vuex';
import ws from '@/modules/websocket';
import { CREATE_ROOM, ENTER_ROOM } from '@/modules/websocket/message-types';
import CreateRoomPopup from '@/components/popup/CreateRoomPopup';
import { page } from '@/mixins';


export default {
  name: 'Home',
  components: { CreateRoomPopup },
  mixins: [ page ],
  data() {
    return {
      userId: '',
      connected: false
    }
  },
  computed: {
    ...mapState(['user'])
  },
  created() {
    this.initializePopup(['createRoomPopup']);
    ws.on(CREATE_ROOM, (data) => {
      this.movePage(`/chats/${data.roomId}`);
    });
    ws.on(ENTER_ROOM, (roomId) => {
      this.movePage(`/chats/${roomId}`);
    });
  },
  methods: {
    ...mapMutations(['setUserId']),    
    disconnectServer() {
      ws.disconnect();
      this.connected = false;
    },
    requestCreateChatRoom() {
      ws.send(CREATE_ROOM);
    }
  }
}
</script>