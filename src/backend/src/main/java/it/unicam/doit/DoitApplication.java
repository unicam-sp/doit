package it.unicam.doit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import it.unicam.doit.dao.DoitRoleDAO;
import it.unicam.doit.dao.EspertoDAO;
import it.unicam.doit.dao.PropositoreDAO;
import it.unicam.doit.entity.Esperto;
import it.unicam.doit.entity.Propositore;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "it.unicam.doit.dao")
public class DoitApplication {
	
	@Autowired
	private EspertoDAO espertoDAO;
	
	@Autowired
	private DoitRoleDAO doitRoleDAO;
	
	@Autowired
	private PropositoreDAO propositoreDAO;
	
	public static void main(String[] args) {
		SpringApplication.run(DoitApplication.class, args);
	}

	@PostConstruct
	public void populate() {
		
		Esperto e1 = new Esperto();
		// "user", "pwd", "email", "Jak", "Red"
		e1.setNome("Jak");
		e1.setCognome("Red");
		e1.setUsername("user");
		e1.setPassword("pwd");
		e1.setEmail("email");
		
		Propositore p1 = new Propositore();
		p1.setUsername("propositore");
		p1.setPassword("pwd");
		p1.setEmail("email");
		p1.setNomeEnte("nome ente");
		p1.setVATNumber("vat number");
		
		// se non salvo non esistera' l'istanza di e1 sulla tabella user
		// quindi non sara' possibile usare il metodo getRoles()
		espertoDAO.save(e1); 
		propositoreDAO.save(p1);
		
		e1.getRoles().add( doitRoleDAO.getOne(2) ); // USER
		e1.getRoles().add( doitRoleDAO.getOne(4) ); // PROGETTISTA
		e1.getRoles().add( doitRoleDAO.getOne(6) ); // ESPERTO
		
		p1.getRoles().add( doitRoleDAO.getOne(2) ); // USER
		p1.getRoles().add( doitRoleDAO.getOne(4) ); // PROGETTISTA
		p1.getRoles().add( doitRoleDAO.getOne(3) ); // PROPOSITORE
		
		espertoDAO.save(e1);
		propositoreDAO.save(p1);
		
	}

}

/*
	List<DoitUser> users = Stream.of(
		new DoitUser("user", "pass", "email"),
		new DoitUser("bean", "mister", "email"))
		.collect(Collectors.toList());
*/