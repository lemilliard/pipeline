<template>
  <div class="match">
    <h1>{{ msg }}</h1>
    <v-container fluid>
      <v-layout row>
        <v-flex order-md5>
          <v-card dark tile flat color="red lighten-1">
            <v-card-text>#1</v-card-text>
          </v-card>
        </v-flex>
        <v-flex order-md7>
          <v-card dark tile flat color="red darken-1">
            <v-card-text>
              <iframe width="100%" height="315" src="https://www.youtube.com/embed/CtH_iPgKzAk" frameborder="0"
                      allow="autoplay; encrypted-media" allowfullscreen></iframe>
            </v-card-text>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
    <v-container grid-list-md text-xs-center>
      <v-layout row wrap>
        <v-flex xs12>
          <v-card dark color="warning">
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
    <v-container grid-list-md text-xs-center>
      <v-layout row wrap>
        <v-flex xs12>
          <v-card dark color="warning">
            <v-card-text class="px-0"><h2>Détails du match</h2></v-card-text>
          </v-card>
        </v-flex>
        <v-flex xs12>
          <v-data-iterator
            content-tag="v-layout"
            row
            wrap
            :items="items"
            :rows-per-page-items="rowsPerPageItems"
            :pagination.sync="pagination"
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
                    <v-list-tile-content>Calories:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.calories }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Fat:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.fat }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Carbs:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.carbs }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Protein:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.protein }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Sodium:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.sodium }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Calcium:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.calcium }}</v-list-tile-content>
                  </v-list-tile>
                  <v-list-tile>
                    <v-list-tile-content>Iron:</v-list-tile-content>
                    <v-list-tile-content class="align-end">{{ props.item.iron }}</v-list-tile-content>
                  </v-list-tile>
                </v-list>
              </v-card>
            </v-flex>
          </v-data-iterator>
        </v-flex>
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
    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  .match {
    width: 100%;
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
