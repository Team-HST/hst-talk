<template>
  <v-app>
    <!-- <v-app-bar app color="primary" dark>
      <v-toolbar-title>HST TALK</v-toolbar-title>
    </v-app-bar> -->

    <!-- <v-main> -->
      <v-container class="pa-0 fill-height" fluid>
        <!-- <v-row>
          <h2>Connection: <v-chip :color="connectionStatus.color" v-text="connectionStatus.text"></v-chip></h2>
        </v-row> -->
        
        <v-row class="pa-0 fill-height" align="center" justify="center">
          <v-col class="pa-0 fill-height" cols="6">
            <v-row class="pa-0 ma-0 fill-height" align="center" justify="center">
              <v-btn class="text-h4" color="light-blue" height="100%" block x-large dark>Create Link</v-btn>
            </v-row>
          </v-col>
          <v-spacer></v-spacer>
          <v-col class="pa-0 ma-0 fill-height" cols="6">
            <v-row class="pa-0 ma-0 fill-height" align="center" justify="center">
              <v-btn class="text-h4" color="teal" height="100%" block x-large dark>Enter</v-btn>
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    <!-- </v-main> -->
  </v-app>
</template>

<script>
import ws from '@/modules/websocket';
// import AppCard from '@/components/app/AppCard';

export default {
  name: 'App',
//  components: { AppCard },
  data() {
    return {
      logs: [],
      connected: false
    }
  },
  computed: {
    connectionStatus() {
      return this.connected ? { color: 'green', text: 'connected!' } : { color: 'red', text: 'disconnected..' }
    }
  },
  created() {
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
      })
    },
    sendMessage(messageType) {
        ws.send(messageType, 'hihi!!');
    }
  }
};
</script>