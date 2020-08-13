import Vue from 'vue';
import VueRouter from 'vue-router';

import Home from '@/pages/Home';
import ChatRoom from '@/pages/ChatRoom';

Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  routes: [
    { path: '/', component: Home },
    { path: '/chat-room', component: ChatRoom }
  ]
});

export default router;
