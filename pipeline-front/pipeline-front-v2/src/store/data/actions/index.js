import Vue from 'vue';
import Types from './types';
import Resources from '../resources';

const vue = new Vue();

export default {
  [Types.RETRIEVE_DATA]({ commit }, { resource, params }) {
    vue.$websocket.get(resource, params);
  },
  [Types.CONNECT]({ commit }, connector) {
    vue.$websocket.post(Resources.CONNECT, connector);
  },
};
