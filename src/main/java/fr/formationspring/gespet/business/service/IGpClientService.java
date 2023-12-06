package fr.formationspring.gespet.business.service;

import fr.formationspring.gespet.business.dao.IGpClientRepository;
import fr.formationspring.gespet.business.dto.GpClientBasicDTO;
import fr.formationspring.gespet.business.dto.GpClientFullDTO;
import fr.formationspring.gespet.business.entity.GpClient;

public interface IGpClientService extends IAbstractGespetServives<GpClient, GpClientBasicDTO, GpClientFullDTO, IGpClientRepository> {
	
}
