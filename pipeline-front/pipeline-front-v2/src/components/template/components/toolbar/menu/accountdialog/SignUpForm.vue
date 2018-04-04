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
            <v-flex xs12 sm6>
              <v-text-field
                label="Pseudo"
                required
                autofocus
                :value="user.pseudo"
                @input="updatePseudo"
              ></v-text-field>
            </v-flex>
            <v-flex xs12 sm6>
              <v-text-field
                label="Email"
                required
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
            <v-flex xs12 sm6>
              <v-text-field
                label="Confirm password"
                required
                type="password"
                v-model="passwordConfirm"
              ></v-text-field>
            </v-flex>
            <v-flex xs12 sm6>
              <v-text-field
                label="First name"
                :value="user.firstname"
                @input="updateFirstname"
              ></v-text-field>
            </v-flex>
            <v-flex xs12 sm6>
              <v-text-field
                label="Last name"
                :value="user.lastname"
                @input="updateLastname"
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
  import { mapState, mapActions, mapMutations } from 'vuex';
  import UserActionTypes from '@/store/data/actions/types';
  import UserMutationTypes from '@/store/data/mutations/types';

  export default {
    name: 'SignUpForm',
    data() {
      return {
        error: false,
        errorText: null,
        passwordConfirm: null,
      };
    },
    computed: {
      ...mapState({
        user: state => state.DataStore.user,
      }),
    },
    methods: {
      async signup() {
        if (!this.user.pseudo) {
          this.errorText = 'Pseudo is missing';
          this.error = true;
          return;
        }
        if (!this.user.email) {
          this.errorText = 'Email is missing';
          this.error = true;
          return;
        }
        if (!this.user.password) {
          this.errorText = 'Password is missing';
          this.error = true;
          return;
        }
        if (!this.passwordConfirm) {
          this.errorText = 'Password confirmation is missing';
          this.error = true;
          return;
        }
        if (this.passwordConfirm !== this.user.password) {
          this.errorText = 'Password confirmation is not corresponding';
        } else {
          const newUser = await this.signupUser(this.user);
          if (newUser && newUser.id_user) {
            this.$cookie.set('id_user', newUser.id_user);
          } else {
            this.errorText = 'Something went wrong!';
            this.error = true;
          }
        }
      },
      ...mapActions({
        signupUser: UserActionTypes.SIGNUP,
      }),
      ...mapMutations({
        updateEmail: UserMutationTypes.UPDATE_USER_EMAIL,
        updatePassword: UserMutationTypes.UPDATE_USER_PASSWORD,
        updatePseudo: UserMutationTypes.UPDATE_USER_PSEUDO,
        updateFirstname: UserMutationTypes.UPDATE_USER_FIRSTNAME,
        updateLastname: UserMutationTypes.UPDATE_USER_LASTNAME,
      }),
    },
  };
</script>

<style scoped>

</style>
