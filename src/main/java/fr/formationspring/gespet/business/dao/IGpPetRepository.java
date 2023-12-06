package fr.formationspring.gespet.business.dao;

import fr.formationspring.gespet.business.entity.GpPet;
import fr.formationspring.gespet.business.utils.GespetConstantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(GespetConstantes.GespetConstantesDAO.GP_PET_DAO)
public interface IGpPetRepository extends JpaRepository<GpPet, Integer> {

}
