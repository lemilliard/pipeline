import Getters from './getters';
import Actions from './actions';
import Mutations from './mutations';
import Resources from './resources';

const DataStore = {
  state: {
    [Resources.USERS.name]: [],
    [Resources.CURRENT_USER.name]: {
      [Resources.CURRENT_USER.id]: null,
      email: null,
      password: null,
      nom: null,
      prenom: null,
      roleName: null,
      role: null,
    },
    [Resources.ROLES.name]: [],
    [Resources.TOURNAMENTS.name]: [],
    [Resources.MATCHS.name]: [],
  },
  getters: Getters,
  actions: Actions,
  mutations: Mutations,
};

export default DataStore;
