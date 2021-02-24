package it.unicam.doit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.unicam.doit.entity.Selezionatore;

public interface SelezionatoreDAO extends JpaRepository<Selezionatore, Integer> {
	
	@Query(value = "SELECT * FROM doit.selezionatore "
			+ "WHERE doit.selezionatore.esperto_id = :esperto_id "
			+ "AND doit.selezionatore.progetto_id = :progetto_id", nativeQuery = true)
	List<String> findWithIds(@Param("esperto_id") int esperto_id, @Param("progetto_id") int progetto_id);
	
}
