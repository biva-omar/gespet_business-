package fr.formationspring.gespet.business.dto;


public class GpPetMediumDTO extends GpPetBasicDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4265413284925832970L;
	
	private GpClientBasicDTO gpClient;

	public GpPetMediumDTO() {
		super();
	}

	public GpClientBasicDTO getGpClient() {
		return gpClient;
	}

	public void setGpClient(GpClientBasicDTO gpClient) {
		this.gpClient = gpClient;
	}
}
