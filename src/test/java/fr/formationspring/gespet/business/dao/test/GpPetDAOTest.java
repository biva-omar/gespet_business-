package fr.formationspring.gespet.business.dao.test;

import java.nio.file.AccessDeniedException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import fr.formationspring.gespet.business.config.GespetBusinessConfig;
import fr.formationspring.gespet.business.dao.IGpClientRepository;
import fr.formationspring.gespet.business.dao.IGpPetRepository;
import fr.formationspring.gespet.business.entity.GpPet;
import fr.formationspring.gespet.business.utils.GespetConstantes;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
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
public class GpPetDAOTest {
	
	@Resource(name = GespetConstantes.GespetConstantesDAO.GP_PET_DAO)
	private IGpPetRepository gpPetRepository;
	
	@Resource(name = GespetConstantes.GespetConstantesDAO.GP_CLIENT_DAO)
	private IGpClientRepository gpClientRepository;
	
	
	private Integer petIdForAllTest = null;
	private Integer createPetId = null;
	
	private GpClient clientTest;
	
	@Test
	public void createPet() {
		
		GpPet pet = new GpPet();
		
		pet.setName("DOG DE CHASSE");
		pet.setAge(3); 
		pet.setRace("BERGER ALLEMAND");
		pet.setType("CHIEN");
		pet.setCreationDate(new Date());
		
		//Assert.assertNotNull(this.clientTest.getId());
		System.out.println("testt------------");
		System.out.println(clientTest.getId());
		pet.setGpClient(this.gpClientRepository.findById(clientTest.getId()).orElse(null));
		
		pet = this.gpPetRepository.save(pet);
		Assert.assertNotNull(pet.getId());
		
		this.createPetId = pet.getId();
	}
	
	@Test
	public void testFindAllPetWithSuccess() {
		// Given
		// When
		List<GpPet> pets = this.gpPetRepository.findAll();
		// Then
		Assert.assertTrue(pets.size() > 0);
	}
	
	@Test
	public void testFindByIdWithSuccess() throws GespetBusinessException {
		// Given
		Integer petId = this.createPetId;
		// When
		GpPet pet = this.gpPetRepository.findById(petId).orElse(null);
		// Then
		Assert.assertTrue(pet.getId() == petId); 
	}
	
	@Test
	public void testUpdatePet() throws AccessDeniedException, GespetBusinessException {
		// Given
		String nameTmp = "DOG ALLEMANDE";
		GpPet pet = this.gpPetRepository.findById(this.createPetId).orElse(null);
		pet.setName(nameTmp);
		// When

		GpPet petUpdate = this.gpPetRepository.saveAndFlush(pet);
		// Then

		Assert.assertTrue(nameTmp.equals(petUpdate.getName()));
	}
	
	@Test
	public void testDelete() throws AccessDeniedException, Exception {
		// Given
		Integer petId = this.createPetId;
		this.createPetId = null;
		// Whens
		this.gpPetRepository.deleteById(petId);
		GpPet pet = this.gpPetRepository.findById(petId).orElse(null);

		// Then 
		Assert.assertNull(pet);

	}
	
	@Before
	public void prepareAllEntityBefore() throws GespetBusinessException {
		
		// creation client
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

		this.clientTest = gpClientRepository.saveAndFlush(client);
		System.out.println("------------------");
		System.out.println(clientTest.getId());
		// creation pet
		GpPet pet = new GpPet();
		
		pet.setName("DOG MECHANT");
		pet.setAge(3); 
		pet.setRace("BERGER ALLEMAND");
		pet.setType("CHIEN");
		pet.setCreationDate(new Date());
		pet.setGpClient(this.clientTest);
		pet = gpPetRepository.save(pet);
		
		//Assert.assertNotNull(pet.getId());
		
		this.createPetId = pet.getId();
	}
	
	@AfterAll
	public void deleteAllEntityAfter() throws AccessDeniedException, GespetBusinessException {
		if (this.petIdForAllTest != null) {
			this.gpPetRepository.deleteById(this.petIdForAllTest);
		}

		if (!Objects.isNull(this.createPetId)) { 
			this.gpClientRepository.deleteById(this.createPetId); 
		}
		
		if (!Objects.isNull(this.clientTest)) {
			this.gpClientRepository.deleteById(this.clientTest.getId());
		}
	}

}
