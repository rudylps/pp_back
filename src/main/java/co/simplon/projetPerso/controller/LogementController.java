package co.simplon.projetPerso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.simplon.projetPerso.model.Agent;
import co.simplon.projetPerso.model.Logement;
import co.simplon.projetPerso.service.LogementService;

@RestController
@CrossOrigin // ne pas oublier faire fonctionner qd pas les mm ports en back front
@RequestMapping("/logement")
public class LogementController {

	@Autowired
	private LogementService logementService;

	/**
	 * @return "C" créer un agent
	 * @throws Exception
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> insertLogement(@RequestBody Logement logement) {
		Logement resultLogement = null;
		int i = 0;
		
		int numeroLogement = logement.getNumero();
//		if ((numeroLogement == 0)) 
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le numéro de rue du logement n'est pas défini!");

		String rueLogement = logement.getRue();
		if ((rueLogement == null) || (rueLogement.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la rue n'est pas définie!");

		String complementLogement = logement.getComplement();
//		if ((complementLogement == null) || (complementLogement.isEmpty()))
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le complément d'adresse du logement n'est pas défini!");

		String code_postalLogement = logement.getCode_postal();
//		if ((code_postalLogement == 0) || (code_postalLogement = ))
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le code postal n'est pas défini!");

		String villeLogement = logement.getVille();
		if ((villeLogement == null) || (villeLogement.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la ville n'est pas définie!");
		
		boolean parkingLogement = logement.isParking();
//		if ((parkingLogement.isEmpty()))
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");

		boolean toilettesLogement = logement.isToilettes();
		
		boolean salle_de_bainLogement = logement.isSalle_de_bain();
		
		boolean cuisineLogement = logement.isCuisine();
					
		try {
			logementService.insertLogement(logement);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resultLogement);
	}
	
	/**
	 * @param id
	 * @return "R" afficher un agent selon son id
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail/{id_logement}", method = RequestMethod.GET)
	public ResponseEntity<?> getLogement(@PathVariable Long id_logement) {
		Logement logement = null;
		try {
			logement = logementService.getLogement(id_logement);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if(logement == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
		return ResponseEntity.status(HttpStatus.OK).body(logement);
	}

	/**
	 * @param id
	 * @return "R" afficher la liste des agents enregistres
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<?> getListLogements() {
		List<Logement> listLogements = null;
		try {
			listLogements = logementService.getListLogements();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listLogements);
	}

	/**
	 * @param id
	 * @return "U" modifier un agent
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/{id_logement}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateLogement(@RequestBody Logement logement,@PathVariable Long id_logement) throws Exception {
		Logement result = null;
		
//		Long id_agent = agent.getId_agent();
//		if (id_agent == 0)
//			return id_agent.getLong(id_agent);
		int numeroLogement = logement.getNumero();
//		if ((numeroLogement == 0)) 
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le numéro de rue du logement n'est pas défini!");

		String rueLogement = logement.getRue();
		if ((rueLogement == null) || (rueLogement.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la rue n'est pas définie!");

		String complementLogement = logement.getComplement();
//		if ((complementLogement == null) || (complementLogement.isEmpty()))
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le complément d'adresse du logement n'est pas défini!");

		String code_postalLogement = logement.getCode_postal();
//		if ((code_postalLogement == 0) || (code_postalLogement = ))
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le code postal n'est pas défini!");

		String villeLogement = logement.getVille();
		if ((villeLogement == null) || (villeLogement.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("la ville n'est pas définie!");
		
		boolean parkingLogement = logement.isParking();
//		if ((parkingLogement.isEmpty()))
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");

		boolean toilettesLogement = logement.isToilettes();
		
		boolean salle_de_bainLogement = logement.isSalle_de_bain();
		
		boolean cuisineLogement = logement.isCuisine();
		
		int prixLogement = logement.getPrix();
		
		try {
			result = logementService.updateLogement(id_logement, logement);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}

	/**
	 * @param id
	 * @return "D" supprimer un agent
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete/{id_logement}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteLogement(@PathVariable Long id_logement) {
		try {
			logementService.deleteLogement(id_logement);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
