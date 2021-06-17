<template>
  <v-app>
    <v-container>
      <h1>Chats!</h1>
      <!-- debug 용 -->
      <h5>RoomId: {{roomId}}</h5>
      <h5>Room Owner Info: {{user}}</h5>
      <h5>My Info: {{user}}</h5>      
      <h6>참가자 json</h6>
      <template v-for="participant in participants">
        {{participant}}
      </template>
      <!-- debug 용 -->
      <!-- ChatWindow -->
      <chat-window :owner="owner" :user="user" :participants="participants"></chat-window>
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
      owner: {},
      participants: []
    }
  },
  computed: {
    ...mapState(['user'])
  },
  created() {
    ws.on(GET_ROOM_MEMBER_LIST, (body) => {
      console.log(body);
      this.owner = body.owner;
      this.participants = this.participants.concat(body.participants);
    });
    ws.send(GET_ROOM_MEMBER_LIST, this.roomId);
  }
}
</script>

<style>

</style>