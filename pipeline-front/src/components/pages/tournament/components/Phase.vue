<template>
  <v-layout row wrap>
    <v-flex xs12>
      <v-flex xs6 offset-xs3 @click.stop="openDialog">
        <v-card dark color="primary" class="item">
          <v-card-text></v-card-text>
        </v-card>
      </v-flex>
      <br/>
      <br/>
      <v-flex xs12>
        <v-layout row wrap>
          <v-flex xs6>
            <phase v-if="depth > 1" :depth="depth - 1" :parent="parent"></phase>
          </v-flex>
          <v-flex xs6>
            <phase v-if="depth > 1" :depth="depth - 1" :parent="parent"></phase>
          </v-flex>
        </v-layout>
      </v-flex>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
  name: 'Phase',
  props: ['depth', 'parent'],
  data() {
    return {
      idMatch: -1,
    };
  },
  created() {
    this.idMatch = this.parent.nextIdMatch;
    this.parent.nextIdMatch += 1;
  },
  methods: {
    openDialog() {
      this.parent.idMatch = this.idMatch;
      this.parent.dialog = true;
    },
  },
};
</script>

<style scoped>
  .item {
    cursor: pointer;
  }
</style>
