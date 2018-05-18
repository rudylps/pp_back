package co.simplon.projetPerso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import co.simplon.projetPerso.dao.IAgentDAO;
import co.simplon.projetPerso.model.Agent;

@Service
public class AgentService {
	
	@Autowired
//	@Qualifier("jdbcTemplateActorDAO")
	private IAgentDAO dao;
	
	// Inserts row into table 
		public Agent insertAgent(Agent agent) throws Exception {
			return dao.insertAgent(agent);
		}
	// Retrieves one row from table based on given id
		public Agent getAgent(Long id_agent) throws Exception {
			return dao.getAgent(id_agent);
		}
	// Retrieve all rows from table and populate list with objects
		public List<Agent> getListAgents() throws Exception {
			return dao.getListAgents();
		}
		// Updates row in table
		public Agent updateAgent(Long id_agent, Agent agent) throws Exception {
			return dao.updateAgent(agent);
		}
	// Removes row from table
		public void deleteAgent(Long id_agent) throws Exception {
			dao.deleteAgent(id_agent);
		}

}
