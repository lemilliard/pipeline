import RouteNames from '@/router/names';
import Icons from './icons';

const globalPlugin = {
  install(Vue, options) {
    // Tools
    /**
     * Retrieve routes list from router instance
     * @returns {*}
     */
    const getRoutes = () => {
      if (options.router && options.router.options && options.router.options.routes) {
        return options.router.options.routes;
      }
      return null;
    };
    /**
     * Verify if route name exists in the current router instance
     * @param routeName
     * @returns {boolean}
     */
    const routeExists = (routeName) => {
      let exists = false;
      const routes = getRoutes();
      if (routes) {
        let i = 0;
        while (i < routes.length && !exists) {
          if (routes[i].name === routeName) {
            exists = true;
          }
          i += 1;
        }
      }
      return exists;
    };
    /**
     * global object
     * Contains global and fields used in the page editor and page display
     * @type {{}}
     */
    const global = { icons: null, openRoute: null };
    global.icons = {};
    /**
     * Adding icons
     */
    Object.keys(Icons).forEach((key) => {
      global.icons[key] = Icons[key];
    });
    /**
     * Open a route with the given name
     * @param route
     */
    global.openRoute = (routeName) => {
      if (routeExists(routeName)) {
        options.router.push({ name: routeName });
        return;
      }
      options.router.push({ name: RouteNames.ERROR_404 });
    };
    /**
     * Add this plugin to Vue instance
     * @type {{}}
     */
    Vue.prototype.$global = global;
  },
};

export default globalPlugin;
