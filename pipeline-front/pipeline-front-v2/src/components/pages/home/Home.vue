<template>
  <div class="home">
    {{ user }}
    {{ users }}
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataResources from '@/store/data/resources';

export default {
  name: 'Home',
  data() {
    return {};
  },
  computed: {
    ...mapState({
      user: state => state.DataStore[DataResources.USER],
      users: state => state.DataStore[DataResources.USERS],
    }),
  },
  created() {
    this.retrieveData({ resource: DataResources.USERS });
    this.retrieveData({ resource: DataResources.USER, params: { id_user: 1 } });
  },
  methods: {
    ...mapActions({
      retrieveData: DataActionsTypes.RETRIEVE_DATA,
    }),
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
