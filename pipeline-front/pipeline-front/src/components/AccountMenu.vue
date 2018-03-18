<template>
  <div id="account">
    <v-menu v-if="isLogin = true" offset-y>
      <v-btn icon slot="activator" dark>
        <v-icon>more_vert</v-icon>
      </v-btn>
      <v-list>
        <v-list-tile v-for="(item, i) in items" :key="i">
          <v-list-tile-title>{{ item.title }}</v-list-tile-title>
        </v-list-tile>
      </v-list>
    </v-menu>
  </div>
</template>

<script>
export default {
  name: 'atpAccountMenu',
  props: ['isLogin'],
  data() {
    return {
      items: [
        { title: 'Account' },
        { title: 'Parameter' },
        { title: 'Preference' },
        { title: 'Log out' },
      ],
    };
  },
};
</script>
