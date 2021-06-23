import Vue from 'vue';
import VueRouter from 'vue-router';

import Entrance from '@/pages/Entrance';
import Home from '@/pages/Home';
import ChatRoom from '@/pages/ChatRoom';

Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  routes: [
    { path: '/', name: 'entrance', component: Entrance },
    { path: '/home', name:'Home', component: Home },
    { path: '/chats/:roomId', name: 'ChatRoom', component: ChatRoom, props: (route) => ({
      ...route.params
    }) }
  ]
});

export default router;
