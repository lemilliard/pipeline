<template>
  <div id="footer">
    <v-footer app fixed>
      <span>&copy; 2017</span>&nbsp;<strong>{{ message }}</strong>
    </v-footer>
  </div>
</template>

<script>

  export default {
    name: 'atpFooter',
    data() {
      return {
        message: 'ATP Live',
      };
    },
  };
</script>

