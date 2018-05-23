<template>
  <div class="calendrier">
    <v-container>
      <v-layout row wrap>
        <h3>{{ msg }}</h3>
        <v-container>
          <v-layout row wrap>
            <v-flex xs6>
              <v-date-picker
                v-model="date"
                landscape
              ></v-date-picker>
            </v-flex>
          </v-layout>
        </v-container>
      </v-layout>
    </v-container>
  </div>
</template>

<script>
export default {
  name: 'Calendrier',
  data() {
    return {
      msg: 'Calendrier',
    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
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
