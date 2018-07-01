<template>
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
</template>

<script>
export default {
  name: 'Match',
  props: ['score'],
  data() {
    return {
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
    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
