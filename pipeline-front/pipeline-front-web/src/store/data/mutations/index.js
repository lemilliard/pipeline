/* eslint-disable max-len,no-console */
import Resources from '../resources';
import ResourcesMap from '../resources-map';
import Types from './types';

const getResourceByWSResource = wsResource => Object.values(ResourcesMap).find(resourceMap => resourceMap.ws === wsResource);

const addItem = (vue, state, resource, item) => {
  if (item[resource.id]) {
    const index = state[resource.name].findIndex(o => o[resource.id] === item[resource.id]);
    if (index > -1) {
      console.log(`Updating ${item[resource.id]} into ${resource.name}`);
      state[resource.name][index] = item;
    } else {
      console.log(`Pushing ${item[resource.id]} into ${resource.name}`);
      state[resource.name].push(item);
    }
  }
};

const setItem = (vue, state, resource, item) => {
  const cookie = vue.prototype.$cookie;
  state[resource.name] = item;
  console.log(`Setting ${item[resource.id]} into ${resource.name}`);
  if (resource.name === Resources.CURRENT_USER.name && item[Resources.CURRENT_USER.id]) {
    cookie.set('id_user', item[Resources.CURRENT_USER.id]);
  }
};

export default {
  [Types.COMMIT_DATA](state, { vue, data }) {
    // Si c'est une réponse
    if (data.request && data.request.resource && (data.content || data.contents)) {
      const resourceMap = getResourceByWSResource(data.request.resource);
      const resource = resourceMap.res;
      const content = (data.content || data.contents);
      if (Array.isArray(content)) {
        content.forEach((item) => {
          addItem(vue, state, resource, item);
        });
      } else if (Array.isArray(state[resource.name])) {
        addItem(vue, state, resource, content);
      } else {
        setItem(vue, state, resource, content);
      }
      // Si c'est une notification
    } else if (data.registryType) {
      const resourceMap = getResourceByWSResource(data.registryType);
      const resource = resourceMap.res;
      const content = (data.content || data.contents);
      if (Array.isArray(content)) {
        content.forEach((item) => {
          addItem(vue, state, resource, item);
        });
      } else if (Array.isArray(state[resource.name])) {
        addItem(vue, state, resource, content);
      } else {
        setItem(vue, state, resource, content);
      }
    }
  },
  [Types.UPDATE_CURRENT_USER_EMAIL](state, email) {
    state[Resources.CURRENT_USER.name].email = email;
  },
  [Types.UPDATE_CURRENT_USER_PASSWORD](state, password) {
    state[Resources.CURRENT_USER.name].password = password;
  },
  [Types.UPDATE_CURRENT_USER_NOM](state, nom) {
    state[Resources.CURRENT_USER.name].nom = nom;
  },
  [Types.UPDATE_CURRENT_USER_PRENOM](state, prenom) {
    state[Resources.CURRENT_USER.name].prenom = prenom;
  },
  [Types.RESET_CURRENT_USER](state) {
    state[Resources.CURRENT_USER.name] = {
      [Resources.CURRENT_USER.id]: null,
      email: null,
      password: null,
      nom: null,
      prenom: null,
      role: null,
    };
  },
};
