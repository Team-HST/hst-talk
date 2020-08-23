<template>
  <v-app>
    <v-container>
      <h1>ChatRoom!</h1>
      <h2>RoomId: {{roomId}} <span>copy this!</span></h2>
      <h3>Room Owner Id: {{ownerId}}</h3>
      <h3>My Id: {{userId}}</h3>      
      <template v-for="participant in participants">
        {{participant}}
      </template>
      <!-- ChatWindow -->
      <chat-window :ownerId="ownerId" :userId="userId" :participants="participants"></chat-window>
    </v-container>
  </v-app>
</template>

<script>
import { mapState } from 'vuex';
import ws from '@/modules/websocket';
import { GET_ROOM_MEMBER_LIST } from '@/modules/websocket/message-types';

import ChatWindow from '@/components/chatroom/ChatWindow';

export default {
  name:  'ChatRoom',
  components: {
    ChatWindow
  },
  props: {
    roomId: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      ownerId: '',
      participants: []
    }
  },
  computed: {
    ...mapState(['userId'])
  },
  created() {
    ws.send(GET_ROOM_MEMBER_LIST, this.roomId);
    ws.on(GET_ROOM_MEMBER_LIST, (body) => {
      this.ownerId = body.owner;
      this.participants.push({id: this.ownerId, isOwner: true});
    });
  }
}
</script>

<style>

</style>