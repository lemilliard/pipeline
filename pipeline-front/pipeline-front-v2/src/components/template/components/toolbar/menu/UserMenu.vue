<template>
  <div>
    <v-list-tile @click="">
      <v-list-tile-title>Profile</v-list-tile-title>
    </v-list-tile>
    <v-list-tile @click="signout">
      <v-list-tile-title>Signout</v-list-tile-title>
    </v-list-tile>
  </div>
</template>

<script>
  import { mapState, mapActions } from 'vuex';
  import UserActionTypes from '@/store/data/actions/types';

  export default {
    name: 'UserMenu',
    data() {
      return {};
    },
    computed: {
      ...mapState({
        user: state => state.DataStore.user,
      }),
    },
    methods: {
      signout() {
        if (this.user && this.user.id_user) {
          this.$cookie.delete('id_user');
          this.signoutUser();
        }
      },
      ...mapActions({
        signoutUser: UserActionTypes.SIGNOUT,
      }),
    },
  };
</script>

<style scoped>

</style>
