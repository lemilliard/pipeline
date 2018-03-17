<template>
  <v-app id="inspire" light>
    <atpHeader></atpHeader>
    <v-content>
      <v-container fluid fill-height>
        <v-layout>
          <v-fade-transition mode="out-in">
            <router-view></router-view>
          </v-fade-transition>
        </v-layout>
      </v-container>
    </v-content>
    <atpFooter></atpFooter>
  </v-app>
</template>

<script>
  import atpHeader from './components/Header';
  import atpFooter from './components/Footer';


  require('vuetify/dist/vuetify.min.css');

  export default {
    components: { atpHeader, atpFooter },
    data: () => ({
      message: 'ATP Live',
    }),
    props: {
      source: String,
    }
  }
</script>
