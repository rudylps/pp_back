package co.simplon.crudTest;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.simplon.projetPerso.ProjetPersoApplication;
import co.simplon.projetPerso.dao.IAgentDAO;
import co.simplon.projetPerso.model.Agent;
import co.simplon.projetPerso.service.AgentService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjetPersoApplication.class)

public class AgentTest {

	static AgentService agentService;

	@Autowired
	IAgentDAO agentDAO;

	@BeforeClass
	public static void initAgent() throws Exception {
		agentService = new AgentService();
	}

	@Test
	public void testFindOneOk() {
		Agent agent = new Agent();
		try {
			agent = agentDAO.getAgent((long) 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("Pellard", agent.getNom());
	}

	@Test
	public void testFindOneKo() {
		Agent agent = new Agent();

		try {
			agent = agentDAO.getAgent((long) 30052122);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertNull(agent);
	}

	@Test
	public void testFindOneBisOk() {
		Agent agent = new Agent();

		try {
			agent = agentDAO.getAgent((long) 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertThat(agent, instanceOf(Agent.class));
	}

	@Test
	public void testInsert() {
		Agent agent = new Agent();
		Agent agentNew = null;

		try {
			agent = createMock("Jean", "saisrien", "Montreuil", "jean.saisrien@simplon.co", "0192837465");
			agentNew = agentDAO.insertAgent(agent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(agentNew != null);
	}

	@Test
	public void testUpdate() {
		Agent agent = new Agent();
		Agent agentNew = null;

		agentNew = null;
		agent.setId_agent((long) 1);
		agent.setNom("Jack");
		agent.setPrenom("Ouille");
		agent.setBureau_affectation("Bordeaux");
		agent.setMail("jack.ouille@simplon.co");
		agent.setTelephone("0192837465");
		try {
			agentNew = agentDAO.updateAgent(agent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertTrue(agentNew != null);
		assertEquals("Jack", agentNew.getNom());

	}
	
	@Test
	public void testDelete() {
		Agent agent = new Agent();
		Long id = (long) 3;

		try {
			agentDAO.deleteAgent(id);
			agent = agentDAO.getAgent(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(agent == null);
	}

	private Agent createMock(String nom, String prenom, String bureau_affectation, String mail, String telephone) {
		Agent mock = new Agent();
		mock.setId_agent(new Long(0));
		mock.setNom(nom);
		mock.setPrenom(prenom);
		mock.setBureau_affectation(bureau_affectation);
		mock.setMail(mail);
		mock.setTelephone(telephone);
		return mock;
	}

}
