<template>
  <div>
    <v-card class="mx-auto" height="600">
      <v-toolbar color="primary" dark flat>
        <v-toolbar-title>
          <b>Take me there</b>
        </v-toolbar-title>
      </v-toolbar>
      <v-card-text>
        <v-row justify="center">
          <!-- 채팅창 -->
          <v-col cols="8">
            <v-list shaped height="420">
              <chat-item v-for="(chat, index) in chats" :key="`${chat}-${index}`" :chat="chat" />
            </v-list>
            <v-divider></v-divider>
            <v-text-field v-model="chatMessage" @keyup.enter="addChat(userId, chatMessage);" >
              <v-icon slot="append" color="primary" @click="addChat(userId, chatMessage);">mdi-send</v-icon>
            </v-text-field>            
          </v-col>
          <v-spacer></v-spacer>
          <v-divider vertical></v-divider>          
          <v-spacer></v-spacer>          
          <!-- 참여자 목록 -->
          <v-col cols="3">
            <v-list height="420">
              <v-list-item v-for="(participant, index) in participants" :key="`${participant}-${index}`">
                {{participant.id}} <small>{{participant.isOwner ? '방장' : ''}}</small>
              </v-list-item>
            </v-list>
          </v-col>          
        </v-row>
      </v-card-text>
    </v-card>
  </div>
</template>

<script>
// import ws from '@/modules/websocket';
import ChatItem from './ChatItem';

export default {
  name: 'ChatWindow',
  components: {
    ChatItem
  },
  props: {
    userId: String,
    participants: Array
  },
  data() {
    return {
      chatMessage: '',
      chats: []
    }
  },
  created() {
    this.addChat('SYSTEM', `${this.userId}님이 입장하셨습니다.`);
  },
  methods: {
    addChat(sender, message) {
      this.chats.push({ sender, message, sendAt: new Date() });
      this.chatMessage = '';
    }
  }
}
</script>

<style>

</style>