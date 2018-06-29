<template>
  <div>
    <v-list-tile @click="$global.openRoute(profile)">
      <v-list-tile-title>Profile</v-list-tile-title>
    </v-list-tile>
    <v-list-tile @click="disconnect">
      <v-list-tile-title>Signout</v-list-tile-title>
    </v-list-tile>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import DataActionTypes from '@/store/data/actions/types';
import DataResources from '@/store/data/resources';
import RouteNames from '@/router/names';

export default {
  name: 'UserMenu',
  data() {
    return {
      profile: RouteNames.PROFILE,
    };
  },
  computed: {
    ...mapState({
      currentUser: state => state.DataStore[DataResources.CURRENT_USER.name],
    }),
  },
  methods: {
    disconnect() {
      if (this.currentUser && this.currentUser[DataResources.CURRENT_USER.id]) {
        this.disconnectUser();
      }
    },
    ...mapActions({
      disconnectUser: DataActionTypes.DISCONNECT,
    }),
  },
};
</script>

<style scoped>

</style>
