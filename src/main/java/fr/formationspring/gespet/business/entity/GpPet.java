package fr.formationspring.gespet.business.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "GSP_PET")
public class GpPet implements IEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3843330837436343649L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PET_ID")
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "AGE")
	private Integer age;
	
	@Column(name = "RACE")
	private String race;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "UPDATE_DATE")
	private Date updateDate;
	
	@ManyToOne
	@JoinColumn(name = "CLIENT_ID")
	private GpClient gpClient;

	public GpPet() {
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

	public GpClient getGpClient() {
		return gpClient;
	}

	public void setGpClient(GpClient gpClient) {
		this.gpClient = gpClient;
	}
	
}
