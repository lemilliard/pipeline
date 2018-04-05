<template>
  <v-app dark>
    <template-toolbar></template-toolbar>
    <v-content>
      <router-view clipped-left></router-view>
    </v-content>
    <template-footer></template-footer>
  </v-app>
</template>

<script>
import { mapActions } from 'vuex';
import DataActionTypes from '@/store/data/actions/types';
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
  methods: {
    autoConnect() {
      if (this.$cookie.get('id_user')) {
        this.connectById(this.$cookie.get('id_user'));
      }
    },
    ...mapActions({
      connectById: DataActionTypes.CONNECT_BY_ID,
    }),
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
