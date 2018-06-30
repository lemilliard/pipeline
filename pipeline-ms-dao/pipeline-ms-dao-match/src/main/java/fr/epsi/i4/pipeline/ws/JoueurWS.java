package fr.epsi.i4.pipeline.ws;

import com.google.gson.Gson;
import com.thomaskint.minidao.MiniDAO;
import com.thomaskint.minidao.enumeration.MDConditionLink;
import com.thomaskint.minidao.enumeration.MDConditionOperator;
import com.thomaskint.minidao.exception.MDException;
import com.thomaskint.minidao.querybuilder.MDCondition;
import com.thomaskint.minidao.querybuilder.MDSelectBuilder;
import fr.epsi.i4.pipeline.model.JoueurFinder;
import fr.epsi.i4.pipeline.model.bdd.equipe.Joueur;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.util.List;

/**
 * @author Thomas Kint
 */
@RestController
public class JoueurWS extends WebService {

	@GetMapping("/joueur")
	public List<Joueur> getJoueurs() {
		return getEntities(Joueur.class);
	}

	@GetMapping("/joueur/count")
	public int getJoueursCount() throws MDException {
		return getMiniDAO().read().countEntities(Joueur.class);
	}

	@GetMapping("/joueur/{offset}/{limit}")
	public List<Joueur> getJoueursBounds(@PathVariable("offset") int offset, @PathVariable("limit") int limit) throws Exception {
		MDSelectBuilder selectBuilder = new MDSelectBuilder();
		selectBuilder.select().from(Joueur.class);
		String query = selectBuilder.build();

		ResultSet resultSet = getMiniDAO().executeQuery(query);

		return getMiniDAO().mapResultSetToEntities(resultSet, Joueur.class, offset, limit);
	}

	@GetMapping("/joueur/{id}")
	public Joueur getJoueurById(@PathVariable("id") int id) {
		return getEntityById(Joueur.class, id);
	}

	@PostMapping("/joueur/find")
	public Joueur getJoueurByFinder(@RequestBody JoueurFinder finder) {
		MDSelectBuilder selectBuilder = new MDSelectBuilder().from(Joueur.class);

		MDCondition conditionNom = new MDCondition("nom", MDConditionOperator.LIKE, finder.nom);
		MDCondition condition = new MDCondition("prenom", MDConditionOperator.LIKE, finder.prenom, MDConditionLink.AND, conditionNom);
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

	@PostMapping("/joueur")
	public boolean createJoueur(@RequestBody Joueur joueur) {
		return createEntity(joueur);
	}

	@PostMapping("/joueur/import")
	public boolean importJoueurs(@RequestParam("file") MultipartFile file) throws Exception {
		InputStream inputStream = file.getInputStream();
		InputStreamReader streamReader = new InputStreamReader(inputStream);
		BufferedReader reader = new BufferedReader(streamReader);

		Gson gson = new Gson();
		String line;
		Joueur nouveauJoueur;
		Joueur joueur;
		boolean imported = true;
		while ((line = reader.readLine()) != null) {
			nouveauJoueur = Joueur.fromCSVLine(line);
			joueur = getJoueurByFinder(JoueurFinder.fromJoueur(nouveauJoueur));

			if (joueur == null) {
				imported &= createEntity(nouveauJoueur);
				System.out.println("Import " + gson.toJson(nouveauJoueur));
			} else {
				if (!nouveauJoueur.equals(joueur)) {
					imported &= updateEntity(nouveauJoueur);
					System.out.println("Update " + gson.toJson(nouveauJoueur));
				} else {
					System.out.println("Ignore " + gson.toJson(nouveauJoueur));
				}
			}
		}
		return imported;
	}

	@PutMapping("/joueur")
	public boolean updateJoueur(@RequestBody Joueur joueur) {
		return updateEntity(joueur);
	}

	@DeleteMapping("/joueur")
	public boolean deleteJoueur(@RequestBody Joueur joueur) {
		return deleteEntity(joueur);
	}

	@DeleteMapping("/joueur/{id}")
	public boolean deleteJoueurById(@PathVariable("id") int id) {
		return deleteEntityById(Joueur.class, id);
	}
}
