import Types from './types';

export default {
  [Types.IS_CONNECTED](state) {
    return state.data && state.data.user && state.data.user.id_user !== null;
  },
  [Types.IS_ADMIN](state) {
    return [Types.IS_CONNECTED] && state.data.user.role === 'Admin';
  },
};
