/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.i4.pipeline.excel;

import java.util.Date;
import java.util.List;

/**
 *
 * @author kbouzan
 */
public class OrganisationImport {
     private List<Utilisateur> joueurs;
     private Utilisateur arbitre;
     private String court;
     private Date date;

    public List<Utilisateur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(List<Utilisateur> joueurs) {
        this.joueurs = joueurs;
    }

    public Utilisateur getArbitre() {
        return arbitre;
    }

    public void setArbitre(Utilisateur arbitre) {
        this.arbitre = arbitre;
    }

    public String getCourt() {
        return court;
    }

    public void setCourt(String court) {
        this.court = court;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date heure) {
        this.date = heure;
    }

     
     
}
