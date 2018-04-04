import Vue from 'vue';
import Router from 'vue-router';
import Home from '@/components/page/Home';
import Matchs from '@/components/page/match/Matchs';
import Match from '@/components/page/match/Match';
import Calendrier from '@/components/page/Calendrier';
import Classement from '@/components/page/Classement';
import Settings from '@/components/page/Settings';
import About from '@/components/page/About';

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
