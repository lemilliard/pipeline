<template>
  <v-dialog max-width="500px">
    <v-btn
      icon
      large
      slot="activator"
      v-model="loginDialog"
    >
      <v-icon>{{ $global.icons.LOGIN }}</v-icon>
    </v-btn>
    <v-card>
      <v-tabs dark grow>
        <v-tabs-bar>
          <v-tabs-item href="#signin">Sign in</v-tabs-item>
          <v-tabs-item href="#signup">Sign up</v-tabs-item>
          <v-tabs-slider></v-tabs-slider>
        </v-tabs-bar>
        <v-tabs-items>
          <v-tabs-content id="signin">
            <sign-in-form></sign-in-form>
          </v-tabs-content>
          <v-tabs-content id="signup">
            <sign-up-form></sign-up-form>
          </v-tabs-content>
        </v-tabs-items>
      </v-tabs>
    </v-card>
  </v-dialog>
</template>

<script>
  import SignInForm from './SignInForm';
  import SignUpForm from './SignUpForm';

  export default {
    name: 'AccountDialog',
    components: { SignInForm, SignUpForm },
    data() {
      return {};
    },
    computed: {
      loginDialog: {
        get() {
          return this.$store.state.TemplateStore.loginDialog;
        },
        set() {
        },
      },
    },
  };
</script>

<style scoped>

</style>
