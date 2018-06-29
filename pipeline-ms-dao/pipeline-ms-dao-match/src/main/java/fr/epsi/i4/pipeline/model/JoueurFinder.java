package fr.epsi.i4.pipeline.model;

import fr.epsi.i4.pipeline.model.bdd.equipe.Joueur;

public class JoueurFinder {

	public String nom;

	public String prenom;

	public static JoueurFinder fromJoueur(Joueur joueur) {
		JoueurFinder finder = new JoueurFinder();
		finder.nom = joueur.nom;
		finder.prenom = joueur.prenom;
		return finder;
	}
}
