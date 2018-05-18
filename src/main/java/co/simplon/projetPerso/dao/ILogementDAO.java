package co.simplon.projetPerso.dao;

import java.util.List;

import co.simplon.projetPerso.model.Logement;

public interface ILogementDAO {
	
	public Logement insertLogement(Logement logement) throws Exception;

	public Logement getLogement(Long id_logement) throws Exception;

	public List<Logement> getListLogements() throws Exception;
	
	public Logement updateLogement(Logement logement) throws Exception;
	
	public void deleteLogement(Long id_logement) throws Exception;

}
