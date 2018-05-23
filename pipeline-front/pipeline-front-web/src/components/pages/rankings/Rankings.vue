<template>
  <div class="rankings">
    <h1>{{ msg }}</h1>
    <v-data-table
      :headers="headers"
      :items="items"
      hide-actions
      class="elevation-1"
    >
      <template slot="items" slot-scope="props">
        <td>{{ props.item.name }}</td>
        <td class="text-xs-right">{{ props.item.actuellement }}</td>
        <td class="text-xs-right">{{ props.item.pad }}</td>
        <td class="text-xs-right">{{ props.item.pg }}</td>
        <td class="text-xs-right">{{ props.item.difference }}</td>
        <td class="text-xs-right">{{ props.item.points }}</td>
      </template>
    </v-data-table>
  </div>
</template>

<script>
export default {
  name: 'Rankings',
  data() {
    return {
      msg: 'Classement',
      headers: [
        {
          text: 'Dessert (100g serving)',
          align: 'left',
          sortable: false,
          value: 'name',
        },
        { text: 'Actuellement', value: 'actuellement' },
        { text: 'Point à défendre', value: 'pad' },
        { text: 'Point gagner cette semaine', value: 'pg' },
        { text: 'Différence', value: 'difference' },
        { text: 'Points', value: 'points' },
      ],
      items: [
        {
          value: false,
          name: 'Federer Roger',
          actuellement: 'Demi-finale, Indian Wells',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          name: 'Federer Roger',
          actuellement: 'Demi-finale, Indian Wells',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          name: 'Federer Roger',
          actuellement: 'Demi-finale, Indian Wells',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          name: 'Federer Roger',
          actuellement: 'Demi-finale, Indian Wells',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          name: 'Federer Roger',
          actuellement: 'Demi-finale, Indian Wells',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          name: 'Federer Roger',
          actuellement: 'Demi-finale, Indian Wells',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          name: 'Federer Roger',
          actuellement: 'Demi-finale, Indian Wells',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          name: 'Federer Roger',
          actuellement: 'Demi-finale, Indian Wells',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          name: 'Federer Roger',
          actuellement: 'Demi-finale, Indian Wells',
          pad: 1000,
          pg: 360,
          difference: -640,
          points: 9420,
        },
        {
          value: false,
          name: 'Federer Roger',
          actuellement: 'Demi-finale, Indian Wells',
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
