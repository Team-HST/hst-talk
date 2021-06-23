import Vue from 'vue';
import App from './App.vue';

import vueMoment from 'vue-moment';
import vuetify from './plugins/vuetify';
import router from '@/modules/router';
import store from '@/modules/store'
import moment from 'moment-timezone';


Vue.config.productionTip = false

moment.locale = 'ko';
Vue.use(vueMoment, { moment });

new Vue({
  vuetify,
  router,
  store,
  render: h => h(App)
}).$mount('#app')
