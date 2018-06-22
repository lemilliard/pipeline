<template>
  <div class="organiser">
    <v-container>
      <v-layout row wrap>
        <h3>{{ msg }}</h3>
        <v-container grid-list-md text-xs-center>
          <v-layout row wrap>
            <template v-for="(item, index) in items">
              <v-flex xs12 sm3 md3>
                <v-card dark color="green">
                  <v-card-text class="px-0" v-html="item.horaire">
                  </v-card-text>
                </v-card>
              </v-flex>
              <v-flex xs12 sm3 md3>
                <v-card>
                  <v-card-text v-html="item.equipe1"></v-card-text>
                  <!--<v-card-media class="img-equipe" :src="item.img_equipe1" height="200px">
                  </v-card-media>-->
                </v-card>
              </v-flex>
              <v-flex xs12 sm3 md3>
                <v-card>
                  <v-card-text v-html="item.equipe2"></v-card-text>
                  <!--<v-card-media class="img-equipe" :src="item.img_equipe2" height="200px">
                  </v-card-media>-->
                </v-card>
              </v-flex>
              <v-flex xs12 sm3 md3>
                <v-card color="yellow" :href="item.href" :to="{name: item.href}">
                  <v-card-text v-html="item.link"></v-card-text>
                </v-card>
              </v-flex>
            </template>
          </v-layout>
        </v-container>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
  export default {
    name: 'Organiser',
    data() {
      return {
        msg: 'Organiser',
        lorem: 'test lorem ipsum',
        items: [
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
          {
            equipe1: 'R.Nadal',
            img_equipe1: 'http://www.atpworldtour.com/-/media/tennis/players/gladiator/2017/nadal_fullao17.png',
            equipe2: 'E.Federer',
            img_equipe2: 'http://www.atpworldtour.com/-/media/tennis/players/gladiator/2018/federer_full_ao18.png',
            horaire: '18:00',
            link: 'Lancer le match',
            href: 'Match',
            router: true,
          },
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
          {
            equipe1: 'R.Nadal',
            img_equipe1: 'http://www.atpworldtour.com/-/media/tennis/players/gladiator/2017/nadal_fullao17.png',
            equipe2: 'E.Federer',
            img_equipe2: 'http://www.atpworldtour.com/-/media/tennis/players/gladiator/2018/federer_full_ao18.png',
            horaire: '19:00',
            link: 'Lancer le match',
            href: 'Match',
            router: true,
          },
          {
            equipe1: 'R.Nadal',
            img_equipe1: 'http://www.atpworldtour.com/-/media/tennis/players/gladiator/2017/nadal_fullao17.png',
            equipe2: 'E.Federer',
            img_equipe2: 'http://www.atpworldtour.com/-/media/tennis/players/gladiator/2018/federer_full_ao18.png',
            horaire: '21:00',
            link: 'Lancer le match',
            href: 'Match',
            router: true,
          },
        ],
      };
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
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

  .img-equipe .card__media__background {
    background-position: center top !important;
  }

</style>
