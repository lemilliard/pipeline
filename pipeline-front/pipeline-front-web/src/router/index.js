import Vue from 'vue';
import Router from 'vue-router';
import Pages from '@/components/pages';
import Names from './names';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: Names.HOME,
      component: Pages.Home,
    },
    {
      path: '/match/:id',
      name: Names.MATCH,
      component: Pages.Match,
    },
    {
      path: '/matchs',
      name: Names.MATCHS,
      component: Pages.Matchs,
    },
    {
      path: '/ladder',
      name: Names.LADDER,
      component: Pages.Ladder,
    },
    {
      path: '/rankings',
      name: Names.RANKINGS,
      component: Pages.Rankings,
    },
    {
      path: '/settings',
      name: Names.SETTINGS,
      component: Pages.Settings,
    },
    {
      path: '/about',
      name: Names.ABOUT,
      component: Pages.About,
    },
    {
      path: '/profile',
      name: Names.PROFILE,
      component: Pages.Profile,
    },
    {
      path: '/tournaments',
      name: Names.TOURNAMENTS,
      component: Pages.Tournament,
    },
    {
      path: '/addtournament',
      name: Names.ADDTOURNAMENT,
      component: Pages.AddTournament,
    },
    {
      path: '/organiser',
      name: Names.ORGANISER,
      component: Pages.Organiser,
    },
    {
      path: '*',
      redirect: Names.ERROR_404,
    },
    {
      path: '/404',
      name: Names.ERROR_404,
      component: Pages.Redirection.Error404,
    },
  ],
});
