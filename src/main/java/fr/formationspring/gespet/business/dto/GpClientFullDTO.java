package fr.formationspring.gespet.business.dto;

import java.util.List;

public class GpClientFullDTO extends GpClientMediumDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5431361226130803526L;
	
	private List<GpPetBasicDTO> gpPets;
	
	public GpClientFullDTO() {
		super();
	}

	public List<GpPetBasicDTO> getGpPets() {
		return gpPets;
	}

	public void setGpPets(List<GpPetBasicDTO> gpPets) {
		this.gpPets = gpPets;
	}
}
