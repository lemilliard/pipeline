<template>
  <div id="header">
    <v-toolbar app fixed clipped-left dark color="green">
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-toolbar-title>ATP Live</v-toolbar-title>
      <v-spacer></v-spacer>
      <!--<v-btn icon>
        <v-icon>search</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>apps</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>refresh</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>more_vert</v-icon>
      </v-btn>-->
      <atpAccountMenu :isLogin="isLogin"></atpAccountMenu>
      <atpLogin></atpLogin>
      <atpRegister></atpRegister>
    </v-toolbar>
    <!--<atpToolBar :drawer="drawer"></atpToolBar>-->
    <atpPrimaryMenu :drawer="drawer"></atpPrimaryMenu>
  </div>
</template>

<script>
import atpToolBar from './ToolBar';
import atpPrimaryMenu from './PrimaryMenu';
import atpLogin from './Login';
import atpRegister from './Register';
import atpAccountMenu from './AccountMenu';

export default {
  components: { atpToolBar, atpPrimaryMenu, atpLogin, atpRegister, atpAccountMenu },
  name: 'atpHeader',
  data() {
    return {
      drawer: false,
      isLogin: false,
    };
  },
};
</script>

