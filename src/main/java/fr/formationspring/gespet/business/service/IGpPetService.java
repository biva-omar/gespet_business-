package fr.formationspring.gespet.business.service;

import fr.formationspring.gespet.business.dao.IGpPetRepository;
import fr.formationspring.gespet.business.dto.GpPetBasicDTO;
import fr.formationspring.gespet.business.dto.GpPetFullDTO;
import fr.formationspring.gespet.business.entity.GpPet;

public interface IGpPetService extends IAbstractGespetServives<GpPet, GpPetBasicDTO, GpPetFullDTO, IGpPetRepository> {

}
