<template>
  <div class="tournament">
    {{ tournament }}
    <v-container>
      <v-layout row wrap>
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
import { mapState, mapActions } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataResources from '@/store/data/resources';
import DataResourcesMap from '@/store/data/resources-map';
import MatchItem from './components/MatchItem';

export default {
  name: 'Tournament',
  components: { MatchItem },
  data() {
    return {
      nextIdMatch: 0,
      idMatch: -1,
      depth: 3,
      dialog: false,
    };
  },
  created() {
    this.retrieveTournament();
  },
  computed: {
    ...mapState({
      tournaments: state => state.DataStore[DataResources.TOURNAMENTS.name],
    }),
    tournament() {
      const id = parseInt(this.$route.params.id, 10);
      return this.tournaments.find((t) => {
        if (t[DataResources.TOURNAMENTS.id] === id) {
          return t;
        }
        return null;
      });
    },
    matchs() {
      const matchs = [];
      if (this.tournament && this.tournament.complexe && this.tournament.complexe.courts) {
        this.tournament.complexe.courts.forEach((court) => {
          court.rencontres.forEach(match => matchs.push(match));
        });
      }
      return matchs;
    },
  },
  methods: {
    retrieveTournament() {
      const id = parseInt(this.$route.params.id, 10);
      this.retrieveData({
        resource: DataResourcesMap.TOURNAMENT.ws,
        params: { [DataResources.TOURNAMENTS.id]: id },
      });
    },
    getVille() {
      return this.$global.getTournamentVille(this.tournament);
    },
    ...mapActions({
      retrieveData: DataActionsTypes.RETRIEVE_DATA,
    }),
  },
};
</script>

<style scoped>
</style>
