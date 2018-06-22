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
            :rows-per-page-items="rowsPerPageItems"
            :pagination.sync="pagination"
            content-tag="v-layout"
            row
            wrap
          >
            <v-flex
              slot="stat"
              slot-scope="props"
              xs12
              sm6
              md4
              lg3
            >
              <v-card>
                <v-card-title><h4>{{ props.stat.name }}</h4></v-card-title>
                <v-divider></v-divider>
                <v-list dense>
                  <v-list-tile>
                    <v-list-tile-content>Calories:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ propsstat.stat.calories }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Fat:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ propsstat.stat.fat }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Carbs:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ propsstat.stat.carbs }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Protein:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ propsstat.stat.protein }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Sodium:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ propsstat.stat.sodium }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Calcium:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ propsstat.stat.calcium }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Iron:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ propsstat.stat.iron }}</v-list-tile-content>
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
      rowsPerPageItems: [2, 8, 12],
      pagination: {
        rowsPerPage: 2,
      },
      stats: [
        {
          value: false,
          name: 'Frozen Yogurt',
          calories: 159,
          fat: 6.0,
          carbs: 24,
          protein: 4.0,
          sodium: 87,
          calcium: '14%',
          iron: '1%',
        },
        {
          value: false,
          name: 'Ice cream sandwich',
          calories: 237,
          fat: 9.0,
          carbs: 37,
          protein: 4.3,
          sodium: 129,
          calcium: '8%',
          iron: '1%',
        },
        {
          value: false,
          name: 'Eclair',
          calories: 262,
          fat: 16.0,
          carbs: 23,
          protein: 6.0,
          sodium: 337,
          calcium: '6%',
          iron: '7%',
        },
        {
          value: false,
          name: 'Cupcake',
          calories: 305,
          fat: 3.7,
          carbs: 67,
          protein: 4.3,
          sodium: 413,
          calcium: '3%',
          iron: '8%',
        },
        {
          value: false,
          name: 'Gingerbread',
          calories: 356,
          fat: 16.0,
          carbs: 49,
          protein: 3.9,
          sodium: 327,
          calcium: '7%',
          iron: '16%',
        },
        {
          value: false,
          name: 'Jelly bean',
          calories: 375,
          fat: 0.0,
          carbs: 94,
          protein: 0.0,
          sodium: 50,
          calcium: '0%',
          iron: '0%',
        },
        {
          value: false,
          name: 'Lollipop',
          calories: 392,
          fat: 0.2,
          carbs: 98,
          protein: 0,
          sodium: 38,
          calcium: '0%',
          iron: '2%',
        },
        {
          value: false,
          name: 'Honeycomb',
          calories: 408,
          fat: 3.2,
          carbs: 87,
          protein: 6.5,
          sodium: 562,
          calcium: '0%',
          iron: '45%',
        },
        {
          value: false,
          name: 'Donut',
          calories: 452,
          fat: 25.0,
          carbs: 51,
          protein: 4.9,
          sodium: 326,
          calcium: '2%',
          iron: '22%',
        },
        {
          value: false,
          name: 'KitKat',
          calories: 518,
          fat: 26.0,
          carbs: 65,
          protein: 7,
          sodium: 54,
          calcium: '12%',
          iron: '6%',
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
