<template>
  <v-layout row wrap>
    <v-flex xs12>
      0
      <v-layout row wrap>
        <v-flex xs6>
          1
        </v-flex>
        <v-flex xs6>
          2
        </v-flex>
      </v-layout>
    </v-flex>
  </v-layout>
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
