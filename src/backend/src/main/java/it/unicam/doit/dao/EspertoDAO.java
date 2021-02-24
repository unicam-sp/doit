package it.unicam.doit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.unicam.doit.entity.Esperto;

public interface EspertoDAO extends JpaRepository<Esperto, Integer> {

	Esperto findByUsername(String string);
	
	@Query(value = "SELECT username FROM doit.esperto JOIN doit.user ON doit.esperto.id = doit.user.id", nativeQuery = true)
	List<String> findAllExpertsUsername();

}
