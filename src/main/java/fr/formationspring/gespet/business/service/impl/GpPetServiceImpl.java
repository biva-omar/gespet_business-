package fr.formationspring.gespet.business.service.impl;

import javax.annotation.Resource;

import fr.formationspring.gespet.business.dao.IGpPetRepository;
import fr.formationspring.gespet.business.dto.GpPetBasicDTO;
import fr.formationspring.gespet.business.dto.GpPetFullDTO;
import fr.formationspring.gespet.business.entity.GpPet;
import fr.formationspring.gespet.business.utils.GespetConstantes;
import org.springframework.stereotype.Service;

import fr.formationspring.gespet.business.service.IGpPetService;

@Service(GespetConstantes.GespetConstantesService.GP_PET_SERVICE_KEY)
public class GpPetServiceImpl extends AbstractGespetServiceImpl<GpPet, GpPetBasicDTO, GpPetFullDTO, IGpPetRepository> implements IGpPetService {

	
	@Resource(name = GespetConstantes.GespetConstantesDAO.GP_PET_DAO)
	private IGpPetRepository gpPetRepository;
	
	
	public GpPetServiceImpl() {
		super(GpPet.class, GpPetBasicDTO.class, GpPetFullDTO.class);
	}

	@Override
	public IGpPetRepository getDAO() {
		return this.gpPetRepository;
	}

}
