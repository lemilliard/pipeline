import Resources from '@/store/data/resources';

export default {
  USER: { ws: 'USER', res: Resources.USERS },
  USERS: { ws: 'USERS', res: Resources.USERS },
  UPDATE_USER: { ws: 'UPDATE_USER', res: Resources.CURRENT_USER },
  CONNECT: { ws: 'CONNECT', res: Resources.CURRENT_USER },
  CONNECT_BY_ID: { ws: 'CONNECT_BY_ID', res: Resources.CURRENT_USER },
  REGISTER: { ws: 'REGISTER', res: Resources.CURRENT_USER },
  ROLE: { ws: 'ROLE', res: Resources.ROLES },
  ROLES: { ws: 'ROLES', res: Resources.ROLES },
  TOURNAMENT: { ws: 'TOURNAMENT', res: Resources.TOURNAMENTS },
  TOURNAMENTS: { ws: 'TOURNAMENTS', res: Resources.TOURNAMENTS },
  MATCHS: { ws: 'MATCHS', res: Resources.MATCHS },
  MATCH: { ws: 'MATCH', res: Resources.MATCHS },
  MATCH_PLAY: { ws: 'MATCH_PLAY', res: Resources.MATCHS },
  MATCH_PAUSE: { ws: 'MATCH_PAUSE', res: Resources.MATCHS },
  MATCH_END: { ws: 'MATCH_END', res: Resources.MATCHS },
  SCORE: { ws: 'SCORE', res: Resources.SCORES },
  SCORE_ADD: { ws: 'SCORE_ADD', res: Resources.SCORES },
  ABONNEMENTS: { ws: 'ABONNEMENTS', res: Resources.ABONNEMENTS },
  ABONNEMENT: { ws: 'ABONNEMENT', res: Resources.ABONNEMENTS },
  DELETE_ABONNEMENT: { ws: 'DELETE_ABONNEMENT', res: Resources.ABONNEMENTS },
};

