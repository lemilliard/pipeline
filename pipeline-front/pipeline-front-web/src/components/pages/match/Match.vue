<template>
  <div class="match">
    <v-container>
      <v-layout row wrap>
        <v-flex xs12 sm3 md3 >
          <h3>{{ msg }}</h3>
        </v-flex>
        <v-flex xs3 sm3 md3 offset-md6 follow-match>
          <v-btn color="primary" dark>Suivre le match</v-btn>
        </v-flex>

        <v-container grid-list-md text-xs-center>
          <v-layout row wrap>
            <template v-for="(match, index) in matchs">
              <v-flex  justify-space-between fill-height xs12 sm2 md2>
                <v-btn color="success">+</v-btn>
                <v-btn color="warning">Avertissement</v-btn>
                <v-btn color="info">Soigneur</v-btn>
              </v-flex>
              <v-flex xs6 sm2 md2>
                <v-btn class="hide-on-desktop" color="success">+</v-btn>

                <v-card>
                  <v-card-text v-html="match.equipe1"></v-card-text>
                  <v-card-media class="img-equipe" :src="match.img_equipe1" height="400px">
                  </v-card-media>
                </v-card>
              </v-flex>

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
                        <div class="headline">6</div>

                      </v-card-title>

                    </v-card>
                  </v-flex>
                  <v-flex xs6 sm6 md6 >
                    <v-card color="blue-grey darken-2" class="white--text">
                      <v-card-title primary-title>
                        <div class="headline">3</div>

                      </v-card-title>

                    </v-card>
                  </v-flex>
                </v-layout>
              </v-flex>
              <v-flex xs6 sm2 md2>
                <v-btn class="hide-on-desktop" color="success">+</v-btn>
                <v-card>
                  <v-card-text v-html="match.equipe2"></v-card-text>
                  <v-card-media class="img-equipe" :src="match.img_equipe2" height="400px">
                  </v-card-media>
                </v-card>
              </v-flex>
              <v-flex xs12 sm2 md2>
                <v-btn color="success">+</v-btn>
                <v-btn color="warning">Avertissement</v-btn>
                <v-btn color="info">Soigneur</v-btn>
              </v-flex>
            </template>
          </v-layout>
        </v-container>

        <v-container grid-list-md text-xs-center>
          <v-layout row wrap>
            <v-flex xs12>
              <v-card dark class="heading" height="50px">
                <v-card-text class="px-0"><h2>Match simple - Score et détails</h2></v-card-text>
              </v-card>
            </v-flex>
            <v-flex xs12>
              <v-data-table
                :headers="headers"
                :items="items"
                hide-actions
                class="elevation-1"
              >
                <template slot="items" slot-scope="props">
                  <td>{{ props.item.name }}</td>
                  <td class="text-xs-right">{{ props.item.Set1 }}</td>
                  <td class="text-xs-right">{{ props.item.Set2 }}</td>
                  <td class="text-xs-right">{{ props.item.Set3 }}</td>
                  <td class="text-xs-right">{{ props.item.Set4 }}</td>
                  <td class="text-xs-right">{{ props.item.Set5 }}</td>
                </template>
              </v-data-table>
            </v-flex>

          </v-layout>
        </v-container>

        <v-container fluid grid-list-md>
          <v-data-iterator
            :items="stats"
            content-tag="v-layout"
            row
            wrap
          >
            <v-flex
              slot="item"
              slot-scope="props"
              xs12
              sm6
              md6
              lg6
            >
              <v-card>
                <v-card-title><h4>{{ props.item.name }}</h4></v-card-title>
                <v-divider></v-divider>
                <v-list dense>
                  <v-list-tile>
                    <v-list-tile-content>% de service réussis :</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.serviceswin }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Balles de break :</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.breakballs }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Balles de match :</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.matchballs }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Balles de sets gagnées :</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.matchsetballswin }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Balles de sets perdus :</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.matchsetballsloose }}</v-list-tile-content>
                  </v-list-tile>
                </v-list>
              </v-card>
            </v-flex>
          </v-data-iterator>
        </v-container>

      </v-layout>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'Match',
  data() {
    return {
      msg: 'Match',
      lorem: 'test lorem ipsum',
      matchs: [
        {
          equipe1: 'R.Nadal',
          img_equipe1: 'http://www.atpworldtour.com/-/media/tennis/players/gladiator/2017/nadal_fullao17.png',
          equipe2: 'E.Federer',
          img_equipe2: 'http://www.atpworldtour.com/-/media/tennis/players/gladiator/2018/federer_full_ao18.png',
          horaire: '16:00',
          link: 'Lancer le match',
          href: 'Match',
          router: true,
        },
      ],
      headers: [
        {
          text: 'Joueurs',
          align: 'left',
          sortable: false,
          value: 'name',
        },
        { text: 'Set 1', value: 'Set1' },
        { text: 'Set 2', value: 'Set2' },
        { text: 'Set 3', value: 'Set3' },
        { text: 'Set 4', value: 'Set4' },
        { text: 'Set 5', value: 'Set5' },
      ],
      items: [
        {
          value: false,
          name: 'Novak Djokovic [2]',
          Set1: 6,
          Set2: 4,
          Set3: 6,
          Set4: 3,
          Set5: 4,
        },
        {
          value: false,
          name: 'Rafael Nadal [1]',
          Set1: 2,
          Set2: 6,
          Set3: 3,
          Set4: 6,
          Set5: 3,
        },
      ],
      stats: [
        {
          value: false,
          name: 'Rafael Nadal',
          serviceswin: 159,
          breakballs: 6.0,
          matchballs: 24,
          matchsetballswin: 4.0,
          matchsetballsloose: 87,
        },
        {
          value: false,
          name: 'Roger Federer',
          serviceswin: 159,
          breakballs: 6.0,
          matchballs: 24,
          matchsetballswin: 4.0,
          matchsetballsloose: 87,
        },
      ],
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
