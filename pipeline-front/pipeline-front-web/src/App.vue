<template>
  <v-app>
    <template-toolbar></template-toolbar>
    <v-content>
      <router-view clipped-left></router-view>
    </v-content>
    <template-footer></template-footer>
  </v-app>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataResources from '@/store/data/resources';
import DataResourcesMap from '@/store/data/resources-map';
import Template from '@/components/template';

require('vuetify/dist/vuetify.min.css');

export default {
  components: {
    TemplateToolbar: Template.Toolbar,
    TemplateFooter: Template.Footer,
  },
  data() {
    return {};
  },
  created() {
    this.autoConnect();
  },
  watch: {
    currentUser(newCurrentUser) {
      if (newCurrentUser && newCurrentUser[DataResources.CURRENT_USER.id]) {
        this.retrieveAbonnements(newCurrentUser[DataResources.CURRENT_USER.id]);
      }
    },
  },
  computed: {
    ...mapState({
      currentUser: state => state.DataStore[DataResources.CURRENT_USER.name],
    }),
  },
  methods: {
    ...mapActions({
      retrieveData: DataActionsTypes.RETRIEVE_DATA,
      connectById: DataActionsTypes.CONNECT_BY_ID,
    }),
    autoConnect() {
      if (this.$cookie.get('idUser')) {
        this.connectById(this.$cookie.get('idUser'));
      }
    },
    retrieveAbonnements(idUser) {
      this.retrieveData({
        resource: DataResourcesMap.ABONNEMENTS.ws,
        params: { [DataResources.CURRENT_USER.id]: idUser },
      });
    },
  },
};
</script>

<style>
  input:-webkit-autofill,
  input:-webkit-autofill:hover,
  input:-webkit-autofill:focus
  input:-webkit-autofill,
  textarea:-webkit-autofill,
  textarea:-webkit-autofill:hover
  textarea:-webkit-autofill:focus,
  select:-webkit-autofill,
  select:-webkit-autofill:hover,
  select:-webkit-autofill:focus {
    border: none !important;
    -webkit-text-fill-color: white !important;
    -webkit-box-shadow: 0 0 0px 1000px #424242 inset;
    transition: background-color 5000s ease-in-out 0s;
  }
</style>
