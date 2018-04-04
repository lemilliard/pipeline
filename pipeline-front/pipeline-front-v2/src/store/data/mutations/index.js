import Types from './types';

export default {
  [Types.UPDATE_DATA](state, data) {
    if (data.request && data.request.resource) {
      state[data.request.resource] = data.content;
    }
  },
};
