<template>
  <v-flex xs12>
    <v-alert
      v-if="!isAdmin"
      :value="true">
      Vous n'avez pas à être là!
    </v-alert>
    <v-layout row fluid v-else>
      <v-flex xs12>
        <v-tabs fixed-tabs>
          <v-tab>Utilisateurs</v-tab>
          <v-tab>Roles</v-tab>
          <v-tab-item>
            <user-settings></user-settings>
          </v-tab-item>
          <v-tab-item>
            <role-settings></role-settings>
          </v-tab-item>
        </v-tabs>
      </v-flex>
    </v-layout>
  </v-flex>
</template>

<script>
import { mapGetters } from 'vuex';
import DataGetterTypes from '@/store/data/getters/types';
import UserSettings from './UserSettings';
import RoleSettings from './RoleSettings';

export default {
  name: 'Settings',
  components: { UserSettings, RoleSettings },
  data() {
    return {};
  },
  computed: {
    ...mapGetters({
      isAdmin: DataGetterTypes.IS_ADMIN,
    }),
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
