<template>
  <v-layout>
    <toolbar-navigation-item v-for="(item, index) in items" :key="index" :item="item"></toolbar-navigation-item>
  </v-layout>
</template>

<script>
  import RouteNames from '@/router/names';
  import ToolbarNavigationItem from './ToolbarNavigationItem';

  export default {
    name: 'ToolbarNavigation',
    components: { ToolbarNavigationItem },
    data() {
      return {
        items: [
          { icon: this.$global.icons.ABOUT, text: 'About', route: RouteNames.ABOUT },
          { icon: this.$global.icons.TOURNAMENTS, text: 'Tournaments', route: RouteNames.TOURNAMENTS },
          { icon: this.$global.icons.MATCHS, text: 'Matchs', route: RouteNames.MATCHS },
          { icon: this.$global.icons.LADDER, text: 'Ladder', route: RouteNames.LADDER },
        ],
      };
    },
    computed: {},
    methods: {},
  };
</script>

<style scoped>
  a {
    text-decoration: none;
    color: #fff;
  }

  .btn {
    color: #fff;
  }

  .application--light .toolbar .btn:not(.btn--disabled) {
    color: #fff;
  }

  a:hover {
    text-shadow: 0 0 10px #fff;

  }
</style>
