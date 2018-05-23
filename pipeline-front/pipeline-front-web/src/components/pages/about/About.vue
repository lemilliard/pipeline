<template>
  <v-jumbotron color="grey lighten-2">
    <v-container fill-height>
      <v-layout align-center>
        <v-flex>
          <h3 class="display-3">Welcome to the Pipeline site</h3>
          <div class="title mb-3"><span class="subheading"><p>Chaque 5 ans, l’association de tennis professionnel (ATP pour les hommes et
            WTA pour les femmes), lance un appel d’offre pour la réalisation d’une application de
            suivi des scores des rencontres de tennis organisées sur le circuit professionnel
            appelée ATP LIVE.</p>
            <p>Cette application vise à pouvoir consulter les scores de rencontres en direct
              pour les organisateurs, les juges-arbitres et le public.</p>
            <p>Les principaux fournisseurs de données sont les arbitres de chaise (arbitre
            principal de la rencontre), qui utilisent l’outil pour le comptage des points, totalement en
            temps réel. C’est ce comptage qui apparaitra comme officiel et certifié par les
              instances dirigeantes de l’ATP/WTA.</p>
            <p>Ces données, une fois saisies, sont consultables à tout moment par
            l’organisation, la presse et le public via le site Internet de l’ATP dans un espace
              réservé.</p>
            <p>Pour l’organisation, il est impératif d’être tenu au courant par messagerie (voir
            par SMS) à chaque moment clés du match. (Début de la rencontre / set gagné / fin de
              la rencontre).</p>
            <p>Pour la presse et le public, les besoins concernent également la mise en place
            d’alertes temps réel sur des rencontres particulières (mêmes besoins que
            l’organisation) mais il est souhaitable également que les comptes-rendus de match et
            les statistiques soient (en plus d’être consultables à tout moment) leur soient envoyés
            à chaque fin de journée par mail. Selon l’ATP, cela représente un flux d’environ 3000
              mails par jour.</p></span></div>
          <v-btn large color="primary" class="mx-0">See more</v-btn>
        </v-flex>
      </v-layout>
    </v-container>
  </v-jumbotron>
</template>

<script>
export default {
  name: 'About',
  data() {
    return {
      msg: 'About',
      items: [{
        href: 'Home',
        router: true,
        text: 'Home',
        icon: 'home',
        disabled: false,
      }, {
        href: 'Settings',
        router: true,
        text: 'Settings',
        icon: 'settings',
        disabled: false,
      }, {
        href: 'About',
        router: true,
        text: 'About',
        icon: 'domain',
        disabled: true,
      }],
    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
