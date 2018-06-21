<template>
  <div class="classement">
    <v-container>
      <v-layout row wrap>
        <h3>{{ msg }}</h3>
        <v-data-table
          :headers="headers"
          :items="items"
          hide-actions
          class="elevation-1"
        >
          <template slot="items" slot-scope="props">
            <td>{{ props.item.ranking }}</td>
            <td>{{ props.item.move }}</td>
            <td>{{ props.item.country }}</td>
            <td>{{ props.item.player }}</td>
            <td class="">{{ props.item.age }}</td>
            <td class="">{{ props.item.points }}</td>
            <td class="">{{ props.item.pad }}</td>
            <td class="">{{ props.item.pg }}</td>
            <td class="">{{ props.item.difference }}</td>
            <td class=""><v-btn color="warning">S'abonner</v-btn></td>

          </template>
        </v-data-table>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'Classement',
  data() {
    return {
      msg: 'Classement',
      headers: [
        {
          text: 'Ranking',
          align: 'left',
          sortable: true,
          value: 'ranking',
        },
        { text: 'Move', align: 'left', sortable: true, value: 'move' },
        { text: 'Country', align: 'left', sortable: true, value: 'country' },
        { text: 'Player', align: 'left', sortable: true, value: 'player' },
        { text: 'Ages', align: 'left', sortable: true, value: 'ages' },
        { text: 'Points', align: 'left', sortable: true, value: 'points' },
        { text: 'PAD', align: 'left', sortable: true, value: 'pad' },
        { text: 'PGS', align: 'left', sortable: true, value: 'pg' },
        { text: 'Différence', align: 'left', sortable: true, value: 'difference' },
        { text: 'Suivre', align: 'left', sortable: false, value: 'Follow' },

      ],
      items: [
        {
          value: false,
          ranking: '1',
          move: '-',
          country: 'Suisse',
          player: 'Federer Roger',
          age: '34',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          ranking: '1',
          move: '-',
          country: 'Suisse',
          player: 'Federer Roger',
          age: '34',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          ranking: '1',
          move: '-',
          country: 'Suisse',
          player: 'Federer Roger',
          age: '34',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          ranking: '1',
          move: '-',
          country: 'Suisse',
          player: 'Federer Roger',
          age: '34',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          ranking: '1',
          move: '-',
          country: 'Suisse',
          player: 'Federer Roger',
          age: '34',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          ranking: '1',
          move: '-',
          country: 'Suisse',
          player: 'Federer Roger',
          age: '34',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          ranking: '1',
          move: '-',
          country: 'Suisse',
          player: 'Federer Roger',
          age: '34',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          ranking: '1',
          move: '-',
          country: 'Suisse',
          player: 'Federer Roger',
          age: '34',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          ranking: '1',
          move: '-',
          country: 'Suisse',
          player: 'Federer Roger',
          age: '34',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          ranking: '1',
          move: '-',
          country: 'Suisse',
          player: 'Federer Roger',
          age: '34',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          ranking: '1',
          move: '-',
          country: 'Suisse',
          player: 'Federer Roger',
          age: '34',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
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

  .classement {
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
