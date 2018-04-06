<template>
  <div class="about">
    <h1>Profile</h1>
    {{ currentUser }}
    <br/>
    {{ roles }}
    <v-select
      :items="roles"
      v-model="currentUser.roleName"
      single-line
      item-text="value"
      item-value="value"
    ></v-select>
    <v-btn
      @click.stop="retrieveRoles">
      <v-icon>refresh</v-icon>
    </v-btn>
    <v-btn
      color="primary"
      @click.stop="updateUser">
      UPDATE USER
    </v-btn>
    <v-text-field v-model="newRole.value"></v-text-field>
    <v-btn
      color="primary"
      @click.stop="createRole">
      CREATE ROLE
    </v-btn>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataResources from '@/store/data/resources';
import DataResourcesMap from '@/store/data/resources-map';

export default {
  name: 'Profile',
  data() {
    return {
      msg: 'Profile',
      role: null,
      newRole: {
        value: null,
      },
    };
  },
  created() {
    this.retrieveRoles();
  },
  computed: {
    ...mapState({
      currentUser: state => state.DataStore[DataResources.CURRENT_USER.name],
      roles: state => state.DataStore[DataResources.ROLES.name],
    }),
  },
  methods: {
    updateUser() {
      this.updateData({ resource: DataResourcesMap.UPDATE_USER.ws, body: this.currentUser });
    },
    createRole() {
      this.createData({ resource: DataResourcesMap.ROLES.ws, body: this.newRole });
    },
    retrieveRoles() {
      this.retrieveData({ resource: DataResourcesMap.ROLES.ws });
    },
    ...mapActions({
      retrieveData: DataActionsTypes.RETRIEVE_DATA,
      updateData: DataActionsTypes.UPDATE_DATA,
      createData: DataActionsTypes.CREATE_DATA,
    }),
  },
};
</script>

<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
