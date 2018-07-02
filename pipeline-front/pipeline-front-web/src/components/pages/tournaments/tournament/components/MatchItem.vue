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
                @click="deleteAbonnement()"
                v-if="isAbonneRencontre">
                Se désabonner
              </v-btn>
              <v-btn
                color="warning"
                @click="createAbonnement()"
                v-else>
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
import { mapActions, mapState } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataResources from '@/store/data/resources';
import DataResourcesMap from '@/store/data/resources-map';
import RouteNames from '@/router/names';

export default {
  name: 'MatchItem',
  props: ['match'],
  data() {
    return {
      matchRoute: RouteNames.MATCH,
      idField: DataResources.MATCHS.id,
    };
  },
  computed: {
    ...mapState({
      currentUser: state => state.DataStore[DataResources.CURRENT_USER.name],
      abonnements: state => state.DataStore[DataResources.ABONNEMENTS.name],
    }),
    isAbonneRencontre() {
      return this.abonnements &&
        this.abonnements
          .filter(abonnement => abonnement.idRencontre === this.match[this.idField])
          .length > 0;
    },
  },
  methods: {
    ...mapActions({
      retrieveData: DataActionsTypes.RETRIEVE_DATA,
      createData: DataActionsTypes.CREATE_DATA,
      deleteData: DataActionsTypes.DELETE_DATA,
    }),
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
    createAbonnement() {
      if (this.currentUser && this.currentUser[DataResources.CURRENT_USER.id]) {
        this.createData({
          resource: DataResourcesMap.ABONNEMENT.ws,
          body: this.getAbonnement(),
        });
      }
    },
    deleteAbonnement() {
      if (this.currentUser && this.currentUser[DataResources.CURRENT_USER.id]) {
        this.deleteData({
          resource: DataResourcesMap.DELETE_ABONNEMENT.ws,
          params: this.getAbonnement(),
        });
      }
    },
    getAbonnement() {
      const idUser = this.currentUser[DataResources.CURRENT_USER.id];
      return {
        [DataResources.CURRENT_USER.id]: idUser,
        [DataResources.MATCHS.id]: this.match[this.idField],
      };
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
