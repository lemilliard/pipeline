<template>
  <div class="add-tournament">
    <v-container>

        <v-form v-model="valid">
          <input type="file" :accept="accept" :multiple="false"
                 ref="file" @change="onFileChange">
          <br>
          <br>
          <v-btn @click="submit">submit</v-btn>
        </v-form>

    </v-container>
  </div>
</template>

<script>
export default {
  name: 'addtournament',
  data() {
    return {
      file: '',
      filename: '',
      msg: 'Welcome to settings page',
    };
  },
  methods: {
    submit() {
      const formData = new FormData();

      formData.append('file', this.file);

      this.axios.post('http://localhost:8080/tournoi/import', formData).then((response) => {
        console.log('Result: ', response);
        if (response && response.data && response.data) {
          console.log(response);
        }
      });
    },
    onFileChange() {
      this.file = this.$refs.file.files[0];
    },
  },
};
</script>

<style scoped>

</style>
