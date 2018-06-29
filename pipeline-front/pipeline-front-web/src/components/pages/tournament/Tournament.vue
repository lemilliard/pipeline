<template>
  <div class="tournament">
    {{ tournament }}
    <v-container fluid>
      <phase-dialog></phase-dialog>
      <phase :depth="depth" :parent="this"></phase>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataResources from '@/store/data/resources';
import DataResourcesMap from '@/store/data/resources-map';
import PhaseDialog from './components/PhaseDialog';
import Phase from './components/Phase';


export default {
  name: 'Tournament',
  components: { Phase, PhaseDialog },
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
