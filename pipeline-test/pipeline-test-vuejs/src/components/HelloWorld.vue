<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    {{ user }}
  </div>
</template>

<script>
/* eslint-disable no-console */

export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: null,
      user: null,
    };
  },
  created() {
    this.msg = this.$websocket.getRepo();
    this.$websocket.getFromRepo('user', 'id_user', 1);
    this.$websocket.get('user');
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
