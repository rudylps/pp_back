package co.simplon.projetPerso.dao;

import java.util.List;

import co.simplon.projetPerso.model.Agent;

public interface IAgentDAO {
	
	public Agent insertAgent(Agent agent) throws Exception;

	public Agent getAgent(Long id_agent) throws Exception;

	public List<Agent> getListAgents() throws Exception;
	
	public Agent updateAgent(Agent agent) throws Exception;
	
	public void deleteAgent(Long id_agent) throws Exception;

}
