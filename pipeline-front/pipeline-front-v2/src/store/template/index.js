import Actions from './actions';
import Mutations from './mutations';

const TemplateStore = {
  state: {
    drawer: true,
    progressBar: false,
    loginDialog: false,
  },
  actions: Actions,
  mutations: Mutations,
};

export default TemplateStore;
