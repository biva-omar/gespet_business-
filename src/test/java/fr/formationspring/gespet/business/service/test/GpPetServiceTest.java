package fr.formationspring.gespet.business.service.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import fr.formationspring.gespet.business.config.GespetBusinessConfig;
import fr.formationspring.gespet.business.dto.GpClientFullDTO;
import fr.formationspring.gespet.business.dto.GpPetBasicDTO;
import fr.formationspring.gespet.business.dto.GpPetFullDTO;
import fr.formationspring.gespet.business.utils.GespetConstantes;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import fr.formationspring.gespet.business.exception.GespetBusinessException;
import fr.formationspring.gespet.business.service.IGpClientService;
import fr.formationspring.gespet.business.service.IGpPetService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { GespetBusinessConfig.class })
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class GpPetServiceTest {
	
	@Resource(name = GespetConstantes.GespetConstantesService.GP_PET_SERVICE_KEY)
	private IGpPetService petService;

	@Resource(name = GespetConstantes.GespetConstantesService.GP_CLIENT_SERVICE_KEY)
	private IGpClientService clientService;

	private Integer petIdForAllTest = null;
	private Integer petIdCreateTest = null;

	private GpClientFullDTO clientTest;

	@Test
	public void testCreatePetWithSuccess() throws GespetBusinessException {
		// Given
		GpPetFullDTO pet = new GpPetFullDTO();

		pet.setName("DOG MECHANT");
		pet.setAge(3); 
		pet.setRace("BERGER ALLEMAND");
		pet.setType("CHIEN");
		pet.setCreationDate(new Date());
		
		Assert.assertNotNull(this.clientTest.getId());
		
		pet.setGpClient(this.clientTest);

		pet = this.petService.create(pet);
		Assert.assertNotNull(pet.getId());

		this.petIdCreateTest = pet.getId();

	}

	@Test
	public void testFindAllPetsWithSuccess() {
		// Given

		// When
		List<GpPetBasicDTO> pets = this.petService.findAll();

		// Then
		Assert.assertTrue(pets.size() > 0);
	}

	@Test
	public void testFindByIdWithSuccess() throws GespetBusinessException {
		// Given
		Integer petId = this.petIdForAllTest;

		// When
		GpPetFullDTO pet = this.petService.findById(petId);

		// Then
		Assert.assertNotNull(pet);
	}

	@Test
	public void testUpdatePetsWithSuccess() throws GespetBusinessException, AccessDeniedException {
		// Given
		Integer petId = this.petIdForAllTest;
		// When

		GpPetFullDTO gpPet = this.petService.findById(petId);
		gpPet.setName("DOG ALLEMANDE");

		petService.update(gpPet);
		assertThat(gpPet.getName()).isEqualTo("DOG ALLEMANDE");
	}

	@Test
	public void testDeletePetWithSuccess() throws AccessDeniedException, GespetBusinessException {
		// Given
		Integer petId = this.petIdForAllTest;
		petIdForAllTest = null;
		// When
		this.petService.deleteById(petId);

		// Then
		GpPetFullDTO pet = this.petService.findById(petId);
		Assert.assertNull(pet);
	}

	@Before
	public void prepareAllEntityBefore() throws GespetBusinessException {

		// Creation Client
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

		this.clientTest = new GpClientFullDTO();
		this.clientTest = client;
 
		// creation pet
		GpPetFullDTO pet = new GpPetFullDTO();
		pet.setName("DOG MECHANT");
		pet.setAge(3); 
		pet.setRace("BERGER ALLEMAND");
		pet.setType("CHIEN");
		pet.setCreationDate(new Date());
		pet.setGpClient(this.clientTest);
		pet = this.petService.create(pet);

		Assert.assertNotNull(pet.getId());

		this.petIdForAllTest = pet.getId();
	}

	@After
	public void deleteAllEntityAfter() throws GespetBusinessException, AccessDeniedException {
		if (!Objects.isNull(this.petIdCreateTest)) {
			this.petService.deleteById(this.petIdForAllTest);
		}
		if (!Objects.isNull(this.petIdCreateTest)) {
			this.petService.deleteById(petIdCreateTest);
		}

		if (!Objects.isNull(this.clientTest)) {
			this.clientService.deleteById(this.clientTest.getId());
		}
	}

}
