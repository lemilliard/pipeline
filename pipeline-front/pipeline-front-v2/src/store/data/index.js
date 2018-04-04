import Resources from './resources';
import Getters from './getters';
import Actions from './actions';
import Mutations from './mutations';

const DataStore = {
  state: {
    [Resources.USER]: null,
    [Resources.USERS]: [],
  },
  getters: Getters,
  actions: Actions,
  mutations: Mutations,
};

export default DataStore;
