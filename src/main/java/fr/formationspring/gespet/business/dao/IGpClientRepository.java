package fr.formationspring.gespet.business.dao;

import fr.formationspring.gespet.business.utils.GespetConstantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.formationspring.gespet.business.entity.GpClient;

@Repository(GespetConstantes.GespetConstantesDAO.GP_CLIENT_DAO)
public interface IGpClientRepository extends JpaRepository<GpClient, Integer> {

}