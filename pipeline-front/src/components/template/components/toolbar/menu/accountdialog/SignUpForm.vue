<template>
  <v-card flat>
    <v-alert color="error" icon="warning" v-model="error" dismissible outline>
      {{ errorText }}
    </v-alert>
    <v-card-title>
      Create an account
      <v-spacer></v-spacer>
      <small>*indicates required field</small>
    </v-card-title>
    <v-card-text>
      <v-container grid-list-md>
        <form method="get" @submit.prevent="signup">
          <v-layout wrap>
            <v-flex xs12 sm12>
              <v-text-field
                label="Email"
                required
                v-model="registrator.email"
              ></v-text-field>
            </v-flex>
            <v-flex xs12 sm6>
              <v-text-field
                label="Password"
                required
                type="password"
                v-model="registrator.password"
              ></v-text-field>
            </v-flex>
            <v-flex xs12 sm6>
              <v-text-field
                label="Confirm password"
                required
                type="password"
                v-model="registrator.confirmPassword"
              ></v-text-field>
            </v-flex>
            <v-flex xs12 sm6>
              <v-text-field
                label="First name"
                required
                v-model="registrator.prenom"
              ></v-text-field>
            </v-flex>
            <v-flex xs12 sm6>
              <v-text-field
                label="Last name"
                required
                v-model="registrator.nom"
              ></v-text-field>
            </v-flex>
          </v-layout>
        </form>
      </v-container>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken-3" @click.stop="signup">Sign up</v-btn>
      <v-spacer></v-spacer>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapActions } from 'vuex';
import DataActionTypes from '@/store/data/actions/types';

export default {
  name: 'SignUpForm',
  data() {
    return {
      error: false,
      errorText: null,
      registrator: {
        email: null,
        password: null,
        confirmPassword: null,
        nom: null,
        prenom: null,
      },
    };
  },
  methods: {
    async signup() {
      if (!this.registrator.email) {
        this.errorText = 'Email is missing';
        this.error = true;
        return;
      }
      if (!this.registrator.password) {
        this.errorText = 'Password is missing';
        this.error = true;
        return;
      }
      if (!this.registrator.confirmPassword) {
        this.errorText = 'Password confirmation is missing';
        this.error = true;
        return;
      }
      if (this.registrator.confirmPassword !== this.registrator.password) {
        this.errorText = 'Password confirmation is not corresponding';
      } else {
        this.register(this.registrator);
      }
    },
    ...mapActions({
      register: DataActionTypes.REGISTER,
    }),
  },
};
</script>

<style scoped>

</style>
