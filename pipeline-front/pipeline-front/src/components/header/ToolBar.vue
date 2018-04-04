<template>
  <div id="toolbar">
    <v-toolbar app fixed clipped-left dark color="green">
      <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      <v-toolbar-title>ATP Live</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon>
        <v-icon>search</v-icon>
      </v-btn>
      <!--<v-btn icon>
        <v-icon>apps</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>refresh</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>more_vert</v-icon>
      </v-btn>-->
      <atpLogin></atpLogin>
    </v-toolbar>
    <atpLogin></atpLogin>
  </div>
</template>

<script>
import atpLogin from './Login';

export default {
  components: { atpLogin },
  name: 'atpToolBar',
  props: ['drawer'],
  data() {
    return {};
  },
};


</script>

