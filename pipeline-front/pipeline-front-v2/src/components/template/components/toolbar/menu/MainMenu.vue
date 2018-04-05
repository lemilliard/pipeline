<template>
  <v-menu bottom left>
    <v-btn
      icon
      large
      slot="activator"
    >
      <v-icon>{{ $global.icons.PROFILE }}</v-icon>
    </v-btn>
    <v-list>
      <admin-menu v-if="isAdmin"></admin-menu>
      <user-menu></user-menu>
    </v-list>
  </v-menu>
</template>

<script>
  import AdminMenu from './AdminMenu';
  import UserMenu from './UserMenu';

  export default {
    name: 'MainMenu',
    components: { AdminMenu, UserMenu },
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
