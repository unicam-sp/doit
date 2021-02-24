package it.unicam.doit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.doit.dao.DoitRoleDAO;
import it.unicam.doit.dao.DoitUserDAO;
import it.unicam.doit.dao.EspertoDAO;
import it.unicam.doit.dao.PropositoreDAO;
import it.unicam.doit.entity.DoitUser;
import it.unicam.doit.entity.Esperto;
import it.unicam.doit.entity.Propositore;
import it.unicam.doit.model.DoitUserDetails;
import it.unicam.doit.model.EnteSignUp;
import it.unicam.doit.model.LoginUser;
import it.unicam.doit.model.PersonaSignUp;
import it.unicam.doit.security.JwtUtil;
import it.unicam.doit.service.DoitAuthenticationProvider;

@RestController
@RequestMapping(value = "/api/auth/")
public class AuthController {
	
	private final static Logger LOG = LoggerFactory.getLogger(AuthController.class);

	/*
	Spring Security contiene le informazioni di ogni utente autenticato
	in un ThreadLocal rappresentato da un oggetto Authentication.
	
	1 istanza di Authentication = 1 utente autenticato
	*/
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private DoitAuthenticationProvider authProvider;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private DoitUserDAO userDAO;
	
	@Autowired
	private DoitRoleDAO doitRoleDAO;
	
	@Autowired
	private EspertoDAO espertoDAO;
	
	@Autowired
	private PropositoreDAO propositoreDAO;
	
	/*
		@return un json web token, oppure una eccezione
		
		TODO 303 as redirect code
	*/
	@PostMapping(value = "login")
	public ResponseEntity<String> postLogin(@RequestBody LoginUser user) throws Exception {
		LOG.debug(" -----> richiesta di login");
		LOG.debug(user.getUsername());
		try {
			UsernamePasswordAuthenticationToken richiestaDiAutenticazione = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			Authentication auth = authManager // chiama il metodo loadUserByUsername in DoitAuthenticationProvider
					.authenticate(richiestaDiAutenticazione); // paragona DoitUserDetails con richiestaDiAutenticazione
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("credenziali errate");
		}
		// a questo punto l'user e' autenticato
		DoitUserDetails dud = authProvider.loadUserByUsername(user.getUsername());
		String jwt = jwtUtil.generateToken(dud);
		return ResponseEntity.ok(jwt);
	}
	
	@PostMapping(value = "signUpPersona")
	public ResponseEntity<String> postSignUpPersona(@RequestBody PersonaSignUp persona) throws Exception {
		// username, password, email, nome, cognome
		String check = checkUsernamePasswordEmail(persona.getUsername(), persona.getPassword(), persona.getEmail());
		if(!check.equals("")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(check);
		
		if(persona.getNome().equals("None") || persona.getNome().equals("")) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nome non valido");
		if(persona.getCognome().equals("None") || persona.getCognome().equals("")) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cognome non valido");
				
		Esperto user = new Esperto();
		user.setUsername(persona.getUsername());
		user.setPassword(persona.getPassword());
		user.setEmail(persona.getEmail());
		user.setNome(persona.getNome());
		user.setCognome(persona.getCognome());
		
		user.getRoles().add( doitRoleDAO.getOne(2) ); // USER
		user.getRoles().add( doitRoleDAO.getOne(4) ); // PROGETTISTA
		user.getRoles().add( doitRoleDAO.getOne(6) ); // ESPERTO
		espertoDAO.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body("Nuovo utente creato!");
	}
	
	@PostMapping(value = "signUpEnte")
	public ResponseEntity<String> postSignUpEnte(@RequestBody EnteSignUp ente) throws Exception {
		// Ente = PROGETTISTA + PROPOSITORE
		String check = checkUsernamePasswordEmail(ente.getUsername(), ente.getPassword(), ente.getEmail());
		if(!check.equals("")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(check);
		
		if(ente.getNomeEnte().equals("None") || ente.getNomeEnte().equals("")) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("nome non valido");
		if(ente.getVATNumber().equals("None") || ente.getVATNumber().equals("")) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("cognome non valido");
		
		Propositore prop = new Propositore();
		prop.setUsername(ente.getUsername());
		prop.setPassword(ente.getPassword());
		prop.setEmail(ente.getEmail());
		prop.setNomeEnte(ente.getNomeEnte());
		prop.setVATNumber(ente.getVATNumber());
		
		prop.getRoles().add( doitRoleDAO.getOne(2) ); // USER
		prop.getRoles().add( doitRoleDAO.getOne(4) ); // PROGETTISTA
		prop.getRoles().add( doitRoleDAO.getOne(3) ); // PROPOSITORE
		propositoreDAO.save(prop);
		return ResponseEntity.status(HttpStatus.CREATED).body("Nuovo ente creato!");
	}
	
	private String checkUsernamePasswordEmail(String username, String password, String email) {
		// TODO regex
		DoitUser findUser = userDAO.findByUsername(username);
		if(findUser != null) return "nome utente già esistente";
		if(username.equals("None") || username.equals("")) return "username non valido";
		if(password.equals("")) return "password non valida";
		if(email.equals("None") || email.equals("")) return "email non valida";
		return "";
	}
	
}


































