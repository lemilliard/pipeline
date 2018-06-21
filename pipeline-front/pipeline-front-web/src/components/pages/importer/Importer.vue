<template>
  <div>
    <v-container>
      <v-form>
        <v-select
          :items="types"
          v-model="type"
          single-line
          item-text="value"
          item-value="value"
        ></v-select>
        {{ type }}
      </v-form>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'Importer',
  data() {
    return {
      types: [
        'CSV',
        'Excel',
      ],
      type: '',
    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
