import Vue from 'vue';
import Vuex from 'vuex';
import DataStore from '@/store/data';
import TemplateStore from '@/store/template';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    DataStore,
    TemplateStore,
  },
});
