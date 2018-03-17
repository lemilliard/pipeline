<template>
  <div id="login">
    <v-layout row justify-center>
      <v-dialog v-model="dialog" persistent max-width="500px">
        <v-btn color="warning" slot="activator"><v-icon>account_circle</v-icon> Login</v-btn>
        <v-card>
          <v-card-title>
            <span class="headline">Login</span>
          </v-card-title>
          <v-card-text>
            <v-container grid-list-md>
              <v-layout wrap>
                <v-flex xs12>
                  <v-text-field label="Email" required></v-text-field>
                </v-flex>
                <v-flex xs12>
                  <v-text-field label="Password" type="password" required></v-text-field>
                </v-flex>
              </v-layout>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" flat @click.native="dialog = false">Sign in</v-btn>
            <v-btn color="blue darken-1" flat @click.native="dialog = false">Sign up</v-btn>
            <v-btn color="blue darken-1" flat @click.native="dialog = false">Close</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-layout>
  </div>
</template>

<script>

  export default {
    name: 'atpLogin',
    data() {
      return {
        dialog: false,
      };
    },
    mounted() {
    },
  }
</script>
