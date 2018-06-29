import CodePostaux from 'codes-postaux';

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

    global.getVille = (codePostal) => {
      const villes = CodePostaux.find(codePostal);
      if (villes && villes.length > 0 && villes[0] && villes[0].nomCommune) {
        return villes[0].nomCommune;
      }
      return null;
    };

    global.getAdresseVille = (adresse) => {
      if (adresse) {
        const codePostal = adresse.codePostal;
        if (codePostal) {
          return global.getVille(codePostal);
        }
      }
      return null;
    };

    global.getComplexeVille = (complexe) => {
      if (complexe) {
        const adresse = complexe.adresse;
        if (adresse) {
          return global.getAdresseVille(adresse);
        }
      }
      return null;
    };

    global.getTournamentVille = (tournament) => {
      if (tournament) {
        const complexe = tournament.complexe;
        if (complexe) {
          return global.getComplexeVille(complexe);
        }
      }
      return null;
    };

    /**
     * Add this plugin to Vue instance
     * @type {{}}
     */
    Vue.prototype.$global = global;
  },
};

export default globalPlugin;
