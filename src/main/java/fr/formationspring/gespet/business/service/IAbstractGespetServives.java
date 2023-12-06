package fr.formationspring.gespet.business.service;

import java.nio.file.AccessDeniedException;
import java.util.List;

import fr.formationspring.gespet.business.exception.GespetBusinessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAbstractGespetServives <Entity, BasicDTO, FullDTO extends BasicDTO, IEntityDAO extends JpaRepository<Entity, Integer>> {
	
	
	/**
	 * Create an new entity
	 * 
	 * @param ent : POJO to create
	 * @return
	 * @throws GespetBusinessException
	 */
	public FullDTO create(FullDTO ent) throws GespetBusinessException;
	
	/**
	 * Update an entity
	 * 
	 * @param entToUpdate
	 * @throws GespetBusinessException,AccessDeniedException
	 */
	public FullDTO update(FullDTO eOntToUpdate) throws GespetBusinessException, AccessDeniedException;
	
	/**
	 * Delete an entity
	 * 
	 * @param id : Entity'Id to delete
	 * @throws GespetBusinessException
	 * @throws AccessDeniedException
	 * 
	 */
	public void deleteById(int id) throws GespetBusinessException, AccessDeniedException;
	
	/**
	 * Find All Entity
	 * 
	 * @return
	 */
	public List<BasicDTO> findAll();
	
	
	/**
	 * Find All Entity
	 * 
	 * @return
	 */
	public List<FullDTO> findAllFull();
	
	
	/**
	 * Find All Entity Pageable
	 * 
	 * @return
	 */
	public Page<FullDTO> findAllPageable(Pageable pageable);
	
	
	/**
	 * Found Entity By Id
	 * 
	 * @param id : Entity's Id to found
	 * @return
	 * @throws GespetBusinessException
	 */

	public FullDTO findById(int id) throws GespetBusinessException;
	
	
	/**
	 * Teste l'existence d'une EntitÃ© par son id
	 * 
	 * @param id
	 * @return
	 * @throws GespetBusinessException
	 */
	public boolean ifEntityExistById(int id) throws GespetBusinessException;
	
	
	/**
	 * Recupere un DTO par son id
	 */
	public <T extends BasicDTO> T findById(int id, Class<T> type) throws GespetBusinessException;
	
	
	/**
	 * Retourne la reference du DAO en cours
	 * 
	 * @return
	 */
	public IEntityDAO getDAO();
	
}
