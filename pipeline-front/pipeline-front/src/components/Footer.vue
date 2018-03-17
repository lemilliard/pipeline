<template>
  <div id="footer">
    <v-footer app fixed dark>
        <v-card-text flat tile class="white--text">
           <span>&copy; 2017</span>&nbsp;<strong>{{ message }}</strong>
        </v-card-text>
        <v-spacer></v-spacer>
        <v-card-text class="text-xs-right">
          <v-btn
            v-for="icon in icons"
            :key="icon"
            icon
            class="mx-3 white--text"
          >
            <v-icon size="18px">{{ icon }}</v-icon>
          </v-btn>
        </v-card-text>
      </v-footer>
  </div>
</template>

<script>

  export default {
    name: 'atpFooter',
    data() {
      return {
        message: 'ATP Live',
        icons: ['fa-facebook', 'fa-twitter', 'fa-google-plus', 'fa-linkedin', 'fa-instagram'],
      };
    },
  };
</script>

