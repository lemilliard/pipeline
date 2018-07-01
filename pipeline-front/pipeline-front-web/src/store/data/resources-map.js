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
  SCORE: { ws: 'SCORE', res: Resources.SCORES },
  SCORE_ADD: { ws: 'SCORE_ADD', res: Resources.SCORES },
};

