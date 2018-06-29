<template>
  <div>
    <v-list-tile
      v-for="(item, index) in items"
      :key="index"
      @click="$global.openRoute(item.to)">
      <v-list-tile-title>{{ item.text }}</v-list-tile-title>
    </v-list-tile>
  </div>
</template>

<script>
  import RouteNames from '@/router/names';

  export default {
    name: 'AdminMenu',
    data() {
      return {
        items: [
          { text: 'Importer', to: RouteNames.IMPORTER },
          { text: 'Paramètres', to: RouteNames.SETTINGS },
        ],
      };
    },
    computed: {},
  };
</script>

<style scoped>

</style>
