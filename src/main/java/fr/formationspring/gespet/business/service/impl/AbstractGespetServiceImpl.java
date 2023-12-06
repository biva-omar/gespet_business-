package fr.formationspring.gespet.business.service.impl;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import fr.formationspring.gespet.business.dto.IDTO;
import fr.formationspring.gespet.business.exception.GespetBusinessException;
import fr.formationspring.gespet.business.service.IAbstractGespetServives;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.formationspring.gespet.business.entity.IEntity;

public abstract class AbstractGespetServiceImpl<Entity extends IEntity, BasicDTO extends IDTO, FullDTO extends BasicDTO, IEntityDAO extends JpaRepository<Entity, Integer>>
		implements IAbstractGespetServives<Entity, BasicDTO, FullDTO, IEntityDAO> {
	
	private Class<Entity> entityClass;
	private Class<BasicDTO> viewClass;
	private Class<FullDTO> fullDTOClass;
	
	@Resource(name = "gespet-modelmapper")
	private ModelMapper mapper;

	public AbstractGespetServiceImpl(Class<Entity> entityClass, Class<BasicDTO> viewClass,
			Class<FullDTO> fullDTOClass) {
		super();
		this.entityClass = entityClass;
		this.viewClass = viewClass;
		this.fullDTOClass = fullDTOClass;
	}	
	
	/**
	 * Create an new entity
	 * 
	 * @param ent : POJO to create
	 * @return
	 * @throws GespetBusinessException
	 */
	public FullDTO create(FullDTO view) throws GespetBusinessException {
		Entity entity = this.getModelMapper().map(view, this.entityClass);
		this.getDAO().save(entity);
		view.setId(entity.getId());
		return view;
	}
	
	/**
	 * Update an entity
	 * 
	 * @param entToUpdate
	 * @throws GespetBusinessException,AccessDeniedException
	 */
	public FullDTO update(FullDTO viewToUpdate) throws GespetBusinessException, AccessDeniedException {
		Entity entity = this.getModelMapper().map(viewToUpdate, this.entityClass);
		entity = this.getDAO().saveAndFlush(entity);
		return this.getModelMapper().map(entity, this.fullDTOClass);
	}
	
	/**
	 * Delete an entity
	 * 
	 * @param id : Entity'Id to delete
	 * @throws GespetBusinessException
	 * @throws AccessDeniedException
	 */
	public void deleteById(int id) throws GespetBusinessException, AccessDeniedException {
		this.getDAO().deleteById(id);
	}
	
	/**
	 * Find All Entity
	 * 
	 * @return
	 */
	public List<BasicDTO> findAll() {
		List<Entity> list = this.getDAO().findAll();
		List<BasicDTO> viewList = new ArrayList<BasicDTO>();
		for (Entity ent : list) {
			BasicDTO view = this.getModelMapper().map(ent, this.viewClass);
			viewList.add(view);
		}
		return viewList;
	}
	
	
	/**
	 * Find All Full Entity
	 * 
	 * @return
	 */
	public List<FullDTO> findAllFull() {
		List<Entity> list = this.getDAO().findAll();
		List<FullDTO> viewListFull = new ArrayList<FullDTO>();
		for (Entity ent : list) {
			FullDTO viewFull = this.getModelMapper().map(ent, this.fullDTOClass);
			viewListFull.add(viewFull);
		}
		return viewListFull;
	}
	
	/**
	 * Find All Entity 
	 * 
	 * @return
	 */
	public Page<FullDTO> findAllPageable(Pageable pageable) {
		List<Entity> list = this.getDAO().findAll();
		List<FullDTO> viewList = new ArrayList<FullDTO>();
		for (Entity ent : list) {
			FullDTO view = this.getModelMapper().map(ent, this.fullDTOClass);
			viewList.add(view);
		}
		int pageSize = pageable.getPageSize();
	    int currentPage = pageable.getPageNumber();
	    int startItem = currentPage * pageSize;
	    List<FullDTO> pageList;
	    if (viewList.size() < startItem) {
	        pageList = Collections.emptyList();
	    } else {
	        int toIndex = Math.min(startItem + pageSize, viewList.size());
	        pageList = viewList.subList(startItem, toIndex);
	    }
	    
	    Page<FullDTO> fullDTOPage = new PageImpl<>(pageList, PageRequest.of(currentPage, pageSize), viewList.size());
		return fullDTOPage;
	}
	
	/**
	 * Found Entity By Id
	 * 
	 * @param id : Entity's Id to found
	 * @return
	 * @throws GespetBusinessException
	 */
	public FullDTO findById(int id) throws GespetBusinessException {

		Entity entity = this.getDAO().findById(id).orElse(null);
		if (entity != null) {

			FullDTO view = this.getModelMapper().map(entity, this.fullDTOClass);
			return view;
		} else {
			return null;
		}
	}
	
	/**
	 * Teste l'existence d'une Entité par son id
	 * 
	 * @param id
	 * @return
	 * @throws GespetBusinessException
	 */
	public boolean ifEntityExistById(int id) throws GespetBusinessException {
		return this.getDAO().existsById(id);
	}
	
	/**
	 * Recupere un DTO par son id
	 */
	public <T extends BasicDTO> T findById(int id, Class<T> type) {
		Entity ent = this.getDAO().getById(id);
		T view = this.getModelMapper().map(ent, type);
		return view;
	}
	
	/**
	 * Retourne la reference du DAO en cours
	 * 
	 * @return
	 */
	public abstract IEntityDAO getDAO();
	
	public ModelMapper getModelMapper() {
		return this.mapper;
	}
}
