/* eslint-disable no-param-reassign,no-console */

const websocketPlugin = {
  install(Vue, url) {
    function WebSocketPlugin() {
      const ws = new WebSocket(url);
      let repo = [];

      // PRIVATE METHODS
      /**
       *
       * @param event
       */
      const processOnOpen = () => {
        console.log('Connected');
      };

      /**
       *
       * @param event
       */
      const processOnClose = () => {
        console.log('Disconnected');
      };

      /**
       *
       * @param event
       */
      const processOnError = (event) => {
        console.log(event);
      };

      /**
       *
       * @param event
       */
      const processOnMessage = (event) => {
        if (event && event.data) {
          console.log(event);
          const data = JSON.parse(event.data);
          console.log(data);
        }
      };

      // const saveEntityToRepo = (type, entity) => {
      //   // Add new type
      //   if (!repo[`${type}`]) {
      //     repo.push(`${type}`);
      //     repo[`${type}`] = [];
      //     // Adding new entity to this type
      //     repo[`${type}`].push(entity);
      //   }
      // };

      /**
       *
       * @param callback
       */
      const waitForWebSocketConnection = (callback) => {
        setTimeout(
          () => {
            if (ws.readyState === 1) {
              if (callback != null) {
                callback();
              }
            } else {
              waitForWebSocketConnection(callback);
            }
          }, 5);
      };

      /**
       * Use this instead of ws.send() to avoid not connected state
       * @param message
       */
      const sendToWebSocket = (message) => {
        waitForWebSocketConnection(() => ws.send(message));
      };

      /**
       *
       * @param method
       * @param resource
       * @param params
       * @param body
       * @returns {string}
       */
      const constructRequest = (method, resource, params, body) => {
        const request = {
          method,
          resource,
          params,
          body,
        };
        return JSON.stringify(request);
      };

      // PUBLIC METHODS
      /**
       *
       * @param type
       * @param key
       * @param value
       */
      this.get = (type, key, value) => {
        let request;
        if (key && value) {
          request = constructRequest('GET', `${type}/{${key}}`, { [key]: value.toString() });
        } else {
          request = constructRequest('GET', `${type}`);
        }
        sendToWebSocket(request);
      };

      /**
       *
       * @returns {Array}
       */
      this.getRepo = () => repo;

      /**
       *
       * @param type
       * @param field
       * @param value
       * @returns {null}
       */
      this.getFromRepo = (type, key, value) => {
        if (repo[`${type}`]) {
          return repo[`${type}`].filter(entity => entity[`${key}`] === value)[0];
        }
        return null;
      };

      /**
       *
       */
      this.resetRepo = () => {
        repo = [];
      };

      // DEFINING WEBSOCKET EVENT HANDLERS
      ws.onopen = (event) => {
        processOnOpen(event);
      };

      ws.onclose = (event) => {
        processOnClose(event);
      };

      ws.onerror = (event) => {
        processOnError(event);
      };

      ws.onmessage = (event) => {
        processOnMessage(event);
      };
    }

    Vue.prototype.$websocket = new WebSocketPlugin();
  },
};

export default websocketPlugin;
