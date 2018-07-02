<template>
  <v-container grid-list-md text-xs-center>
    <v-layout row wrap>
      <v-flex row-tournament xs12 sm12 md12>
        <v-card>
          <v-layout row wrap>
            <v-flex xs12 sm2 md2>
              <v-card>
                <v-card-text v-html="getDate"></v-card-text>
              </v-card>
            </v-flex>
            <v-flex xs12 sm6 md6>
              <v-card>
                <v-card-text>
                  {{ tournament.nom }} - {{ getVille }}
                </v-card-text>
              </v-card>
            </v-flex>
            <v-flex xs12 sm4 md4>
              <v-btn
                color="info"
                :to="{name: tournamentRoute, params: {id: tournament[idField]}}">
                Voir les match
              </v-btn>
            </v-flex>
          </v-layout>
        </v-card>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import RouteNames from '@/router/names';
import DataResources from '@/store/data/resources';

export default {
  name: 'TournamentItem',
  props: ['tournament'],
  data() {
    return {
      tournamentRoute: RouteNames.TOURNAMENT,
      idField: DataResources.TOURNAMENTS.id,
    };
  },
  computed: {
    getDate() {
      const date = new Date(this.tournament.date);
      const day = `0${date.getDay()}`.substr(-2);
      const month = `0${date.getMonth()}`.substr(-2);
      const year = date.getFullYear();
      const hours = `0${date.getHours()}`.substr(-2);
      const minutes = `0${date.getMinutes()}`.substr(-2);
      return `${day}/${month}/${year} - ${hours}h${minutes}`;
    },
    getVille() {
      return this.$global.getTournamentVille(this.tournament);
    },
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
