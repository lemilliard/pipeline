/* eslint-disable no-param-reassign,no-console,no-unused-vars */
import Store from '@/store';
import MutationsTypes from '@/store/data/mutations/types';
import Methods from './request-methods';

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
        console.log('WebSocket connected');
      };

      /**
       *
       * @param event
       */
      const processOnClose = () => {
        console.log('WebSocket disconnected');
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
        console.log(event);
        if (event && event.data) {
          const data = JSON.parse(event.data);
          Store.commit(MutationsTypes.COMMIT_DATA, { vue: Vue, data });
        }
      };

      /**
       *
       * @param callback
       */
      const waitForWebSocketConnection = (callback) => {
        setTimeout(
          () => {
            if (ws.readyState === 1) {
              if (callback !== null) {
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
        // console.log(message);
        waitForWebSocketConnection(() => ws.send(message));
      };

      const isNotification = (message) => {
        if (message.method) {
          return false;
        }
        return true;
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
       * @param resource
       * @param param
       */
      this.get = (resource, params) => {
        let request;
        if (params) {
          request = constructRequest(Methods.GET, `${resource}`, params);
        } else {
          request = constructRequest(Methods.GET, `${resource}`);
        }
        sendToWebSocket(request);
      };

      /**
       *
       * @param resource
       * @param param
       */
      this.post = (resource, body) => {
        if (body) {
          const request = constructRequest(Methods.POST, `${resource}`, null, body);
          sendToWebSocket(request);
        }
      };

      /**
       *
       * @param resource
       * @param param
       */
      this.put = (resource, body) => {
        if (body) {
          const request = constructRequest(Methods.PUT, `${resource}`, null, body);
          sendToWebSocket(request);
        }
      };

      /**
       *
       * @param resource
       * @param param
       */
      this.delete = (resource, params) => {
        let request;
        if (params) {
          request = constructRequest(Methods.DELETE, `${resource}`, params);
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
