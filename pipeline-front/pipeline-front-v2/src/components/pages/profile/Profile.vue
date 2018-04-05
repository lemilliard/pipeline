<template>
  <div class="about">
    <h1>Profile</h1>
    {{ currentUser }}
  </div>
</template>

<script>
import { mapState } from 'vuex';
import DataResources from '@/store/data/resources';

export default {
  name: 'Profile',
  data() {
    return {
      msg: 'Profile',
    };
  },
  computed: {
    ...mapState({
      currentUser: state => state.DataStore[DataResources.CURRENT_USER.name],
    }),
  },
};
</script>

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
