<template>
  <div class="match">
    {{ score }}
    <v-container>
      <v-layout row wrap>
        <v-flex xs12 sm3 md3>
          <h3>Match</h3>
        </v-flex>
        <v-flex xs3 sm3 md3 offset-md6 follow-match>
          <v-btn color="primary" dark>Suivre le match</v-btn>
        </v-flex>

        <v-container grid-list-md text-xs-center>
          <v-layout row wrap>
            <v-flex justify-space-between fill-height xs12 sm2 md2 v-if="match && match.equipeUne">
              <v-btn color="success" @click="addPoint(match.equipeUne)">+</v-btn>
              <v-btn color="warning">Avertissement</v-btn>
              <v-btn color="info">Soigneur</v-btn>
            </v-flex>
            <v-flex xs6 sm2 md2 v-if="match && match.equipeDeux">
              <v-btn class="hide-on-desktop" color="success" @click="addPoint(match.equipeUne)">+</v-btn>
              <v-card>
                <v-card-text>
                  <span v-for="(joueur, index) in getJoueurs(match.equipeUne)" :key="index">
                    {{ joueur.nom }}
                    <span v-if="index === 0">|</span>
                  </span>
                </v-card-text>
                <v-card-media class="img-equipe" :src="match.img_equipe1" height="400px">
                </v-card-media>
              </v-card>
            </v-flex>

            <score-direct v-if="match" :match="match" :score="score"></score-direct>

            <v-flex xs6 sm2 md2 v-if="match && match.equipeDeux">
              <v-btn color="success" class="hide-on-desktop" @click="addPoint(match.equipeDeux)">+</v-btn>
              <v-card>
                <v-card-text>
                  <span v-for="(joueur, index) in getJoueurs(match.equipeDeux)" :key="index">
                    {{ joueur.nom }}
                    <span v-if="index === 0">|</span>
                  </span>
                </v-card-text>
                <v-card-media class="img-equipe" :src="match.img_equipe2" height="400px">
                </v-card-media>
              </v-card>
            </v-flex>
            <v-flex xs12 sm2 md2 v-if="match && match.equipeDeux">
              <v-btn color="success" @click="addPoint(match.equipeDeux)">+</v-btn>
              <v-btn color="warning">Avertissement</v-btn>
              <v-btn color="info">Soigneur</v-btn>
            </v-flex>
          </v-layout>
        </v-container>

        <score-details
          v-if="match"
          :score="score"
          :joueurs-equipe-une="getJoueurs(match.equipeUne)"
          :joueurs-equipe-deux="getJoueurs(match.equipeDeux)"></score-details>

        <stats></stats>

      </v-layout>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataResources from '@/store/data/resources';
import DataResourcesMap from '@/store/data/resources-map';

import ScoreDirect from './components/ScoreDirect';
import ScoreDetails from './components/ScoreDetails';
import Stats from './components/Stats';

export default {
  name: 'Match',
  components: { ScoreDirect, ScoreDetails, Stats },
  data() {
    return {
      idMatchField: DataResources.MATCHS.id,
    };
  },
  created() {
    this.retrieveMatch();
  },
  computed: {
    ...mapState({
      matchs: state => state.DataStore[DataResources.MATCHS.name],
      scores: state => state.DataStore[DataResources.SCORES.name],
    }),
    match() {
      const id = parseInt(this.$route.params.id, 10);
      return this.matchs.find((t) => {
        if (t[DataResources.MATCHS.id] === id) {
          return t;
        }
        return null;
      });
    },
    score() {
      const id = parseInt(this.$route.params.id, 10);
      return this.scores.find((t) => {
        if (t[DataResources.SCORES.id] === id) {
          return t;
        }
        return null;
      });
    },
  },
  methods: {
    ...mapActions({
      retrieveData: DataActionsTypes.RETRIEVE_DATA,
      createData: DataActionsTypes.CREATE_DATA,
    }),
    retrieveMatch() {
      const id = parseInt(this.$route.params.id, 10);
      this.retrieveData({
        resource: DataResourcesMap.MATCH.ws,
        params: { [DataResources.MATCHS.id]: id },
      });
      this.retrieveData({
        resource: DataResourcesMap.SCORE.ws,
        params: { [DataResources.SCORES.id]: id },
      });
    },
    getJoueurs(equipe) {
      const joueurs = [];
      equipe.equiJous.forEach((equiJou) => {
        joueurs.push(equiJou.joueur);
      });
      return joueurs;
    },
    addPoint(equipe) {
      if (this.match) {
        const ajoutPoint = {
          [this.idMatchField]: this.match[this.idMatchField],
          idEquipe: equipe.idEquipe,
        };
        this.createData({ resource: DataResourcesMap.SCORE_ADD.ws, body: ajoutPoint });
      }
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
  h1, h2 {
    font-weight: normal;
    font-size: 20px;
    line-height: 20px;
    text-transform: uppercase;
  }

  .follow-match {
    text-align: right;
  }

  .match {
    width: 100%;
  }

  .heading {
    height: 50px;
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

  .hide-on-desktop {
    display: none;
  }
</style>
