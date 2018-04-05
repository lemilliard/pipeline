<template>
  <div class="home">
    {{ users }}
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataResourcesMap from '@/store/data/resources-map';
import DataResources from '@/store/data/resources';

export default {
  name: 'Home',
  data() {
    return {};
  },
  computed: {
    ...mapState({
      users: state => state.DataStore[DataResources.USER.name],
    }),
  },
  created() {
    this.retrieveData({ resource: DataResourcesMap.USERS.ws });
    this.retrieveData({ resource: DataResourcesMap.USER.ws, params: { id_user: 1 } });
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
