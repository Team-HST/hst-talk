import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    connected: false,
    user: {}
  },
  mutations: {
    setConnected: (state, connected) => {
      state.connected = connected;
    },
    setUser: (state, user) => {
      state.user = user;
    }
  }
});