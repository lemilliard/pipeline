/* eslint-disable max-len */
import ResourcesMap from '../resources-map';
import Types from './types';

const getResourceByWSResource = wsResource => Object.values(ResourcesMap).find(resourceMap => resourceMap.ws === wsResource);

const addItem = (state, item, resource) => {
  if (item[resource.id]) {
    const index = state[resource.name].findIndex(o => o[resource.id] === item[resource.id]);
    if (index > -1) {
      console.log(`Updating ${item[resource.id]}`);
      state[resource.name][index] = item;
    } else {
      console.log(`Pushing ${item[resource.id]}`);
      state[resource.name].push(item);
    }
  }
};

export default {
  [Types.UPDATE_DATA](state, data) {
    if (data.request && data.request.resource) {
      const resourceMap = getResourceByWSResource(data.request.resource);
      const resource = resourceMap.res;
      const content = data.content;
      if (Array.isArray(content)) {
        content.forEach((item) => {
          addItem(state, item, resource);
        });
      } else {
        addItem(state, content, resource);
      }
    }
  },
};
