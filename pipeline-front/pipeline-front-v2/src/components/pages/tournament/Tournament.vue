<template>
  <v-container fluid>
    <phase-dialog></phase-dialog>
    <phase :depth="depth" :parent="this"></phase>
  </v-container>
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
    };
  },
};
</script>

<style scoped>

</style>
