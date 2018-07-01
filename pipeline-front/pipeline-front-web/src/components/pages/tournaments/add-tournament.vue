<template>
  <div class="add-tournament">
    <v-container>

        <v-form v-model="valid">
          <v-text-field
            v-model="name"
            :rules="nameRules"
            :counter="10"
            label="Name"
            required
          ></v-text-field>
          <v-text-field
            v-model="email"
            :rules="emailRules"
            label="E-mail"
            required
          ></v-text-field>
          <v-text-field prepend-icon="attach_file" single-line
                        v-model="filename" :label="label" :required="required"
                        @click.native="onFocus"
                        :disabled="disabled" ref="fileTextField"></v-text-field>
          <input type="file" :accept="accept" :multiple="false" :disabled="disabled"
                 ref="fileInput" @change="onFileChange">
          <br>
          <br>
          <v-btn @click="submit">submit</v-btn>
          <v-btn @click="clear">clear</v-btn>
        </v-form>

    </v-container>
  </div>
</template>

<script>
export default {
  name: 'addtournament',
  data() {
    return {
      msg: 'Welcome to settings page',
    };
  },
};
</script>

<style scoped>

</style>
