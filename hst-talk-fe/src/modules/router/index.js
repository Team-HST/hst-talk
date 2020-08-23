import Vue from 'vue';
import VueRouter from 'vue-router';

import Home from '@/pages/Home';
import ChatRoom from '@/pages/ChatRoom';

Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  routes: [
    { path: '/', name:'Home', component: Home },
    { path: '/chat-room/:roomId', name: 'ChatRoom', component: ChatRoom, props: (route) => ({
      ...route.params
    }) }
  ]
});

export default router;
