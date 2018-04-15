<template>
  <v-container fluid>
    <v-layout row wrap>
      <v-flex xs12>
        <v-layout justify-center align-center>
          <h2 class="text-xs-center error-404"><span class="error-404-title" v-for="(letter, index) in titleArray"
                                                       :key="index">{{ letter }}</span></h2>
        </v-layout>
      </v-flex>
      <v-flex xs12>
        <v-layout justify-center>
          <h2 class="text-xs-center error-404-text">{{ text }}</h2>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
  export default {
    name: 'Error404',
    data() {
      return {
        title: 'Error 404',
        text: "This page doesn't exist",
      };
    },
    computed: {
      titleArray() {
        return this.title.split('');
      },
    },
  };
</script>

<style scoped>
  h2.error-404-text {
    font-weight: bold;
    text-shadow: 0 0 10px #000;
  }

  h2.error-404 {
    margin-bottom: 10%;
    word-spacing: 10px;
  }

  img {
    -webkit-filter: drop-shadow(0px 0px 10px #000);
    filter: drop-shadow(0px 0px 10px #000);
    margin-bottom: 15px;
  }

  img:hover {
    animation: shake 0.82s cubic-bezier(.36, .07, .19, .97) both;
    transform: translate3d(0, 0, 0);
    backface-visibility: hidden;
  }

  .error-404-title {
    font-family: Monoton, cursive;
    text-transform: uppercase;
    text-shadow: 0 0 10px #ffffff, 0 0 20px #008000, 0 0 6px #0000ff;
    color: #fff;
  }

  .error-404-title:nth-of-type(1) {
    animation: light 15s linear infinite;
  }

  .error-404-title:nth-of-type(2) {
    animation: light 11s linear infinite;
  }

  .error-404-title:nth-of-type(4) {
    animation: light 20s linear infinite;
  }

  .error-404-title:nth-of-type(5) {
    animation: light 7s linear infinite;
  }

  .error-404-title:nth-of-type(9) {
    animation: light 5s linear infinite;
  }

  .error-404-title:nth-of-type(11) {
    animation: light 9s linear infinite;
  }

  @keyframes light {
    0%, 19.999%, 22%, 62.999%, 64%, 64.999%, 70%, 100% {
      opacity: .99;
      text-shadow: 0 0 10px #ffffff, 0 0 20px #008000, 0 0 6px #0000ff;
    }
    20%, 21.999%, 63%, 63.999%, 65%, 69.999% {
      opacity: 0.4;
      text-shadow: none;
    }
  }

  @keyframes shake {
    10%, 90% {
      transform: translate3d(-1px, 0, 0);
    }

    20%, 80% {
      transform: translate3d(2px, 0, 0);
    }

    30%, 50%, 70% {
      transform: translate3d(-4px, 0, 0);
    }

    40%, 60% {
      transform: translate3d(4px, 0, 0);
    }
  }
</style>
