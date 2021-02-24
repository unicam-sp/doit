package it.unicam.doit.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.doit.dao.DoitUserDAO;
import it.unicam.doit.dao.EspertoDAO;
import it.unicam.doit.dao.ProgettoDAO;
import it.unicam.doit.dao.SelezionatoreDAO;
import it.unicam.doit.entity.DoitUser;
import it.unicam.doit.entity.Esperto;
import it.unicam.doit.entity.Progetto;
import it.unicam.doit.entity.Selezionatore;
import it.unicam.doit.entity.StatoProgetto;
import it.unicam.doit.model.DoitUserDetails;
import it.unicam.doit.model.NuovoProgetto;
import it.unicam.doit.model.ProgettoModificato;

@RestController
@RequestMapping(value="/api/progetti/")
public class ProgettoController {

	private final static Logger LOG = LoggerFactory.getLogger(ProgettoController.class);
	
	@Autowired
	private ProgettoDAO progettoDAO;
	
	@Autowired
	private DoitUserDAO doitUserDAO;
	
	@Autowired
	private EspertoDAO espertoDAO;
	
	@Autowired
	private SelezionatoreDAO selezionatoreDAO;
	
	@PostMapping(value = "modify")
	public ResponseEntity<String> modify(@RequestBody ProgettoModificato progetto) throws Exception {
		
		Progetto p = progettoDAO.getOne(progetto.getId());
		p.setTitolo(progetto.getTitolo());
		p.setDescrizione(progetto.getDescrizione());
		p.setRequisiti(progetto.getRequisiti());
		progettoDAO.save(p); // fino a qui funziona
		
		// cicla la lista di username selezionati
		for(String username : progetto.getSelezionatori()) {
			Esperto e = espertoDAO.findByUsername(username);
			List<String> selezionatore = selezionatoreDAO.findWithIds(e.getId(), progetto.getId());
			if(selezionatore.size() >= 1) continue;
			
			Selezionatore s = new Selezionatore();
			s.setEsperto_id(e.getId());
			s.setProgetto_id(p.getId());
			
			selezionatoreDAO.save(s);
			e.getSelezionatore_di().add(s);
			espertoDAO.save(e);
			p.getSelezionatori().add(s);
		}
		progettoDAO.save(p);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Progetto modificato!");
	}
	
	@PostMapping("getProjectByUsername")
	public List<Progetto> getProjectByUsername(@RequestBody Map<String, String> json) {
		DoitUser du = doitUserDAO.findByUsername(json.get("username"));
		List<Progetto> progetti = progettoDAO.findProjectsByUserID(du.getId());
		return progetti;
	}
	
	@PostMapping(value = "creaNuovoProgetto")
	public ResponseEntity<String> creaNuovoProgetto(@RequestBody NuovoProgetto progetto) throws Exception {
		LOG.debug(" -----> richiesta creazione nuovo progetto");
		
		// TODO controlla autorizzazioni ed ottieni DoitUserDetails tramite metodo su Utils
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof DoitUserDetails) {
    		DoitUserDetails propositoreDetails = (DoitUserDetails) principal;
    		Progetto p = new Progetto();
    		p.setPropositoreId(propositoreDetails.getId());
    		p.setTitolo(progetto.getTitolo());
    		p.setDescrizione(progetto.getDescrizione());
    		p.setRequisiti(progetto.getRequisiti());
    		p.setStato(StatoProgetto.IN_RICERCA);
    		progettoDAO.save(p);
    		
    		return ResponseEntity.status(HttpStatus.CREATED).body("Progetto creato!");
		} else {
    		LOG.debug(principal.toString());
    		throw new Exception("Utente non riconosciuto");
		}

	}
	
	@GetMapping("storefront")
	public Object getStorefront() {
		LOG.debug("----------> GET progetti completati");
		return "prova";
	}

	private List<Progetto> getRecruitersProject(UUID recruiterID) {

		return null;
	}

}

