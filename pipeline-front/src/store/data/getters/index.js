/* eslint-disable max-len */
import Types from './types';
import Resources from '../resources';

export default {
  [Types.IS_CONNECTED](state) {
    return state[Resources.CURRENT_USER.name] && state[Resources.CURRENT_USER.name].id_user !== null;
  },
  [Types.IS_ADMIN](state) {
    return [Types.IS_CONNECTED] && state[Resources.CURRENT_USER.name].role && state[Resources.CURRENT_USER.name].role.valeur === 'Admin';
  },
};
