/* eslint-disable max-len */
import Vue from 'vue';
import Types from './types';
import MutationsTypes from '../mutations/types';
import DataResourcesMap from '../resources-map';

const vue = new Vue();

export default {
  [Types.RETRIEVE_DATA]({ commit }, { resource, params }) {
    vue.$websocket.get(resource, params);
  },
  [Types.CONNECT]({ commit }, connector) {
    vue.$websocket.post(DataResourcesMap.CONNECT.ws, connector);
  },
  [Types.CONNECT_BY_ID]({ commit }, id) {
    vue.$websocket.get(DataResourcesMap.CONNECT_BY_ID.ws, { [DataResourcesMap.CONNECT_BY_ID.res.id]: id });
  },
  [Types.REGISTER]({ commit }, registrator) {
    vue.$websocket.post(DataResourcesMap.REGISTER.ws, registrator);
  },
  [Types.DISCONNECT]({ commit }) {
    vue.$cookie.delete('id_user');
    commit(MutationsTypes.RESET_CURRENT_USER);
  },
};
