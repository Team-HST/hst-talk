<template>
  <v-app>
    <v-container>
      <h1>ChatRoom!</h1>
      <h2>RoomId: {{roomId}} <span>copy this!</span></h2>
      <h3>Room owner: {{ownerId}}</h3>
      <template v-for="participant in participants">
        {{participant}}
      </template>
      <textarea style="border: 1px solid black;"></textarea>
    </v-container>
  </v-app>
</template>

<script>
import ws from '@/modules/websocket';
import { GET_ROOM_MEMBER_LIST } from '@/modules/websocket/message-types';

export default {
  name:  'ChatRoom',
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
  created() {
    ws.send(GET_ROOM_MEMBER_LIST, this.roomId);
    ws.on(GET_ROOM_MEMBER_LIST, (body) => {
      this.ownerId = body.owner;
    });
  }
}
</script>

<style>

</style>