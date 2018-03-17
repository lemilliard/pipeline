<template>
  <div id="nav">
    <v-navigation-drawer
      clipped
      fixed
      v-model="drawer"
      app
    >
      <v-list>
        <template v-for="(item, index) in items">
          <v-list-tile :href="item.href" :to="{name: item.href}" exact>
            <v-list-tile-action>
              <v-icon light v-html="item.icon"></v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title v-html="item.title"></v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </template>
      </v-list>
      <v-divider></v-divider>
      <v-list>
        <template v-for="(item, index) in itemsMenuArbitre">
          <v-list-tile :href="item.href" :to="{name: item.href}" exact>
            <v-list-tile-action>
              <v-icon light v-html="item.icon"></v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-list-tile-title v-html="item.title"></v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </template>
      </v-list>
    </v-navigation-drawer>
  </div>
</template>

<script>
  export default {
    name: 'atpPrimaryMenu',
    props: ['drawer'],
    data() {
      return {
        drawer: null,
        items: [{
          href: 'Home',
          router: true,
          title: 'Home',
          icon: 'home',
        }, {
          href: 'Settings',
          router: true,
          title: 'Settings',
          icon: 'settings',
        }, {
          href: 'About',
          router: true,
          title: 'About',
          icon: 'domain',
        }],
        itemsMenuArbitre: [{
          href: 'Matchs',
          router: true,
          title: 'Matchs en direct',
          icon: 'gamepad',
        }, {
          href: 'Classement',
          router: true,
          title: 'Classement',
          icon: 'settings',
        }, {
          href: 'Calendrier',
          router: true,
          title: 'Calendrier',
          icon: 'settings',
        }],
      };
    },
  };


</script>

