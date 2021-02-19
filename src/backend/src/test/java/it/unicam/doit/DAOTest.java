/*
	@SpringBootTest 
		loads full application context, 
		exactly like how you start a 
		Spring container when you run 
		your Spring Boot application.

    @WebMvcTest 
    	loads only the web layer, which 
    	includes security, filter, 
    	interceptors, etc for handling 
    	request/response. Typically you 
    	would write tests for methods 
    	under @Controller or @RestController.

    @DataJpaTest 
    	loads only configuration for JPA. 
    	It uses an embedded in-memory h2 
    	if not specified otherwise.

*/
package it.unicam.doit;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import it.unicam.doit.dao.DoitRoleDAO;
import it.unicam.doit.dao.DoitUserDAO;
import it.unicam.doit.dao.EspertoDAO;
import it.unicam.doit.dao.PropositoreDAO;
import it.unicam.doit.entity.DoitRole;
import it.unicam.doit.entity.DoitUser;
import it.unicam.doit.entity.Esperto;
import it.unicam.doit.entity.Propositore;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DAOTest {

	@Autowired
	private DoitUserDAO doitUserDAO;

	@Autowired
	private DoitRoleDAO doitRoleDAO;

	@Autowired
	private EspertoDAO espertoDAO;

	@Autowired
	private PropositoreDAO propositoreDAO;

	@Test
	@Transactional
	@Rollback(true)
	public void testAddDepartment() {
		userDaoTest();
		roleDaoTest();
		propositoreDaoTest();
		espertoDaoTest();
	}

	public void userDaoTest() {
		System.out.println("\n --------------- INIZIO USER QUERY TEST --------------- ");

		DoitUser admin = doitUserDAO.findByUsername("admin");
		assertNotNull(admin);

		System.out.println(" --------------- LETTURA DEI RUOLI DI UN UTENTE --------------- \n");
		List<String> ruoli = doitUserDAO.findRoleByUserID(1);
		for (String s : ruoli)
			System.out.println(s);

		System.out.println(" --------------- AGGIUNGERE UN RUOLO AD UN UTENTE --------------- \n");
		admin.getRoles().add( doitRoleDAO.getOne(1) );
		assertEquals(2, admin.getRoles().size());
		
		System.out.println(" --------------- AGGIUNGERE UN UTENTE SENZA RUOLI --------------- \n");
		DoitUser newUser = new DoitUser("Alberto", "Sassi", "email");
		doitUserDAO.save(newUser);
		
		System.out.println(" --------------- ELIMINARE UN UTENTE SENZA RUOLI --------------- \n");
		doitUserDAO.deleteByUsername(newUser.getUsername());

		System.out.println(" --------------- FINE USER QUERY TEST --------------- \n");
	}

	public void roleDaoTest() {
		System.out.println("\n --------------- INIZIO ROLE QUERY TEST --------------- ");

		List<DoitRole> threeRole = doitRoleDAO.findAll();
		for (DoitRole role : threeRole)
			System.out.println(role.getId() + " " + role.getName());

		System.out.println(" --------------- FINE ROLE QUERY TEST --------------- \n");
	}

	private void propositoreDaoTest() {
		System.out.println("\n --------------- INIZIO PROPOSITORE QUERY TEST --------------- ");
		System.out.println("\n --------------- TEST CREA UTENTE PROPOSITORE COMPLETO --------------- ");
		Propositore prop1 = new Propositore("username1", "sdasd", "email@gmail.com", "Nome Ente", "VAT Number");
		prop1.getRoles().add(doitRoleDAO.getOne(2));
		prop1.getRoles().add(doitRoleDAO.getOne(3));
		propositoreDAO.save(prop1);
		System.out.println("\n --------------- FINE PROPOSITORE QUERY TEST --------------- ");
	}

	public void espertoDaoTest() {
		System.out.println("\n --------------- INIZIO ESPERTO QUERY TEST --------------- ");

		System.out.println("\n --------------- TEST ELIMINAZIONE A CASCATA CON RECORD GENITORE --------------- ");
		espertoDAO.save(new Esperto("username1", "password", "email", "Giacomo", "Rossi"));
		Esperto esperto = espertoDAO.findByUsername("username1");
		assertNotNull(esperto);
		doitUserDAO.deleteByUsername("username1"); // cancello l'esperto dalla tabella user invece che dalla tabella esperto
		Esperto query = espertoDAO.findByUsername("username1"); // cerco l'utente nella tabella esperto
		assertNull(query); // mi assicuro che la query non trovi nulla

		System.out.println("\n --------------- TEST ELIMINAZIONE UTENTI SENZA RUOLO --------------- ");
		
		Propositore prop1 = new Propositore("username2", "password", "email", "nomeEnte", "VATNumber");
		prop1.getRoles().add(doitRoleDAO.getOne(2)); // USER
		prop1.getRoles().add(doitRoleDAO.getOne(3)); // PROPOSITORE
		prop1.getRoles().add(doitRoleDAO.getOne(4)); // PROGETTISTA
		propositoreDAO.save(prop1);
		
		Esperto esp1 = new Esperto("username6", "password", "email", "Giacomo", "Rossi");
		esp1.getRoles().add( doitRoleDAO.getOne(2) ); // USER
		esp1.getRoles().add( doitRoleDAO.getOne(4) ); // PROGETTISTA
		esp1.getRoles().add( doitRoleDAO.getOne(6) ); // ESPERTO
		espertoDAO.save(esp1);

		printTableStatus();
		
		// 1. trova tutti gli user
		// 2. ottieni i ruoli
		// 3. se non ha ruoli eliminalo
		List<DoitUser> lista = doitUserDAO.findAll();
		for(DoitUser du : lista) {
			if( du.getRoles().size() == 0) {
				System.out.println("--- " + du.getUsername());
				doitUserDAO.deleteByUsername(du.getUsername()); // da errore		
			}
		}
		
		assertEquals(3, doitUserDAO.count()); // esp1, prop1, admin
		System.out.println("\n --------------- FINE ESPERTO QUERY TEST --------------- ");
	}

	public void printTableStatus() {
		System.out.println("\n --- PRINT --- ");
		
		List<DoitUser> users = doitUserDAO.findAll();
		System.out.println("\n --- Tabella DoitUser --- ");
		for (DoitUser u : users) {
			System.out.println( u.getId() + " " + u.getUsername() + " ");
		}
		System.out.println("---\n");
		
		List<Propositore> propositori = propositoreDAO.findAll();
		System.out.println("\n --- Tabella Propositore --- ");
		for (Propositore p : propositori) {
			System.out.println(p.getUsername() + " " + p.getNomeEnte());
		}
		
		System.out.println("---\n");
	}

}
