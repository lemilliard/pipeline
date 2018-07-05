<template>
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
            <td
              v-for="(propName, index) in Object.keys(props.item)"
              :key="index"
              class="text-xs-center">
              {{ props.item[propName] }}
            </td>
          </template>
        </v-data-table>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
export default {
  name: 'ScoreDetails',
  props: ['score', 'joueursEquipeUne', 'joueursEquipeDeux'],
  data() {
    return {};
  },
  computed: {
    headers() {
      const headers = [];
      if (this.score) {
        let setText;
        let pointKey;
        headers.push({ text: 'Equipe', value: 'equipe', sortable: false, align: 'center' });
        Object.keys(this.score.sets).forEach((set) => {
          setText = `Set ${parseInt(set, 10) + 1}`;
          pointKey = `Set${parseInt(set, 10) + 1}`;
          headers.push({ text: setText, value: pointKey, sortable: false, align: 'center' });
        });
      }
      return headers;
    },
    items() {
      const items = [];
      if (this.score && this.joueursEquipeUne[0] && this.joueursEquipeUne[1]) {
        let pointKey;
        items.push({
          equipe: `${this.joueursEquipeUne[0].nom} - ${this.joueursEquipeUne[1].nom}`,
        });
        items.push({
          equipe: `${this.joueursEquipeDeux[0].nom} - ${this.joueursEquipeDeux[1].nom}`,
        });
        Object.keys(this.score.sets).forEach((set) => {
          pointKey = `Set${parseInt(set, 10) + 1}`;
          items[0][pointKey] = this.score.sets[set].jeuxEquipeUne;
          items[1][pointKey] = this.score.sets[set].jeuxEquipeDeux;
        });
      }
      return items;
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
