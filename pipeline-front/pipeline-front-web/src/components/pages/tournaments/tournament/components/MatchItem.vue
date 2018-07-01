<template>
  <v-container grid-list-md text-xs-center>
    <v-layout row wrap>
      <v-flex row-match xs12 sm12 md12>
        <v-card>
          <v-layout row wrap>
            <v-flex xs12 sm2 md2>
              <v-card>
                <v-card-text>{{ getDate() }}</v-card-text>
              </v-card>
            </v-flex>
            <v-flex xs12 sm3 md3>
              <v-card>
                <v-card-text>
                  <span v-for="(joueur, index) in getJoueurs(match.equipeUne)" :key="index">
                    {{ joueur.nom }}
                    <span v-if="index === 0">|</span>
                  </span>
                </v-card-text>
              </v-card>
            </v-flex>
            <v-flex xs12 sm3 md3>
              <v-card>
                <v-card-text>
                  <span v-for="(joueur, index) in getJoueurs(match.equipeDeux)" :key="index">
                    {{ joueur.nom }}
                    <span v-if="index === 0">|</span>
                  </span>
                </v-card-text>
              </v-card>
            </v-flex>
            <v-flex xs12 sm2 md2>
              <v-btn
                color="info"
                :to="{name: matchRoute, params: {id: match[idField]}}">
                Voir le match
              </v-btn>
            </v-flex>
            <v-flex xs12 sm2 md2>
              <v-btn
                color="warning"
                :to="{name: matchRoute, params: {id: match[idField]}}">
                S'abonner
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
  name: 'MatchItem',
  props: ['match'],
  data() {
    return {
      matchRoute: RouteNames.MATCH,
      idField: DataResources.MATCHS.id,
    };
  },
  methods: {
    getDate() {
      const date = new Date(this.match.dateDebut);
      const day = `0${date.getDay()}`.substr(-2);
      const month = `0${date.getMonth()}`.substr(-2);
      const year = date.getFullYear();
      const hours = `0${date.getHours()}`.substr(-2);
      const minutes = `0${date.getMinutes()}`.substr(-2);
      return `${day}/${month}/${year} - ${hours}h${minutes}`;
    },
    getJoueurs(equipe) {
      const joueurs = [];
      equipe.equiJous.forEach((equiJou) => {
        joueurs.push(equiJou.joueur);
      });
      return joueurs;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
