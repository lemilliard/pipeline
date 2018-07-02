/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.epsi.i4.pipeline.excel;

import fr.epsi.i4.pipeline.model.bdd.tournoi.Tournoi;
import java.util.List;

/**
 *
 * @author kbouzan
 */
public class ImportTournoi {
    
    private Tournoi tournoi;
    
    private List<OrganisationImport> organisations;

    public ImportTournoi(Tournoi tournoi, List<OrganisationImport> organisations) {
        this.tournoi = tournoi;
        this.organisations = organisations;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public List<OrganisationImport> getOrganisations() {
        return organisations;
    }

    public void setOrganisations(List<OrganisationImport> organisations) {
        this.organisations = organisations;
    }
    
}
