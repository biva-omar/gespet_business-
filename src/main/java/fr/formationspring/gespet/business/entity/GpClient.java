package fr.formationspring.gespet.business.entity;

//import fr.formationspring.gespet.business.enums.ListRole;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GSP_CLIENT")
public class GpClient implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8055281044871398454L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLIENT_ID")
	private Integer id;


	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "OCCUPATION")
	private String occupation;
	
	@Column(name = "AGE")
	private Integer age;
	
	@Column(name = "SEXE")
	private char sexe;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	@OneToMany(mappedBy = "gpClient")
	private List<GpPet> gpPets;

	public GpClient() {
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public char getSexe() {
		return sexe;
	}

	public void setSexe(char sexe) {
		this.sexe = sexe;
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

	public List<GpPet> getGpPets() {
		return gpPets;
	}

	public void setGpPets(List<GpPet> gpPets) {
		this.gpPets = gpPets;
	}
}
