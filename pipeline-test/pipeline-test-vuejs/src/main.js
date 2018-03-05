// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue';
import App from './App';
import router from './router';

import WebSocketPlugin from './plugins/websocket';

// Vue.use(WebSocketPlugin, 'ws://home.thomaskint.com:8085/websocket');
Vue.use(WebSocketPlugin, 'ws://localhost:8085/user');

Vue.config.productionTip = false;

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>',
});
