<template>
  <v-flex xs6 sm4 md4>
    <h4>Score en direct</h4>
    <h4>
      <span class="label label-primary">{{ time.heures }}</span> heures
      <span class="label label-primary">{{ time.minutes }}</span> minutes
      <span class="label label-primary">{{ time.secondes }}</span> secondes
    </h4>
    <br>
    <p>
      <span v-if="isFinished()">
        <v-alert danger :value="true">
          Match terminé
        </v-alert>
      </span>
      <span v-if="!isFinished() && isArbitre">
        <button class="btn btn-primary btn-lg" v-show="!isStarted() || isPaused()" v-on:click="play(false)">
          Démarrer
        </button>
        <button class="btn btn-danger btn-lg" v-show="isStarted() && !isPaused()" v-on:click="stop">
          Pause
        </button>
        <button class="btn btn-danger btn-lg" v-show="isStarted() && isPaused()" v-on:click="end">
          Terminer
        </button>
      </span>
    </p>
    <v-layout text-xs-center text-md-center>
      <v-flex xs6 sm6 md6 align-center="true">
        <v-card color="blue-grey darken-2" class="white--text">
          <v-card-title primary-title>
            <div class="headline" v-if="isFinished()">{{ setsGagnantsEquipeUne }}</div>
            <div class="headline" v-else>{{ jeuxEquipeUne }} [{{ pointsEquipeUne }}]</div>
          </v-card-title>
        </v-card>
      </v-flex>
      <v-flex xs6 sm6 md6>
        <v-card color="blue-grey darken-2" class="white--text">
          <v-card-title primary-title>
            <div class="headline" v-if="isFinished()">{{ setsGagnantsEquipeDeux }}</div>
            <div class="headline" v-else>{{ jeuxEquipeDeux }} [{{ pointsEquipeDeux }}]</div>
          </v-card-title>
        </v-card>
      </v-flex>
    </v-layout>
  </v-flex>
</template>

<script>
import { mapActions, mapGetters } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataGetterTypes from '@/store/data/getters/types';
import DataResources from '@/store/data/resources';
import DataResourcesMap from '@/store/data/resources-map';

export default {
  name: 'ScoreDirect',
  props: ['match', 'score'],
  data() {
    return {
      idMatchField: DataResources.MATCHS.id,
      time: {
        heures: 0,
        minutes: 0,
        secondes: 0,
      },
      btnPlay: 'Démarrer',
      totalSecondes: 0,
      timer: null,
    };
  },
  computed: {
    ...mapGetters({
      isArbitre: DataGetterTypes.IS_ARBITRE,
    }),
    pointsEquipeUne() {
      let points = 0;
      if (this.score
        && this.score.pointActuelEquipeUne
        && this.score.pointActuelEquipeUne.value) {
        points = this.score.pointActuelEquipeUne.value;
      }
      return points;
    },
    pointsEquipeDeux() {
      let points = 0;
      if (this.score
        && this.score.pointActuelEquipeDeux
        && this.score.pointActuelEquipeDeux.value) {
        points = this.score.pointActuelEquipeDeux.value;
      }
      return points;
    },
    jeuxEquipeUne() {
      let jeux = 0;
      if (this.score
        && this.score.dernierSet
        && this.score.dernierSet.jeuxEquipeUne) {
        jeux = this.score.dernierSet.jeuxEquipeUne;
      }
      return jeux;
    },
    jeuxEquipeDeux() {
      let jeux = 0;
      if (this.score
        && this.score.dernierSet
        && this.score.dernierSet.jeuxEquipeDeux) {
        jeux = this.score.dernierSet.jeuxEquipeDeux;
      }
      return jeux;
    },
    setsGagnantsEquipeUne() {
      if (this.score) {
        return this.score.setsGagnantsEquipeUne;
      }
      return 0;
    },
    setsGagnantsEquipeDeux() {
      if (this.score) {
        return this.score.setsGagnantsEquipeDeux;
      }
      return 0;
    },
  },
  watch: {
    match() {
      this.autoPlay();
    },
  },
  created() {
    this.autoPlay();
  },
  methods: {
    ...mapActions({
      createData: DataActionsTypes.CREATE_DATA,
    }),
    resetTime() {
      if (this.match) {
        if (this.match.dureeJeu) {
          const dureeJeu = Math.floor(this.match.dureeJeu / 1000);
          if (!this.isPaused()) {
            const dateActuelle = Math.floor(Date.now() / 1000);
            const dateDerniereReprise = Math.floor(this.match.dateDerniereReprise / 1000);
            this.totalSecondes = (dateActuelle - dateDerniereReprise) + dureeJeu;
          } else {
            this.totalSecondes = dureeJeu;
          }
        } else {
          this.totalSecondes = 0;
        }
        this.updateTime();
      }
    },
    autoPlay() {
      if (this.match) {
        this.resetTime();
        this.play(true);
      }
    },
    isStarted() {
      return this.match && this.match.started;
    },
    isFinished() {
      return this.match && this.match.finished === true;
    },
    isPaused() {
      return this.match && this.match.paused === true;
    },
    play(autoplay) {
      if (!autoplay) {
        this.createData({
          resource: DataResourcesMap.MATCH_PLAY.ws,
          params: { [this.idMatchField]: this.match[this.idMatchField] },
        });
      }
      this.chronoStop();
      this.chronoStart();
    },
    stop() {
      this.createData({
        resource: DataResourcesMap.MATCH_PAUSE.ws,
        params: { [this.idMatchField]: this.match[this.idMatchField] },
      });
      this.chronoStop();
    },
    end() {
      this.createData({
        resource: DataResourcesMap.MATCH_END.ws,
        params: { [this.idMatchField]: this.match[this.idMatchField] },
      });
    },
    chronoStart() {
      if (this.isStarted() && !this.isPaused() && !this.isFinished()) {
        this.timer = setInterval(() => {
          this.totalSecondes += 1;
          this.updateTime();
        }, 1000);
      }
    },
    chronoStop() {
      clearInterval(this.timer);
    },
    updateTime() {
      this.time.secondes = (this.totalSecondes) % 60;
      this.time.minutes = Math.floor((this.totalSecondes / 60) % 60);
      this.time.heures = Math.floor(((this.totalSecondes / 3600)) % 24);
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
