import Types from './types';
import MutationTypes from '../mutations/types';

export default {
  [Types.SWITCH_DRAWER]({ commit }) {
    commit(MutationTypes.SWITCH_DRAWER);
  },
  [Types.SWITCH_PROGRESSBAR]({ commit }) {
    commit(MutationTypes.SWITCH_PROGRESSBAR);
  },
};
