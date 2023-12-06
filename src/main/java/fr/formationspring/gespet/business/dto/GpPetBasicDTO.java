package fr.formationspring.gespet.business.dto;

import java.util.Date;

public class GpPetBasicDTO implements IDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7022825723438278988L;

	private Integer id;
	
	private String name;
	
	private Integer age;
	
	private String race;
	
	private String type;
	
	private Date creationDate;
	
	private Date updateDate;

	public GpPetBasicDTO() {
		super();
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
