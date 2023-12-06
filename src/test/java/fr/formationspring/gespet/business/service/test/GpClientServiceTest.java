package fr.formationspring.gespet.business.service.test;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import fr.formationspring.gespet.business.config.GespetBusinessConfig;
import fr.formationspring.gespet.business.dto.GpClientBasicDTO;
import fr.formationspring.gespet.business.dto.GpClientFullDTO;
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

import fr.formationspring.gespet.business.exception.GespetBusinessException;
import fr.formationspring.gespet.business.service.IGpClientService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GespetBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class GpClientServiceTest {
	
	@Resource(name = GespetConstantes.GespetConstantesService.GP_CLIENT_SERVICE_KEY)
	private IGpClientService clientService;

	private Integer clientIdForAllTest = null;
	private Integer createClientId = null;
	
	@Test
	public void createClientId() throws GespetBusinessException {
		GpClientFullDTO client = new GpClientFullDTO();
		String sexeStr = "M";
		client.setFirstName("Loti");
		client.setLastName("ABDOU ZOYIM");
		client.setPhoneNumber("699246415");
		client.setAddress("RUE 65 Boulevard la liberte");
		client.setOccupation("Informaticien");
		client.setAge(25);
		client.setSexe(sexeStr.charAt(0)); 
		client.setCreationDate(new Date());
		client = clientService.create(client);
		this.createClientId = client.getId();
	}

	@Test
	public void testFindAllClientWithSuccess() {
		// Given
		// When
		List<GpClientBasicDTO> emps = this.clientService.findAll();
		// Then
		Assert.assertTrue(emps.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() throws GespetBusinessException {
		// Given
		Integer clientId = this.clientIdForAllTest;
		// When
		GpClientFullDTO client = this.clientService.findById(clientId);
		// Then
		Assert.assertTrue(client.getId() == clientId);
	}

	@Test
	public void testUpdateClient() throws AccessDeniedException, GespetBusinessException {
		// Given
		GpClientFullDTO client = this.clientService.findById(this.clientIdForAllTest);
		client.setPhoneNumber("672750917");
		// When
		this.clientService.update(client);
		GpClientFullDTO clientUpdate = this.clientService.findById(this.clientIdForAllTest);
		// Then

		Assert.assertTrue(clientUpdate.getPhoneNumber() == "672750917");
	}

	@Test
	public void testDelete() throws AccessDeniedException, Exception {
		// Given
		Integer clientId = this.clientIdForAllTest;
		this.clientIdForAllTest = null;
		// Whens
		this.clientService.deleteById(clientId);
		GpClientFullDTO client = this.clientService.findById(clientId);

		// Then
		Assert.assertNull(client);

	}

	@Before
	public void prepareAllEntityBefore() throws GespetBusinessException {
		GpClientFullDTO client = new GpClientFullDTO();
		String sexeStr = "M";
		client.setFirstName("Loti");
		client.setLastName("ABDOU ZOYIM");
		client.setPhoneNumber("699246415");
		client.setAddress("RUE 65 Boulevard la liberte");
		client.setOccupation("Informaticien");
		client.setAge(25);
		client.setSexe(sexeStr.charAt(0)); 
		client.setCreationDate(new Date());
		client = clientService.create(client);
		this.clientIdForAllTest = client.getId();
	}

	@After
	public void deleteAllEntityAfter() throws AccessDeniedException, GespetBusinessException {
		if (this.clientIdForAllTest != null) {
			this.clientService.deleteById(this.clientIdForAllTest);
		}

		if (!Objects.isNull(this.createClientId)) {
			this.clientService.deleteById(this.createClientId);
		}
	}

}
