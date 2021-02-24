package it.unicam.doit.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unicam.doit.entity.DoitUser;

/*
	Estendendo con JpaRepository invece che solo con Repository
	si ottiene la possibilita' di usare altri metodi tipo CRUD
	
	Alcune query possono venire inferite dal nome del metodo
*/
@Repository
public interface DoitUserDAO 
	extends JpaRepository<DoitUser, Integer> {
	
	DoitUser findByUsername(@Param("username") String username);

	void deleteByUsername(String string);
	
	// WORKS
	@Query(value = "SELECT name FROM doit.user_role JOIN doit.role ON user_role.role_id = role.id WHERE user_role.user_id = :idUser", nativeQuery = true)
	List<String> findRoleByUserID(@Param("idUser") int idUser);
	
	@Query(value = "SELECT username FROM doit.user WHERE doit.user.id = :id", nativeQuery = true)
	String getUsernameById(@Param("id") int id);
	
}
	
	
//	default List<DoitUser> deleteUserWithNoRole() {
//	// find users with no roles
//	List<DoitUser> listaUtentiSenzaRuolo = this.findAll();
//	
//	return listaUtentiSenzaRuolo;
//}

	//da problemi ma funzionava
//	@Query(value = "SELECT doit.user.* FROM doit.user LEFT JOIN user_role ON user.id = user_role.user_id WHERE role_id IS NULL", nativeQuery = true)
//	List<DoitUser> findUserWithNoRole();

// 	SELECT A FROM EntityA LEFT JOIN EntityB B WHERE B.entityA = A 
// 	select u from UserGroup ug inner join ug.user u 

//	@Query("select du FROM DoitUser du LEFT JOIN du.roles r where r.id = 0")
//	List<DoitUser> leftJoin();
	
	
	
	// @Query(value = "DELETE doit.user FROM user LEFT JOIN user_role ON user.id = user_role.user_id WHERE role_id IS NULL", nativeQuery = true)
	
	// Solo i parametri posizionali sono supportati nelle query native
	/* 	JPA non ha nessun INSERT statement
			per inserire un elemento sulla joinedTable bisogna:
				1. ottenere l'utente (findByUsername)
				3. ottenere i ruoli List<DoitRole>
				4. aggiungere alla lista quello nuovo
				2. settare i ruoli con la lista aggiornata doitUser.setRoles(List<DoitRole>)
	*/
	//@Query(value = "INSERT INTO doit.user_role (user_id, role_id) VALUES (:idUser, :idRole)", nativeQuery = true)
	//public void setRole(@Param("idUser") int idUser, @Param("idRole") int idRole);

	
