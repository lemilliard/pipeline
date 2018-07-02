// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import Vuetify from 'vuetify';
import VueCookie from 'vue-cookie';

import store from './store';
import GlobalPlugin from './plugins/global';
import WebSocketPlugin from './plugins/websocket';

import App from './App';
import router from './router';

Vue.use(Vuetify);
Vue.use(VueCookie);
Vue.use(GlobalPlugin, { router });
// Vue.use(WebSocketPlugin, 'ws://localhost:8080', store);
// Vue.use(WebSocketPlugin, 'ws://ae3ab787.ngrok.io', store);
Vue.use(WebSocketPlugin, 'ws://home.thomaskint.com:8085/websocket', store);

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  template: '<App/>',
  components: { App },
});
