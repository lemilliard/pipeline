<template>
  <v-card flat>
    <v-alert color="error" icon="warning" v-model="error" dismissible outline>
      {{ errorText }}
    </v-alert>
    <v-card-title>
      Sign in to your account
      <v-spacer></v-spacer>
      <small>*indicates required field</small>
    </v-card-title>
    <v-card-text>
      <v-container grid-list-md>
        <form method="get" v-on:submit.prevent="signin">
          <v-layout wrap>
            <v-flex xs12 sm6>
              <v-text-field
                label="Email"
                required
                autofocus
                v-model="connector.email"
              ></v-text-field>
            </v-flex>
            <v-flex xs12 sm6>
              <v-text-field
                label="Password"
                required
                type="password"
                v-model="connector.password"
              ></v-text-field>
            </v-flex>
          </v-layout>
        </form>
      </v-container>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue" dark @click.stop="signin">Sign in</v-btn>
      <v-spacer></v-spacer>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapActions } from 'vuex';
import DataActionTypes from '@/store/data/actions/types';

export default {
  name: 'SignInForm',
  data() {
    return {
      error: false,
      errorText: null,
      connector: {
        email: null,
        password: null,
      },
    };
  },
  methods: {
    signin() {
      if (this.connector && this.connector.email && this.connector.password) {
        this.connect(this.connector);
        this.errorText = null;
        this.error = false;
      } else {
        this.errorText = 'Invalid email or password';
        this.error = true;
      }
    },
    ...mapActions({
      connect: DataActionTypes.CONNECT,
    }),
  },
}
;
</script>

<style>

</style>
