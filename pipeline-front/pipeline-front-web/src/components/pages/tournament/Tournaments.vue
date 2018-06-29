<template>
  <div class="tournament">
    <v-container>
      <v-layout row wrap>
        <h3>Liste des tournois</h3>
        <tournament-item
          v-for="(tournament, index) in tournaments"
          :key="index"
          :tournament="tournament">
        </tournament-item>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataResources from '@/store/data/resources';
import DataResourcesMap from '@/store/data/resources-map';
import TournamentItem from './components/TournamentItem';


export default {
  name: 'Tournament',
  components: { TournamentItem },
  data() {
    return {};
  },
  created() {
    this.retrieveTournaments();
  },
  computed: {
    ...mapState({
      tournaments: state => state.DataStore[DataResources.TOURNAMENTS.name],
    }),
  },
  methods: {
    retrieveTournaments() {
      this.retrieveData({ resource: DataResourcesMap.TOURNAMENTS.ws });
    },
    ...mapActions({
      retrieveData: DataActionsTypes.RETRIEVE_DATA,
    }),
  },
};
</script>

<style scoped>

  .flex {
    padding: 0;
  }

  .card {
    box-shadow: none;
  }

  .row-tournament {
    border: 1px solid #ccc;
  }

</style>
