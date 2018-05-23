<template>
  <div class="home">
    <v-container grid-list-md>
      <v-layout row wrap>
        <v-flex d-flex xs12 sm12 md8>
          <v-carousel>
            <v-carousel-item v-for="(match,i) in matchs" :src="match.src" :key="i">
              <v-jumbotron dark>
                <v-container fill-height>
                  <v-layout align-center>
                    <v-flex>
                      <h3 class="display-3">{{ match.title }}</h3>
                      <span class="subheading">{{ match.text }}</span>
                    </v-flex>
                  </v-layout>
                </v-container>
              </v-jumbotron>
            </v-carousel-item>
          </v-carousel>
        </v-flex>
        <v-flex d-flex xs12 sm6 md4>
          <v-layout row wrap>

            <v-flex d-flex>
              <v-layout row wrap>
                <v-flex
                  v-for="n in 2"
                  :key="n"
                  d-flex
                  xs12
                >
                  <v-card>
                    <v-card-media
                      class="white--text"
                      height="250px"
                      src="http://www.atpworldtour.com/-/media/images/news/2018/05/15/14/54/pouille-rome-2018-tuesday.jpg"
                    >
                      <v-container fill-height fluid>
                        <v-layout fill-height>
                          <v-flex xs12 align-end flexbox>
                            <span class="headline">Top 10 Australian beaches</span>
                            <v-card-title>
                              <div>
                                <span class="grey--text">Number 10</span><br>
                                <span>Whitehaven Beach</span><br>
                                <span>Whitsunday Island, Whitsunday Islands</span>
                              </div>
                            </v-card-title>
                            <v-card-actions>
                              <v-btn flat dark>Listen now</v-btn>
                            </v-card-actions>
                          </v-flex>
                        </v-layout>
                      </v-container>
                    </v-card-media>


                  </v-card>
                </v-flex>
              </v-layout>
            </v-flex>
          </v-layout>
        </v-flex>

      </v-layout>
    </v-container>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';
import DataActionsTypes from '@/store/data/actions/types';
import DataResourcesMap from '@/store/data/resources-map';
import DataResources from '@/store/data/resources';

export default {
  name: 'Home',
  data: () => ({
    lorem: 'Lorem ipsum dolor sit amet, mel at clita quando. Te sit oratio vituperatoribus, nam ad ipsum posidonium mediocritatem, explicari dissentiunt cu mea. Repudiare disputationi vim in, mollis iriure nec cu, alienum argumentum ius ad. Pri eu justo aeque torquatos.',
    matchs: [
      {
        src: 'http://www.atpworldtour.com/-/media/images/news/2018/05/15/17/40/shapovalov-rome-2018-tuesday.jpg',
        title: 'Top 10 Australian beaches',
        text: 'Lorem ipsum dolor sit amet, mel at clita quando. Te sit oratio vituperatoribus.',
      },
      {
        src: 'http://www.atpworldtour.com/-/media/images/news/2018/05/15/17/40/shapovalov-rome-2018-tuesday.jpg',
        title: 'Top 10 Australian beaches',
        text: 'Lorem ipsum dolor sit amet, mel at clita quando. Te sit oratio vituperatoribus.',
      },
    ],
  }),
  created() {
    this.retrieveData({ resource: DataResourcesMap.USERS.ws });
//    this.retrieveData({ resource: DataResourcesMap.USERS.ws, params: { id_user: 81 } });
  },
  computed: {
    ...mapState({
      users: state => state.DataStore[DataResources.USERS.name],
    }),
  },
  methods: {
    ...mapActions({
      retrieveData: DataActionsTypes.RETRIEVE_DATA,
    }),
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
</style>
