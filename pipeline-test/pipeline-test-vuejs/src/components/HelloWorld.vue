<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: null,
    };
  },
  created() {
    this.$websocket.addDatum('user', {
      id_user: 1,
    });
    this.$websocket.addDatum('user', {
      id_user: 2,
    });
    this.msg = this.$websocket.getData();
    this.$websocket.getDatum('user', 'id_user', 1);
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
