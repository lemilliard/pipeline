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
      <button class="btn btn-danger btn-lg" v-show="etat.backward" v-on:click="backward">Effacer</button>
      <button class="btn btn-danger btn-lg" v-show="etat.stop" v-on:click="stop">Pause</button>
      <button class="btn btn-primary btn-lg" v-show="etat.play" v-on:click="play">{{ btnPlay }}</button>
    </p>
    <v-layout text-xs-center text-md-center>
      <v-flex xs6 sm6 md6 align-center="true">
        <v-card color="blue-grey darken-2" class="white--text">
          <v-card-title primary-title>
            <div class="headline">{{ jeuxEquipeUne }} [{{ pointsEquipeUne }}]</div>
          </v-card-title>
        </v-card>
      </v-flex>
      <v-flex xs6 sm6 md6>
        <v-card color="blue-grey darken-2" class="white--text">
          <v-card-title primary-title>
            <div class="headline">{{ jeuxEquipeDeux }} [{{ pointsEquipeDeux }}]</div>
          </v-card-title>
        </v-card>
      </v-flex>
    </v-layout>
  </v-flex>
</template>

<script>
export default {
  name: 'Match',
  props: ['score'],
  data() {
    return {
      time: {
        heures: 0,
        minutes: 0,
        secondes: 0,
      },
      btnPlay: 'Démarrer',
      etat: {
        stop: false,
        backward: false,
        play: true,
      },
      totalSecondes: 0,
      timer: null,
    };
  },
  computed: {
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
  },
  methods: {
    backward() {
      this.chronoReset();
    },
    play() {
      this.chronoStart();
    },
    stop() {
      this.chronoStop();
    },
    chronoStart() {
      this.timer = setInterval(() => {
        this.totalSecondes += 1;
        this.time.secondes = (this.totalSecondes) % 60;
        this.time.minutes = Math.floor((this.totalSecondes / 60) % 60);
        this.time.heures = Math.floor(((this.totalSecondes / 3600)) % 24);
      }, 1000);
      this.setEtat(false, true, false);
      this.btnPlay = 'Continuer';
    },
    chronoStop() {
      clearInterval(this.timer);
      this.setEtat(true, false, true);
    },
    chronoReset() {
      this.totalSecondes = 0;
      this.time.heures = 0;
      this.time.minutes = 0;
      this.time.secondes = 0;
      this.setEtat(true, false, false);
      this.btnPlay = 'Démarrer';
    },
    setEtat(play, stop, backward) {
      this.etat.play = play;
      this.etat.stop = stop;
      this.etat.backward = backward;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
