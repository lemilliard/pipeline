import Types from './types';

export default {
  [Types.SWITCH_DRAWER](state) {
    state.drawer = !state.drawer;
  },
  [Types.SWITCH_PROGRESSBAR](state) {
    state.progressBar = !state.progressBar;
  },
};
