<template>
  <div class="tournament">
    <v-container>
      <v-layout row wrap>
        <h3>{{ msg }}</h3>
        <template v-for="(item, index) in items">
          <v-container :key="index" grid-list-md text-xs-center>
            <v-layout row wrap>
              <v-flex row-tournament xs12 sm12 md12>
                <v-card>
                  <v-layout row wrap>
                    <v-flex xs12 sm2 md2>
                      <v-card>
                        <v-card-text v-html="item.date"></v-card-text>
                      </v-card>
                    </v-flex>
                    <v-flex xs12 sm6 md6>
                      <v-card>
                        <v-card-text v-html="item.nom"></v-card-text>
                      </v-card>
                    </v-flex>
                    <v-flex xs12 sm2 md2>
                      <v-card :href="item.href" :to="{name: item.href, params: {id: item.id}}">
                        <!--<v-card-text v-html="item.link"></v-card-text>-->
                        <v-btn color="info">Voir les match</v-btn>
                      </v-card>
                    </v-flex>
                    <v-flex xs12 sm2 md2>
                      <v-card :href="item.href" :to="{name: item.href, params: {id: item.id}}">
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
  <!--
  <v-container fluid>
    <phase-dialog></phase-dialog>
    <phase :depth="depth" :parent="this"></phase>
  </v-container>
  -->
</template>

<script>
import PhaseDialog from './components/PhaseDialog';
import Phase from './components/Phase';


export default {
  name: 'Tournament',
  components: { Phase, PhaseDialog },
  data() {
    return {
      nextIdMatch: 0,
      idMatch: -1,
      depth: 3,
      dialog: false,
      msg: 'Liste des tournois',
      items: [
        {
          id: 0,
          nom: 'Roland Garros',
          date: '16/07/2018',
          link: 'Voir les matchs',
          subscribe: 'S\'abonner',
          href: 'Match',
          router: true,
        },
        {
          id: 1,
          nom: 'Wimbledon',
          date: '16/07/2018',
          link: 'Voir les matchs',
          subscribe: 'S\'abonner',
          href: 'Match',
          router: true,
        },
        {
          id: 2,
          nom: 'ATP Live',
          date: '16/07/2018',
          link: 'Voir les matchs',
          subscribe: 'S\'abonner',
          href: 'Match',
          router: true,
        },
      ],
    };
  },
};
</script>

<style scoped>

  .flex {
    padding: 0;
  }

  .card {
    box-shadow: none;
  }

  .row-tournament {
    border: 1px solid #ccc;
  }

</style>
