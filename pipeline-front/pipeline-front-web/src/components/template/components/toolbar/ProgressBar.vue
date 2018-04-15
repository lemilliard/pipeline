<template>
  <v-progress-linear
    id="progressbar"
    indeterminate
    color="success"
    background-color="secondary"
    height="2"
  ></v-progress-linear>
</template>

<script>
export default {
  name: 'ProgressBar',
};
</script>

<style scoped>
  #progressbar {
    position: fixed;
    z-index: 5;
    margin: 0;
  }
</style>
