package fr.formationspring.gespet.business.service.impl;

import javax.annotation.Resource;

import fr.formationspring.gespet.business.dao.IGpClientRepository;
import fr.formationspring.gespet.business.dto.GpClientBasicDTO;
import fr.formationspring.gespet.business.dto.GpClientFullDTO;
import fr.formationspring.gespet.business.utils.GespetConstantes;
import org.springframework.stereotype.Service;

import fr.formationspring.gespet.business.entity.GpClient;
import fr.formationspring.gespet.business.service.IGpClientService;





@Service(GespetConstantes.GespetConstantesService.GP_CLIENT_SERVICE_KEY)
public class GpClientServiceImpl extends AbstractGespetServiceImpl<GpClient, GpClientBasicDTO, GpClientFullDTO, IGpClientRepository> implements IGpClientService {
	
	@Resource(name = GespetConstantes.GespetConstantesDAO.GP_CLIENT_DAO)
	private IGpClientRepository gpClientRepository;
	
	public GpClientServiceImpl() {
		super(GpClient.class, GpClientBasicDTO.class, GpClientFullDTO.class);
	}

	@Override
	public IGpClientRepository getDAO() {
		return this.gpClientRepository;
	}

}
