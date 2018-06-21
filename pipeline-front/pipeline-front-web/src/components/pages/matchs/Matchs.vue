<template>
  <div class="matchs">
    <v-container>
      <v-layout row wrap>
        <h3>{{ msg }}</h3>
        <template v-for="(item, index) in items">
          <v-container :key="index" grid-list-md text-xs-center>
            <v-layout row wrap>
              <v-flex row-match xs12 sm12 md12>
                <v-card  >
                  <v-layout row wrap>
                    <v-flex xs12 sm2 md2>
                      <v-card  >
                        <v-card-text v-html="item.horaire"></v-card-text>
                      </v-card>
                    </v-flex>
                    <v-flex xs12 sm3 md3>
                      <v-card  >
                        <v-card-text v-html="item.equipe1"></v-card-text>
                      </v-card>
                    </v-flex>
                    <v-flex xs12 sm3 md3>
                      <v-card  >
                        <v-card-text v-html="item.equipe2"></v-card-text>
                      </v-card>
                    </v-flex>
                    <v-flex xs12 sm2 md2>
                      <v-card  :href="item.href" :to="{name: item.href}">
                        <!--<v-card-text v-html="item.link"></v-card-text>-->
                        <v-btn color="info">Voir le match</v-btn>
                      </v-card>
                    </v-flex>
                    <v-flex xs12 sm2 md2>
                      <v-card  :href="item.href" :to="{name: item.href}">
                        <!--<v-card-text v-html="item.subscribe">


                        </v-card-text>-->
                        <v-btn color="warning">S'abonner</v-btn>
                      </v-card>
                    </v-flex>
                  </v-layout>
                </v-card>
              </v-flex>
            </v-layout>
          </v-container>
        </template>
      </v-layout>
    </v-container>
  </div>
</template>

<script>

export default {
  name: 'Matchs',
  data() {
    return {
      msg: 'Matchs en direct',
      items: [
        {
          equipe1: 'R.Nadal',
          equipe2: 'W.Tsonga',
          horaire: '16:00',
          link: 'Voir le match',
          subscribe: 'S\'abonner',
          href: 'Match',
          router: true,
        },
        {
          equipe1: 'N.Djokovic',
          equipe2: 'E.Federer',
          horaire: '18:00',
          link: 'Voir le match',
          subscribe: 'S\'abonner',
          href: 'Match',
          router: true,
        },
        {
          equipe1: 'Equipe 1',
          equipe2: 'Equipe 2',
          horaire: '16:00',
          link: 'Voir le match',
          subscribe: 'S\'abonner',
          href: 'Match',
          router: true,
        },
        {
          equipe1: 'Equipe 1',
          equipe2: 'Equipe 2',
          horaire: '19:00',
          link: 'Voir le match',
          subscribe: 'S\'abonner',
          href: 'Match',
          router: true,
        },
        {
          equipe1: 'Equipe 1',
          equipe2: 'Equipe 2',
          horaire: '21:00',
          link: 'Voir le match',
          subscribe: 'S\'abonner',
          href: 'Match',
          router: true,
        },
      ],
    };
  },
  computed: {
    binding() {
      const binding = {};

      if (this.$vuetify.breakpoint.mdAndUp) binding.column = true;

      return binding;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  .container.grid-list-xl {
    padding: 25px 0px;
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
