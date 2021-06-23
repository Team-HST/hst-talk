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
      <chat-window :owner="owner" :user="user" :chats="chats" :participants="participants"></chat-window>
    </v-container>
  </v-app>
</template>

<script>
import { mapState } from 'vuex';
import ws from '@/modules/websocket';
import { GET_ROOM_MEMBER_LIST, ENTER_ROOM } from '@/modules/websocket/message-types';

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
    },
  },
  data() {
    return {
      owner: {},
      participants: [],
      chats: []
    }
  },
  computed: {
    ...mapState(['user'])
  },
  created() {
    ws.on(GET_ROOM_MEMBER_LIST, (body) => {
      let res = body.payload;
      this.owner = res.owner;
      this.participants = this.participants.concat(res.participants);
    });
    ws.on(ENTER_ROOM, (body) => {
      // 아래 payload엔 방에 입장한 유저 정보가 담겨있다. (자신일 수도 있음) ChatWindow 부분에 노출시켜줘야함
      // 현재 이슈는, ChatWindow에서 입장 메시지를 띄워주는데, 그 부분을 제거하고
      // 여기서 전담할지, 아니면 입장 메시지 부분 수용하고 여기는 순수 타 유저 입장 처리만 할지 결정하고 나서야
      // 이부분 로직을 결정지을수 있음
      let enteredUser = body.payload;
      ws.send(GET_ROOM_MEMBER_LIST, this.roomId);
    });
    ws.send(ENTER_ROOM, this.roomId, { id: this.user.id });
  }
}
</script>

<style>

</style>