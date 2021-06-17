<template>
  <v-app>
    <v-container class="fill-height" fluid>
      {{connected}}
      {{user}}
      <v-row align="center" justify="center">
        <v-col cols="4"> 
          <v-text-field v-model="nickname" label="What your name?">
            <v-icon slot="append" color="green" @click.stop="onClickConnectButton();">mdi-login</v-icon>
          </v-text-field>
        </v-col>
      </v-row>
    </v-container>
  </v-app>
</template>

<script>
import { mapState, mapMutations } from 'vuex';
import ws from '@/modules/websocket';
import { INIT_USER_INFO } from '@/modules/websocket/message-types';
import { page } from '@/mixins';

export default {
  mixins: [ page ],
  data() {
    return {
      nickname: ''
    }
  },
  created() {
    ws.on(INIT_USER_INFO, (response) => {
      this.setUser({
        id: response.payload,
        nickname: this.nickname
      });
      this.movePage('/home');
    });
  },
  computed: {
    ...mapState(['connected', 'user'])    
  },
  methods: {
    ...mapMutations(['setConnected', 'setUser']),
    onClickConnectButton() {
      if (this.nickname === '') {
        alert('Name is required!');
        return;
      }
      this.connectServer();
    },
    connectServer() {
      ws.connect('ws://localhost:8000/ws/chat', {
        onopen: () => {
          this.setConnected(true);
          ws.send(INIT_USER_INFO, null, this.nickname);
        },
        enableLogging: true
      });
    }
  }  
}
</script>

<style>

</style>