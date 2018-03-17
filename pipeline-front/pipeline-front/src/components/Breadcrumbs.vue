<template>
  <div class="breadcrumbs">
    <v-breadcrumbs>
      <v-icon slot="divider">chevron_right</v-icon>
      <v-breadcrumbs-item
        v-for="item in items"
        :key="item.title"
        :disabled="item.disabled"
      >
        {{ item.text }}
      </v-breadcrumbs-item>
    </v-breadcrumbs>
  </div>
</template>

<script>
  export default {
    name: 'Breadcrumbs',
    data() {
      return {
        msg: 'Breadcrumbs',
        items: [{
          href: 'Home',
          router: true,
          title: 'Home',
          disabled: false
        }, {
          href: 'Settings',
          router: true,
          title: 'Settings',
          disabled: false
        }, {
          href: 'About',
          router: true,
          title: 'About',
          disabled: false
        }],
        itemsMenuArbitre: [{
          href: 'Matchs',
          router: true,
          title: 'Matchs',
          disabled: false
        }, {
          href: 'Classement',
          router: true,
          title: 'Classement',
          disabled: false
        }, {
          href: 'Calendrier',
          router: true,
          title: 'Calendrier',
          disabled: false
        }],
      };
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }
</style>
