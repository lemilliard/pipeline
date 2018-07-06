<template>
  <v-flex xs12>
    <v-data-table
      :headers="headers"
      :items="users"
      hide-actions>
      <template slot="items" slot-scope="props">
        <td>{{props.item[idField]}}</td>
        <td>{{props.item.email}}</td>
        <td>{{props.item.nom}}</td>
        <td>{{props.item.prenom}}</td>
        <td>
          <v-layout row>

            <v-select
              :items="roles"
              v-model="props.item.roleName"
              single-line
              item-text="value"
              item-value="value"
            ></v-select>
            <v-btn icon @click="updateUser(props.item)">
              <v-icon color="blue">{{$global.icons.SAVE}}</v-icon>
            </v-btn>
          </v-layout>
        </td>
      </template>
    </v-data-table>
  </v-flex>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataResources from '@/store/data/resources';
import DataResourcesMap from '@/store/data/resources-map';

export default {
  name: 'UserSettings',
  data() {
    return {
      idField: DataResources.USERS.id,
      headers: [
        { text: 'Id', value: this.idField },
        { text: 'Email', value: 'email' },
        { text: 'Nom', value: 'nom' },
        { text: 'Prénom', value: 'prenom' },
        { text: 'Role', value: 'roleName' },
      ],
    };
  },
  created() {
    this.retrieveUsers();
    this.retrieveRoles();
  },
  computed: {
    ...mapState({
      users: state => state.DataStore[DataResources.USERS.name],
      roles: state => state.DataStore[DataResources.ROLES.name],
    }),
  },
  methods: {
    updateUser(user) {
      this.updateData({ resource: DataResourcesMap.USERS.ws, body: user });
    },
    retrieveUsers() {
      this.retrieveData({ resource: DataResourcesMap.USERS.ws });
    },
    retrieveRoles() {
      this.retrieveData({ resource: DataResourcesMap.ROLES.ws });
    },
    ...mapActions({
      retrieveData: DataActionsTypes.RETRIEVE_DATA,
      updateData: DataActionsTypes.UPDATE_DATA,
    }),
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
