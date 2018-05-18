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

import co.simplon.projetPerso.model.Logement;

/**
 * JDBD implementation of the Actor DAO interface.
 */
// @Component
@Repository
public class JdbcTemplateLogementDAO implements ILogementDAO {

	private JdbcTemplate jdbcTemplate;

	/**
	 * Constructor
	 * @param jdbcTemplate
	 *  : the JDBCTemplace connected to the Database (thanks to Spring)
	 */
	@Autowired
	public JdbcTemplateLogementDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * Get the list of all the agents.
	 * @return : the list of all the agents.
	 */
	@Override
	public List<Logement> getListLogements() throws Exception {
		String sql = "SELECT * FROM logement";

		List<Logement> aListOfLogements = jdbcTemplate.query(sql, new ResultSetExtractor<List<Logement>>() {
			@Override
			public List<Logement> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Logement> list = new ArrayList<Logement>();
				while (rs.next()) {
					Logement logement = getLogementFromResultSet(rs);
					list.add(logement);
				}
				return list;
			}

		});

		return aListOfLogements;
	}

	/**
	 * Get a specific agent based on ID
	 * 
	 * @param id
	 *            : the id of agent.
	 * @return Agent : the agent object (or null)
	 */
	@Override
	public Logement getLogement(Long id) {

		try {
			String sql = "SELECT * FROM logement WHERE id_logement = ?";
			Logement logement = (Logement) jdbcTemplate.queryForObject(sql, new Object[] { id }, new RowMapper<Logement>() {
				@Override
				public Logement mapRow(ResultSet rs, int rowNum) throws SQLException {
					Logement logement = getLogementFromResultSet(rs);
					return logement;
				}
			});
			return logement;
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
	public Logement insertLogement(Logement logement) throws Exception {
		String sql = "INSERT INTO logement (id_logement, numero, rue, complement, code_postal, ville, parking, toilettes, salle_de_bain, cuisine, nombre_lit, prix) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
//		Timestamp updateTime = new Timestamp(System.currentTimeMillis());
		long newId;

		logement.setId_logement(new Long(0));
		
		final PreparedStatementCreator psc = new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(final Connection connection) throws SQLException {
				int i = 0;
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setLong(++i, logement.getId_logement());
				ps.setInt(++i, logement.getNumero());
				ps.setString(++i, logement.getRue());
				ps.setString(++i, logement.getComplement());
				ps.setString(++i, logement.getCode_postal());
				ps.setString(++i, logement.getVille());
				ps.setBoolean(++i, logement.isParking());
				ps.setBoolean(++i, logement.isToilettes());
				ps.setBoolean(++i, logement.isSalle_de_bain());
				ps.setBoolean(++i, logement.isCuisine());
				ps.setInt(++i, logement.getNombre_lit());
				ps.setInt(++i, logement.getPrix());
				return ps;
			}
		};

		// The newly generated key will be saved in this object
		final KeyHolder holder = new GeneratedKeyHolder();

		jdbcTemplate.update(psc, holder);

		newId = holder.getKey().longValue();
		logement.setId_logement(newId);
		return logement;
	}

	/**
	 * Update an existing agent.
	 * 
	 * @param agent
	 *            : the agent information.
	 */
	@Override
	public Logement updateLogement(Logement logement) throws Exception {

		String sql = "UPDATE logement SET numero = ?, rue = ?, complement = ?, code_postal = ?, ville = ?, parking = ?, toilettes = ?, salle_de_bain = ?, cuisine = ?, nombre_lit = ?, prix = ? WHERE id_logement = ?";
//		Timestamp updateTime = new Timestamp(System.currentTimeMillis());

		jdbcTemplate.update(sql, new Object[] { logement.getNumero(), logement.getRue(), logement.getComplement(), logement.getCode_postal(), logement.getVille(), logement.isParking(), logement.isToilettes(),  logement.isSalle_de_bain(), logement.isCuisine(), logement.getNombre_lit(), logement.getId_logement(), logement.getPrix() });

		return logement;
	}

	/**
	 * Delete an existing actor.
	 * 
	 * @param id
	 *            : the id of actor.
	 */
	@Override
	public void deleteLogement(Long id_logement) throws Exception {
		this.jdbcTemplate.update("DELETE FROM logement WHERE id_logement = ?", new Object[] { id_logement });
	}

	/**
	 * Build an actor object with data from the ResultSet
	 * 
	 * @param rs
	 *            : the ResultSet to process.
	 * @return Agent : The new Agent object
	 */
	private Logement getLogementFromResultSet(ResultSet rs) throws SQLException {
		Logement logement = new Logement();
		logement.setId_logement(rs.getLong("id_logement"));
		logement.setNumero(rs.getInt("numero"));
		logement.setRue(rs.getString("rue"));
		logement.setCode_postal(rs.getString("code_postal"));
		logement.setVille(rs.getString("ville"));
		logement.setParking(rs.getBoolean(5));
		logement.setToilettes(rs.getBoolean(6));
		logement.setSalle_de_bain(rs.getBoolean(7));
		logement.setCuisine(rs.getBoolean(8));
		logement.setNombre_lit(rs.getInt("nombre_lit"));
		logement.setPrix(rs.getInt("prix"));

		return logement;
	}

}
