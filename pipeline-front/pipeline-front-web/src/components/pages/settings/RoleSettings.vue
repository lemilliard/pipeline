<template>
  <v-flex xs12>
    <v-data-table
      :headers="headers"
      :items="roles"
      hide-actions>
      <template slot="items" slot-scope="props">
        <td>{{props.item[idField]}}</td>
        <td>{{props.item.users.length}}</td>
        <td>
          <v-btn icon @click="deleteRole(props.item)">
            <v-icon color="blue">{{$global.icons.CLOSE}}</v-icon>
          </v-btn>
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
      idField: DataResources.ROLES.id,
      headers: [
        { text: 'Valeur', value: this.idField },
        { text: 'Utilisateurs', value: 'users' },
        { text: 'Supprimer', value: '' },
      ],
    };
  },
  created() {
    this.retrieveRoles();
  },
  computed: {
    ...mapState({
      roles: state => state.DataStore[DataResources.ROLES.name],
    }),
  },
  methods: {
    retrieveRoles() {
      this.retrieveData({ resource: DataResourcesMap.ROLES.ws });
    },
    createRole() {
      this.createData({ resource: DataResourcesMap.ROLES.ws, body: this.newRole });
    },
    deleteRole(role) {
      this.deleteData({
        resource: DataResourcesMap.ROLE.ws,
        params: { [this.idField]: role[this.idField] },
      });
    },
    ...mapActions({
      retrieveData: DataActionsTypes.RETRIEVE_DATA,
      createData: DataActionsTypes.CREATE_DATA,
      deleteData: DataActionsTypes.DELETE_DATA,
    }),
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
