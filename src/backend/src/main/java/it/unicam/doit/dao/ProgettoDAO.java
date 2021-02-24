package it.unicam.doit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.unicam.doit.entity.Progetto;

public interface ProgettoDAO extends JpaRepository<Progetto, Integer> {
	
	@Query(value = "SELECT doit.progetto.* FROM doit.progetto WHERE doit.progetto.propositore_id = :idUser", nativeQuery = true)
	List<Progetto> findProjectsByUserID(@Param("idUser") int idUser);
	
}
