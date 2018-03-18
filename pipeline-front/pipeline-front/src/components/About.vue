<template>
  <div class="about">
    <v-breadcrumbs>
      <v-icon slot="divider">chevron_right</v-icon>
      <v-breadcrumbs-item
        v-for="item in items"
        :key="item.text"
        :disabled="item.disabled"
      >
        {{ item.text }}
      </v-breadcrumbs-item>
    </v-breadcrumbs>
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
export default {
  name: 'About',
  data() {
    return {
      msg: 'About',
      items: [{
        href: 'Home',
        router: true,
        text: 'Home',
        icon: 'home',
        disabled: false,
      }, {
        href: 'Settings',
        router: true,
        text: 'Settings',
        icon: 'settings',
        disabled: false,
      }, {
        href: 'About',
        router: true,
        text: 'About',
        icon: 'domain',
        disabled: true,
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
