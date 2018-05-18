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
import co.simplon.projetPerso.service.AgentService;

@RestController
@CrossOrigin
@RequestMapping("/agent")
public class AgentController {

	@Autowired
	private AgentService agentService;

	/**
	 * @return "C" créer un agent
	 * @throws Exception
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<?> insertAgent(@RequestBody Agent agent) {
		Agent resultAgent = null;
		
		String nomAgent = agent.getNom();
		if ((nomAgent == null) || (nomAgent.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le nom de l'agent n'est pas défini!");
		String prenomAgent = agent.getPrenom();
		if ((prenomAgent == null) || (prenomAgent.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le prénom de l'agent n'est pas défini!");
		String bureau_affectationAgent = agent.getBureau_affectation();
		if ((bureau_affectationAgent == null) || (bureau_affectationAgent.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le bureau d'affectation de l'agent n'est pas défini!");
		String mailAgent = agent.getMail();
		if ((mailAgent == null) || (mailAgent.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le mail de l'agent n'est pas défini!");
		String telephoneAgent = agent.getTelephone();
		if ((telephoneAgent == null) || (telephoneAgent.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le téléphone de l'agent n'est pas défini!");
					
		try {
			agentService.insertAgent(agent);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(resultAgent);
	}
	
	/**
	 * @param id
	 * @return "R" afficher un agent selon son id
	 * @throws Exception
	 */
	@RequestMapping(value = "/detail/{id_agent}", method = RequestMethod.GET)
	public ResponseEntity<?> getAgent(@PathVariable Long id_agent) {
		Agent agent = null;
		try {
			agent = agentService.getAgent(id_agent);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
		if(agent == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		
		return ResponseEntity.status(HttpStatus.OK).body(agent);
	}

	/**
	 * @param id
	 * @return "R" afficher la liste des agents enregistres
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<?> getListAgents() {
		List<Agent> listAgents = null;
		try {
			listAgents = agentService.getListAgents();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listAgents);
	}

	/**
	 * @param id
	 * @return "U" modifier un agent
	 * @throws Exception
	 */
	@RequestMapping(value = "/update/{id_agent}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAgent(@RequestBody Agent agent,@PathVariable Long id_agent) throws Exception {
		Agent result = null;
		
//		Long id_agent = agent.getId_agent();
//		if (id_agent == 0)
//			return id_agent.getLong(id_agent);
		String nomAgent = agent.getNom();
		if ((nomAgent == null) || (nomAgent.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le nom de l'agent n'est pas défini!");
		String prenomAgent = agent.getPrenom();
		if ((prenomAgent == null) || (prenomAgent.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le prénom de l'agent n'est pas défini!");
		String bureau_affectationAgent = agent.getBureau_affectation();
		if ((bureau_affectationAgent == null) || (bureau_affectationAgent.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le bureau d'affectation de l'agent n'est pas défini!");
		String mailAgent = agent.getMail();
		if ((mailAgent == null) || (mailAgent.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le mail de l'agent n'est pas défini!");
		String telephoneAgent = agent.getTelephone();
		if ((telephoneAgent == null) || (telephoneAgent.isEmpty()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("le téléphone de l'agent n'est pas défini!");
		
		try {
			result = agentService.updateAgent(id_agent, agent);
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
	@RequestMapping(value = "/delete/{id_agent}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAgent(@PathVariable Long id_agent) {
		try {
			agentService.deleteAgent(id_agent);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
}
