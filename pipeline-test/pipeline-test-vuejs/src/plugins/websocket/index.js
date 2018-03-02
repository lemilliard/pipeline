/* eslint-disable no-param-reassign,no-console */

const websocketPlugin = {
  install(Vue, url) {
    function WebSocketPlugin() {
      const ws = new WebSocket(url);
      let data = [];

      ws.onopen = (event) => {
        console.log(event);
      };

      ws.onmessage = (event) => {
        console.log(event);
      };

      ws.onerror = (event) => {
        console.log(event);
      };

      ws.onclose = (event) => {
        console.log(event);
      };

      this.addDatum = (type, datum) => {
        if (!data[`${type}`]) {
          data.push(`${type}`);
          data[`${type}`] = [];
        }
        data[`${type}`].push(datum);
      };

      // PRIVATE FUNCTIONS
      this.displayData = () => {
        console.log(data);
      };

      this.getData = () => data;

      this.getDatum = (type, field, value) => {
        if (data[`${type}`]) {
          return data[`${type}`].filter(datum => datum[`${field}`] === value)[0];
        }
        return null;
      };

      this.resetData = () => {
        data = null;
      };
    }

    Vue.prototype.$websocket = new WebSocketPlugin();
  },
};

export default websocketPlugin;
