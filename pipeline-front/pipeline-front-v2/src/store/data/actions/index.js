import Vue from 'vue';
import Types from './types';

const vue = new Vue();

export default {
  [Types.RETRIEVE_DATA]({ commit }, { resource, params }) {
    vue.$websocket.get(resource, params);
  },
};
