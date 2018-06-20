<template>
  <div>
    <v-list-tile @click="$global.openRoute(importer)">
      <v-list-tile-title>Importer</v-list-tile-title>
    </v-list-tile>
  </div>
</template>

<script>
  import RouteNames from '@/router/names';

  export default {
    name: 'AdminMenu',
    data() {
      return {
        importer: RouteNames.IMPORTER,
      };
    },
    computed: {},
  };
</script>

<style scoped>

</style>
