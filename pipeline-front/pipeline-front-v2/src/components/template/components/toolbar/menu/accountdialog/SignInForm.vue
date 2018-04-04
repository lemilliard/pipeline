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
                :value="user.email"
                @input="updateEmail"
              ></v-text-field>
            </v-flex>
            <v-flex xs12 sm6>
              <v-text-field
                label="Password"
                required
                type="password"
                :value="user.password"
                @input="updatePassword"
              ></v-text-field>
            </v-flex>
          </v-layout>
        </form>
      </v-container>
    </v-card-text>
    <v-card-actions>
      <v-spacer></v-spacer>
      <v-btn color="blue darken-3" @click.stop="signin">Sign in</v-btn>
      <v-spacer></v-spacer>
    </v-card-actions>
  </v-card>
</template>

<script>
  import { mapState, mapActions, mapMutations } from 'vuex';
  import UserActionTypes from '@/store/data/actions/types';
  import UserMutationTypes from '@/store/data/mutations/types';

  export default {
    name: 'SignInForm',
    data() {
      return {
        error: false,
        errorText: null,
      };
    },
    computed: {
      ...mapState({
        user: state => state.DataStore.user,
      }),
    },
    methods: {
      async signin() {
        const newUser = await this.signinUser(this.user);
        if (newUser && newUser.id_user) {
          this.$cookie.set('id_user', newUser.id_user);
        } else {
          this.errorText = 'Invalid email or password';
          this.error = true;
        }
      },
      ...mapActions({
        signinUser: UserActionTypes.SIGNIN,
      }),
      ...mapMutations({
        updateEmail: UserMutationTypes.UPDATE_USER_EMAIL,
        updatePassword: UserMutationTypes.UPDATE_USER_PASSWORD,
      }),
    },
  };
</script>

<style>

</style>
