<template>
  <div class="matchs">
    <v-container>
      <v-layout row wrap>
        <h3>Matchs</h3>
        <match-item
          v-for="(match, index) in matchs"
          :key="index"
          :match="match">
        </match-item>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataResources from '@/store/data/resources';
import DataResourcesMap from '@/store/data/resources-map';
import MatchItem from '../tournaments/tournament/components/MatchItem';

export default {
  name: 'Matchs',
  components: { MatchItem },
  data() {
    return {};
  },
  created() {
    this.retrieveMatchs();
  },
  computed: {
    ...mapState({
      matchs: state => state.DataStore[DataResources.MATCHS.name],
    }),
    binding() {
      const binding = {};

      if (this.$vuetify.breakpoint.mdAndUp) binding.column = true;

      return binding;
    },
  },
  methods: {
    ...mapActions({
      retrieveData: DataActionsTypes.RETRIEVE_DATA,
    }),
    retrieveMatchs() {
      this.retrieveData({
        resource: DataResourcesMap.MATCHS.ws,
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  .container.grid-list-xl {
    padding: 25px 0px;
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

  .flex {
    padding: 0;
  }

  .card {
    box-shadow: none;
  }

  .row-match {
    border: 1px solid #ccc;
  }
</style>
