package co.simplon.projetPerso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.projetPerso.dao.ILogementDAO;
import co.simplon.projetPerso.model.Logement;

@Service
public class LogementService {
	
	@Autowired
//	@Qualifier("jdbcTemplateActorDAO")
	private ILogementDAO dao;
	
	// Inserts row into table 
		public Logement insertLogement(Logement logement) throws Exception {
			return dao.insertLogement(logement);
		}
	// Retrieves one row from table based on given id
		public Logement getLogement(Long id_logement) throws Exception {
			return dao.getLogement(id_logement);
		}
	// Retrieve all rows from table and populate list with objects
		public List<Logement> getListLogements() throws Exception {
			return dao.getListLogements();
		}
		// Updates row in table
		public Logement updateLogement(Long id_logement, Logement logement) throws Exception {
			return dao.updateLogement(logement);
		}
	// Removes row from table
		public void deleteLogement(Long id_logement) throws Exception {
			dao.deleteLogement(id_logement);
		}

}
