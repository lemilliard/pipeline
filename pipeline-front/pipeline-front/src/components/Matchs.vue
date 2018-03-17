<template>
  <div class="matchs">
    <h1>{{ msg }}</h1>
    <template v-for="(item, index) in items">
      <v-container grid-list-xl text-xs-center>
        <v-layout row wrap>
          <v-flex xs12 sm12 md12>
            <v-card dark color="orange">
              <v-layout row wrap>
                <v-flex xs12 sm3 md3>
                  <v-card dark color="primary">
                    <v-card-text v-html="item.horaire"></v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 sm3 md3>
                  <v-card dark color="primary">
                    <v-card-text v-html="item.equipe1"></v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 sm3 md3>
                  <v-card dark color="primary">
                    <v-card-text v-html="item.equipe2"></v-card-text>
                  </v-card>
                </v-flex>
                <v-flex xs12 sm3 md3>
                  <v-card dark color="primary" :href="item.href" :to="{name: item.href}">
                    <v-card-text  v-html="item.link"></v-card-text>
                  </v-card>
                </v-flex>
              </v-layout>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </template>
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
            href: 'Match',
            router: true,
          },
          {
            equipe1: 'N.Djokovic',
            equipe2: 'E.Federer',
            horaire: '18:00',
            link: 'Voir le match',
            href: 'Match',
            router: true,
          },
          {
            equipe1: 'Equipe 1',
            equipe2: 'Equipe 2',
            horaire: '16:00',
            link: 'Voir le match',
            href: 'Match',
            router: true,
          },
          {
            equipe1: 'Equipe 1',
            equipe2: 'Equipe 2',
            horaire: '19:00',
            link: 'Voir le match',
            href: 'Match',
            router: true,
          },
          {
            equipe1: 'Equipe 1',
            equipe2: 'Equipe 2',
            horaire: '21:00',
            link: 'Voir le match',
            href: 'Match',
            router: true,
          },
        ],
      };
    },
    computed: {
      binding () {
        const binding = {}

        if (this.$vuetify.breakpoint.mdAndUp) binding.column = true

        return binding

      },
    },
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }
  .matchs {
    width: 100%;
  }
  .container.grid-list-xl {
    padding: 25px 0px;
  }
  .container {
      max-width: 100%;
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
