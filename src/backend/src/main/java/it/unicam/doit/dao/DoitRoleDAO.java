package it.unicam.doit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unicam.doit.entity.DoitRole;

@Repository
public interface DoitRoleDAO 
	extends JpaRepository<DoitRole, Integer> {
	
	// WORKS
	// @Query("select u.name from DoitRole u JOIN UserRole r ON r.role_id = u.id WHERE r.user_id = :id")
	// public List<String> findUserRolesById(@Param("id") int id);
	
	// @Query("SELECT name FROM UserRole JOIN DoitRole ON UserRole.role_id = DoitRole.id WHERE UserRole.user_id = ?0") 
	// List<String> getRoles(Integer userID);
	
	// @Query("SELECT name FROM UserRole WHERE UserRole.user_id = 1") 
	
	// WHERE UserRole.user_id = 1
	
	// WORKS
	@Query("select u from DoitRole u where u.id = :id")
	public DoitRole prova(@Param("id") int id);
	
	// WORKS
	@Query("select u from DoitRole u")
	public List<DoitRole> prova2();
	

}
