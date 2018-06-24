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
      <v-tabs grow v-model="active">
        <v-tab>Sign in</v-tab>
        <v-tab>Sign up</v-tab>
        <v-tab-item>
          <sign-in-form></sign-in-form>
        </v-tab-item>
        <v-tab-item>
          <sign-up-form></sign-up-form>
        </v-tab-item>
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
    return {
      active: null,
    };
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
