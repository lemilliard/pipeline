import Getters from './getters';
import Actions from './actions';
import Mutations from './mutations';
import Resources from './resources';

const DataStore = {
  state: {
    [Resources.USER.name]: [],
    [Resources.CURRENT_USER.name]: {
      [Resources.CURRENT_USER.id]: null,
      email: null,
      password: null,
      nom: null,
      prenom: null,
      role: null,
    },
  },
  getters: Getters,
  actions: Actions,
  mutations: Mutations,
};

export default DataStore;
