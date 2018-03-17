import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/components/Home';
import Matchs from '@/components/Matchs';
import Match from '@/components/Match';
import Calendrier from '@/components/Calendrier';
import Classement from '@/components/Classement';
import Settings from '@/components/Settings';
import About from '@/components/About';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home,
    },
    {
      path: '/matchs',
      name: 'Matchs',
      component: Matchs,
    },
    {
      path: '/match',
      name: 'Match',
      component: Match,
    },
    {
      path: '/classement',
      name: 'Classement',
      component: Classement,
    },
    {
      path: '/calendrier',
      name: 'Calendrier',
      component: Calendrier,
    },
    {
      path: '/settings',
      name: 'Settings',
      component: Settings,
    },
    {
      path: '/about',
      name: 'About',
      component: About,
    },
  ],
});
