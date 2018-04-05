import Resources from '@/store/data/resources';

export default {
  USER: { ws: 'USER', res: Resources.USER },
  USERS: { ws: 'USERS', res: Resources.USER },
  CONNECT: { ws: 'CONNECT', res: Resources.CURRENT_USER },
  CONNECT_BY_ID: { ws: 'CONNECT_BY_ID', res: Resources.CURRENT_USER },
  REGISTER: { ws: 'REGISTER', res: Resources.CURRENT_USER },
};
