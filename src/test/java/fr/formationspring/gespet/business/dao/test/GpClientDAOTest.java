package fr.formationspring.gespet.business.dao.test;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import fr.formationspring.gespet.business.config.GespetBusinessConfig;
import fr.formationspring.gespet.business.dao.IGpClientRepository;
import fr.formationspring.gespet.business.utils.GespetConstantes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import fr.formationspring.gespet.business.entity.GpClient;
import fr.formationspring.gespet.business.exception.GespetBusinessException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GespetBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GpClientDAOTest {
	
	@Resource(name = GespetConstantes.GespetConstantesDAO.GP_CLIENT_DAO)
	private IGpClientRepository gpClientRepository;
	
	
	private Integer clientIdForAllTest = null;
	private Integer createClientId = null;
	
	@Test
	public void createClient() {
		GpClient client = new GpClient();
		String sexeStr = "M";
		
		client.setFirstName("Loti");
		client.setLastName("ABDOU ZOYIM");
		client.setPhoneNumber("699246415");
		client.setAddress("RUE 65 Boulevard la liberte");
		client.setOccupation("Informaticien");
		client.setAge(25);
		client.setSexe(sexeStr.charAt(0));
		client.setCreationDate(new Date());
		
		client = gpClientRepository.save(client);
		
		this.createClientId = client.getId();
	}
	
	@Test
	public void testFindAllClientWithSuccess() {
		// Given
		// When
		List<GpClient> clients = this.gpClientRepository.findAll();
		// Then
		Assert.assertTrue(clients.size() > 0);
	}
	
	@Test
	public void testFindByIdWithSuccess() throws GespetBusinessException {
		// Given
		Integer clientId = this.clientIdForAllTest;
		// When
		GpClient client = this.gpClientRepository.findById(clientId).orElse(null);
		// Then
		Assert.assertTrue(client.getId() == clientId);
	}
	
	@Test
	public void testUpdateClient() throws AccessDeniedException, GespetBusinessException {
		// Given
		GpClient client = this.gpClientRepository.findById(this.clientIdForAllTest).orElse(null);
		client.setPhoneNumber("672750917");
		// When
		this.gpClientRepository.saveAndFlush(client);
		GpClient clientUpdate = this.gpClientRepository.findById(this.clientIdForAllTest).orElse(null);
		// Then

		Assert.assertTrue(clientUpdate.getPhoneNumber() == "672750917");
	}
	
	@Test
	public void testDelete() throws AccessDeniedException, Exception {
		// Given
		Integer clientId = this.clientIdForAllTest;
		this.clientIdForAllTest = null;
		// Whens
		this.gpClientRepository.deleteById(clientId);
		GpClient client = this.gpClientRepository.findById(clientId).orElse(null);

		// Then
		Assert.assertNull(client);

	}
	
	@Before
	public void prepareAllEntityBefore() throws GespetBusinessException {
		GpClient client = new GpClient();
		String sexeStr = "M";
		client.setFirstName("Loti");
		client.setLastName("ABDOU ZOYIM");
		client.setPhoneNumber("699246415");
		client.setAddress("RUE 65 Boulevard la liberte");
		client.setOccupation("Informaticien");
		client.setAge(25);
		client.setSexe(sexeStr.charAt(0)); 
		client.setCreationDate(new Date());
		client = gpClientRepository.save(client);
		this.clientIdForAllTest = client.getId();
	} 
	
	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, GespetBusinessException {
		if (this.clientIdForAllTest != null) {
			this.gpClientRepository.deleteById(this.clientIdForAllTest);
		}

		if (!Objects.isNull(this.createClientId)) { 
			this.gpClientRepository.deleteById(this.createClientId);
		}
	}

}
