<template>
  <div>
    <progress-bar v-if="progressBar"></progress-bar>
    <v-toolbar
      app
      clipped-left
      fixed
      height="50"
      color="success"
    >
      <v-toolbar-title style="width: 300px" class="ml-0 pl-3">
        <!--<v-toolbar-side-icon @click.stop="switchDrawer"></v-toolbar-side-icon>-->
        <v-btn flat color="white" to="/">Pipeline</v-btn>
      </v-toolbar-title>
      <toolbar-navigation></toolbar-navigation>

      <v-spacer></v-spacer>
      <main-menu v-if="isConnected()"></main-menu>
      <account-dialog v-else></account-dialog>
    </v-toolbar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from 'vuex';
import RouteNames from '@/router/names';
import UserGetterTypes from '@/store/data/getters/types';
import ActionTypes from '@/store/template/actions/types';
import ProgressBar from './ProgressBar';
import MainMenu from './menu/MainMenu';
import AccountDialog from './menu/accountdialog/AccountDialog';
import ToolbarNavigation from './navigation/ToolbarNavigation';

export default {
  name: 'Toolbar',
  components: { MainMenu, AccountDialog, ProgressBar, ToolbarNavigation },
  data() {
    return {
      RouteNames,
    };
  },
  computed: {
    ...mapState({
      progressBar: state => state.TemplateStore.progressBar,
    }),
  },
  methods: {
    ...mapGetters({
      isConnected: UserGetterTypes.IS_CONNECTED,
    }),
    ...mapActions({
      switchDrawer: ActionTypes.SWITCH_DRAWER,
    }),
  },
};
</script>

<style scoped>
  .application .theme--dark.toolbar {
    background: #2ECC71;
  }

  .application .theme--light.toolbar {
    color: #fff;
  }
</style>
