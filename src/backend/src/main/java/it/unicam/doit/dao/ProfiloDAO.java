package it.unicam.doit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.unicam.doit.entity.Profilo;

public interface ProfiloDAO extends JpaRepository<Profilo, Integer> {
	
	@Query(value = "SELECT username FROM doit.user WHERE doit.user.profilo_id IS NOT NULL", nativeQuery = true)
	List<String> findUserNamesWithProfile();
	
	@Query(value = "SELECT doit.profilo.* FROM doit.profilo JOIN doit.user ON doit.profilo.id = doit.user.id", nativeQuery = true)
	Profilo findProfileOf(String username);

}
