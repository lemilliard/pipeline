<template>
  <v-dialog v-model="phaseDialog">
    <v-card>
      <v-card-title>
        {{ selectedIdMatch }}
      </v-card-title>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: 'PhaseDialog',
  data() {
    return {};
  },
  computed: {
    phaseDialog: {
      get() {
        return this.$parent.dialog;
      },
      set() {
        this.$parent.dialog = false;
      },
    },
    selectedIdMatch: {
      get() {
        return this.$parent.idMatch;
      },
      set() {
      },
    },
  },
};
</script>

<style scoped>

</style>
