package co.simplon.projetPerso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import co.simplon.projetPerso.model.Agent;

/**
 * JDBD implementation of the Actor DAO interface.
 */
// @Component
@Repository
public class JdbcTemplateAgentDAO implements IAgentDAO {

	private JdbcTemplate jdbcTemplate;

	/**
	 * Constructor
	 * @param jdbcTemplate
	 *  : the JDBCTemplace connected to the Database (thanks to Spring)
	 */
	@Autowired
	public JdbcTemplateAgentDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Get the list of all the agents.
	 * @return : the list of all the agents.
	 */
	@Override
	public List<Agent> getListAgents() throws Exception {
		String sql = "SELECT * FROM agent ";

		List<Agent> aListOfAgents = jdbcTemplate.query(sql, new ResultSetExtractor<List<Agent>>() {
			@Override
			public List<Agent> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Agent> list = new ArrayList<Agent>();
				while (rs.next()) {
					Agent agent = getAgentFromResultSet(rs);
					list.add(agent);
				}
				return list;
			}

		});

		return aListOfAgents;
	}

	/**
	 * Get a specific agent based on ID
	 * 
	 * @param id
	 *            : the id of agent.
	 * @return Agent : the agent object (or null)
	 */
	@Override
	public Agent getAgent(Long id) {

		try {
			String sql = "SELECT * FROM agent WHERE id_agent = ?";
			Agent agent = (Agent) jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<Agent>() {
				@Override
				public Agent mapRow(ResultSet rs, int rowNum) throws SQLException {
					Agent agent = getAgentFromResultSet(rs);
					return agent;
				}
			});
			return agent;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * create a new agent.
	 * 
	 * @param agent : the agent information.
	 */
	@Override
	public Agent insertAgent(Agent agent) throws Exception {
		String sql = "INSERT INTO agent (id_agent, nom, prenom, bureau_affectation, mail, telephone) VALUES (?,?,?,?,?,?)";
//		Timestamp updateTime = new Timestamp(System.currentTimeMillis());
		long newId;

		agent.setId_agent(new Long(0));
		
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				int i = 0;
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setLong(++i, agent.getId_agent());
				ps.setString(++i, agent.getNom());
				ps.setString(++i, agent.getPrenom());
				ps.setString(++i, agent.getBureau_affectation());
				ps.setString(++i, agent.getMail());
				ps.setString(++i, agent.getTelephone());
				return ps;
			}
		};

		// The newly generated key will be saved in this object
		final KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(psc, holder);

		newId = holder.getKey().longValue();
		agent.setId_agent(newId);
		return agent;
	}

	/**
	 * Update an existing agent.
	 * 
	 * @param agent
	 *            : the agent information.
	 */
	@Override
	public Agent updateAgent(Agent agent) throws Exception {

		String sql = "UPDATE agent SET nom = ?, prenom = ?, bureau_affectation = ?, mail = ?, telephone = ? WHERE id_agent = ?";
//		Timestamp updateTime = new Timestamp(System.currentTimeMillis());

		jdbcTemplate.update(sql, new Object[] { agent.getNom(), agent.getPrenom(), agent.getBureau_affectation(), agent.getMail(), agent.getTelephone(), agent.getId_agent() });

		return agent;
	}

	/**
	 * Delete an existing actor.
	 * 
	 * @param id
	 *            : the id of actor.
	 */
	@Override
	public void deleteAgent(Long id_agent) throws Exception {
		this.jdbcTemplate.update("DELETE FROM agent WHERE id_agent = ?", new Object[] { id_agent });
	}

	/**
	 * Build an actor object with data from the ResultSet
	 * 
	 * @param rs
	 *            : the ResultSet to process.
	 * @return Agent : The new Agent object
	 */
	private Agent getAgentFromResultSet(ResultSet rs) throws SQLException {
		Agent agent = new Agent();
		agent.setId_agent(rs.getLong("id_agent"));
		agent.setNom(rs.getString("nom"));
		agent.setPrenom(rs.getString("prenom"));
		agent.setBureau_affectation(rs.getString("bureau_affectation"));
		agent.setMail(rs.getString("mail"));
		agent.setTelephone(rs.getString("telephone"));

		return agent;
	}

}
