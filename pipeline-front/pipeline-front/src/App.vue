<template>
  <v-app id="inspire" light>
    <atpHeader></atpHeader>
    <v-content>
      <atpBreadcrumbs></atpBreadcrumbs>
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
import atpHeader from './components/header/Header';
import atpFooter from './components/footer/Footer';
import atpBreadcrumbs from './components/Breadcrumbs';


require('vuetify/dist/vuetify.min.css');

export default {
  components: { atpHeader, atpFooter, atpBreadcrumbs },
  data: () => ({
    message: 'ATP Live',
  }),
  props: {
    source: String,
  },
};
</script>
