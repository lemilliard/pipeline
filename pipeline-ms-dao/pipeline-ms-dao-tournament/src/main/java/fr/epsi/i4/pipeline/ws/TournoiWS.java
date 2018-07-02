package fr.epsi.i4.pipeline.ws;

import com.thomaskint.minidao.MiniDAO;
import com.thomaskint.minidao.enumeration.MDConditionLink;
import com.thomaskint.minidao.enumeration.MDConditionOperator;
import com.thomaskint.minidao.exception.MDException;
import com.thomaskint.minidao.querybuilder.MDCondition;
import com.thomaskint.minidao.querybuilder.MDSelectBuilder;
import fr.epsi.i4.pipeline.excel.ExcelReader;
import fr.epsi.i4.pipeline.excel.ImportTournoi;
import fr.epsi.i4.pipeline.excel.OrganisationImport;
import fr.epsi.i4.pipeline.excel.Utilisateur;
import fr.epsi.i4.pipeline.model.bdd.court.Court;
import fr.epsi.i4.pipeline.model.bdd.equipe.EquiJou;
import fr.epsi.i4.pipeline.model.bdd.equipe.Equipe;
import fr.epsi.i4.pipeline.model.bdd.equipe.Joueur;
import fr.epsi.i4.pipeline.model.bdd.rencontre.Rencontre;
import fr.epsi.i4.pipeline.model.bdd.tournoi.Tournoi;
import fr.epsi.i4.pipeline.model.bdd.tournoi.Organisation;
import fr.epsi.i4.pipeline.model.bdd.user.User;
import fr.epsi.i4.pipeline.ws.WebService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Thomas Kint
 */
@RestController
public class TournoiWS extends WebService {

	@GetMapping("/tournoi")
	public List<Tournoi> getTournois() {
		return getEntities(Tournoi.class);
	}

	@GetMapping("/tournoi/{id}")
	public Tournoi getTournoiById(@PathVariable("id") int id) {
		return getEntityById(Tournoi.class, id);
	}

	@GetMapping("/tournoi/complexe/{id}")
	public List<Tournoi> getTournoisByComplexeId(@PathVariable("id") int id) {
		List<Tournoi> tournois = null;
		try {
			MDCondition condition = new MDCondition(Tournoi.idComplexeFieldName, MDConditionOperator.EQUAL, id);
			tournois = getMiniDAO().read().getEntities(Tournoi.class, condition);
		} catch (MDException e) {
			e.printStackTrace();
		}
		return tournois;
	}

	@PostMapping("/tournoi")
	public boolean createTournoi(@RequestBody Tournoi tournoi) {
		return createEntity(tournoi);
	}

	@PutMapping("/tournoi")
	public boolean updateTournoi(@RequestBody Tournoi tournoi) {
		return updateEntity(tournoi);
	}
        
        @PostMapping("/tournoi/import")
        public Integer importTournament(@RequestParam("file") MultipartFile multipartFile,
            RedirectAttributes redirectAttributes) throws IOException, InvalidFormatException, MDException, SQLException{
            File file = new File(multipartFile.getOriginalFilename());
            file.createNewFile(); 
            FileOutputStream fos = new FileOutputStream(file); 
            fos.write(multipartFile.getBytes());
            fos.close();
            Integer cpt = 0;
            ImportTournoi importTournoi = ExcelReader.readExcel(file);
            Tournoi tournoi = checkTournoi(importTournoi.getTournoi());
            if(tournoi != null){
                boolean check;
                for (OrganisationImport organisation : importTournoi.getOrganisations()){
                    check = true;

                    User arbitre = checkArbitre(organisation.getArbitre());
                    if(arbitre == null){
                        check = false;
                    }
                    List<Joueur> joueurs = new ArrayList<>();
                    for (Utilisateur utilisateur : organisation.getJoueurs()){
                        Joueur joueur = checkJoueur(utilisateur);
                        if(joueur != null){
                            joueurs.add(joueur);
                        }
                    }
                    List<Equipe> equipes = checkEquipe(joueurs);
                    if (equipes.isEmpty()){
                        check = false;
                    }
                    Court court = checkCourt(organisation.getCourt());
                    if (court == null){
                        check = false;
                    }
                    if (check){
                        Rencontre rencontre = new Rencontre();
                        rencontre.idArbitre = arbitre.idUser;
                        rencontre.idEquipeUne = equipes.get(0).idEquipe;
                        rencontre.idEquipeDeux = equipes.get(1).idEquipe;
                        rencontre.dateDebut = new Timestamp(organisation.getDate().getTime());
                        rencontre.idCourt = court.idCourt;
                        rencontre.enPause = new BigDecimal(1);
                        createEntity(rencontre);
                        
                        ResultSet resultSet = getMiniDAO().executeQuery("SELECT MAX(ID_RENCONTRE) FROM RENCONTRE");
                        resultSet.next();
                        BigDecimal id = resultSet.getBigDecimal(1);
                        
                        rencontre.idRencontre = id;
                        
                        Organisation orga = new Organisation();
                        orga.idRencontre = rencontre.idRencontre;
                        orga.idTournoi = tournoi.idTournoi;
                        orga.idUser = new BigDecimal(1);
                        
                        createEntity(orga);
                        cpt ++;
                    }

                }
            }
            return cpt;
        }
        
        public Joueur checkJoueur(Utilisateur user){
            MDSelectBuilder selectBuilder = new MDSelectBuilder().from(Joueur.class);

            MDCondition conditionNom = new MDCondition("nom", MDConditionOperator.LIKE, user.getNom());
            MDCondition condition = new MDCondition("prenom", MDConditionOperator.LIKE, user.getPrenom(), MDConditionLink.AND, conditionNom);
            selectBuilder.where(condition);

            Joueur joueur = null;
            ResultSet resultSet;
            try {
                    String query = selectBuilder.build();
                    resultSet = getMiniDAO().executeQuery(query);

                    joueur = getMiniDAO().mapResultSetToEntity(resultSet, Joueur.class);
            } catch (MDException e) {
                    e.printStackTrace();
            }
            return joueur;
        }
        
        public User checkArbitre(Utilisateur user){
            MDSelectBuilder selectBuilder = new MDSelectBuilder().from(User.class);

            MDCondition conditionNom = new MDCondition("nom", MDConditionOperator.LIKE, user.getNom());
            MDCondition condition = new MDCondition("prenom", MDConditionOperator.LIKE, user.getPrenom(), MDConditionLink.AND, conditionNom);
            selectBuilder.where(condition);

            User arbitre = null;
            ResultSet resultSet;
            try {
                    String query = selectBuilder.build();
                    resultSet = getMiniDAO().executeQuery(query);

                    arbitre = getMiniDAO().mapResultSetToEntity(resultSet, User.class);
            } catch (MDException e) {
                    e.printStackTrace();
            }
            return arbitre;
        }
        
        public Court checkCourt(String nom){
            MDSelectBuilder selectBuilder = new MDSelectBuilder().from(Court.class);

            MDCondition conditionNom = new MDCondition("nom", MDConditionOperator.LIKE, nom);
            selectBuilder.where(conditionNom);

            Court court = null;
            ResultSet resultSet;
            try {
                    String query = selectBuilder.build();
                    resultSet = getMiniDAO().executeQuery(query);

                    court = getMiniDAO().mapResultSetToEntity(resultSet, Court.class);
            } catch (MDException e) {
                    e.printStackTrace();
            }
            return court;
        }
        
        public Tournoi checkTournoi(Tournoi tournoiParam){
            MDSelectBuilder selectBuilder = new MDSelectBuilder().from(Tournoi.class);

            MDCondition conditionNom = new MDCondition("nom", MDConditionOperator.LIKE, tournoiParam.nom);
            selectBuilder.where(conditionNom);

            Tournoi tournoi = null;
            ResultSet resultSet;
            try {
                    String query = selectBuilder.build();
                    resultSet = getMiniDAO().executeQuery(query);

                    tournoi = getMiniDAO().mapResultSetToEntity(resultSet, Tournoi.class);
            } catch (MDException e) {
                    e.printStackTrace();
            }
            if(tournoi == null){
                try {
                    createEntity(tournoiParam);
                    ResultSet result = getMiniDAO().executeQuery("SELECT MAX(ID_TOURNOI) FROM TOURNOI");
                    result.next();
                    BigDecimal id = result.getBigDecimal(1);
                    tournoiParam.idTournoi = id;
                    tournoi = tournoiParam;
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            return tournoi;
        }
        
        public List<Equipe> checkEquipe(List<Joueur> joueurs) throws MDException{
            List<BigDecimal> idEquipe = new ArrayList<BigDecimal>();
            for (Joueur joueur : joueurs){
                try {
                    EquiJou equijou;
                    MDCondition condition = new MDCondition(EquiJou.idJoueurFieldName, MDConditionOperator.EQUAL, joueur.idJoueur);
                    equijou = getMiniDAO().read().getEntityByCondition(EquiJou.class, condition);
                    if (equijou != null){
                        idEquipe.add(equijou.idEquipe);
                    }
                } catch (MDException ex) {
                    Logger.getLogger(TournoiWS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            List<Equipe> equipes = new ArrayList<>();
            Equipe equipe1 = new Equipe();
            Equipe equipe2 = new Equipe();
            if (idEquipe.size() == 4){
                if((idEquipe.get(0).intValue() == idEquipe.get(1).intValue()) && (idEquipe.get(2).intValue() == idEquipe.get(3).intValue()) && (idEquipe.get(1).intValue() != idEquipe.get(3).intValue())){
                    equipe1.idEquipe = idEquipe.get(0);
                    equipe2.idEquipe = idEquipe.get(2);
                    equipes.add(equipe1);
                    equipes.add(equipe2);
                }
            } else if (idEquipe.size() == 2){
                MDCondition conditionEquipeUne = new MDCondition(EquiJou.idEquipeFieldName, MDConditionOperator.EQUAL, idEquipe.get(0));
                MDCondition conditionEquipeDeux = new MDCondition(EquiJou.idEquipeFieldName, MDConditionOperator.EQUAL, idEquipe.get(1));
                int countEquipeUne = getMiniDAO().read().countEntities(EquiJou.class, conditionEquipeUne);
                int countEquipeDeux = getMiniDAO().read().countEntities(EquiJou.class, conditionEquipeDeux);
                if(idEquipe.get(0).intValue() != idEquipe.get(1).intValue() && countEquipeDeux == 1 && countEquipeUne == 1){
                    equipe1.idEquipe = idEquipe.get(0);
                    equipe2.idEquipe = idEquipe.get(1);
                    equipes.add(equipe1);
                    equipes.add(equipe2);
                }
            }
            return equipes;
        }
}
