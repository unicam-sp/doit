package com.unicam.doit.progetto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.unicam.doit.esperto.Esperto;
import com.unicam.doit.propositore.PropositoreController;

@RestController
public class ProgettoController {

	// Il logger è integrato in spring boot
	private final static Logger LOG = LoggerFactory.getLogger(PropositoreController.class);

	@GetMapping("/api/projects")
	public Object getProjects(@RequestHeader Map<String, String> headers) {
		headers.forEach((key, value) -> {
			LOG.debug(key + ": " + value);
		});

		// controlla che ruoli ha
		// fa chiamata al database per ottenere i progetti

		// le chiavi nell'header sono tutte in lowecase
		if (headers.containsKey("userid")) {
			// controlla se e' un vero user
			try {
				String value = headers.get("userid");
				UUID id = UUID.fromString(value);
				return getProjectByUserID(id);
			} catch (Exception e) {
				e.printStackTrace();
				// return new ResponseEntity("Progetto non trovato", HttpStatus.NOT_FOUND);
				return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body("Nessun progetto trovato");
			}
		}

		if (headers.containsKey("recruiterid")) {
			// TODO controlla se e' un vero selezionatore
			try {
				String value = headers.get("recruiterid");
				UUID id = UUID.fromString(value);
				return getRecruitersProject(id);
			} catch (Exception e) {
				return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body("Nessun progetto trovato");
			}
		}

		if (headers.containsKey("projectid")) {
			// controlla se e' il progetto esiste
			LOG.debug("ricevuto projectID");
		}

		return null;

	}

	private List<Progetto> getProjectByUserID(UUID userID) {
		List<Progetto> progetti = new ArrayList<Progetto>();
		progetti.add(new Progetto(UUID.randomUUID(), "prova", "prova", "prova"));
		progetti.add(new Progetto(UUID.randomUUID(), "prova2", "prova2", "prova2"));
		return progetti;
	}

	private List<Progetto> getRecruitersProject(UUID recruiterID) {

		LOG.debug("============ START getRecruitersProject ==================");
		// TODO
		// ottieni progetti
		// per ogni progetto controlla che l'ID dei selezionatori

		// creo dei progetti a caso che dovrei ottenere dal DB
		List<Progetto> progetti = new ArrayList<Progetto>();
		progetti.add(new Progetto(UUID.randomUUID(), "titolo1", "descrizione1", "requisiti1"));
		progetti.add(new Progetto(UUID.randomUUID(), "titolo2", "descrizione2", "requisiti2"));
		progetti.add(new Progetto(UUID.randomUUID(), "titolo3", "descrizione3", "requisiti3"));

		// creo un selezionatore personalizzato
		UUID id = UUID.fromString("bfa3a6e9-c914-4bcb-9d4b-6d0c3b51101b");
		Esperto selezionatore = new Esperto(id, "questoseleziona@gmail.proj", "passw", "nome", "cognome");

		// aggiungo al secondo progetto come selezionatore l'ID passato dal frontend
		try {
			progetti.get(0).addSelezionatore(selezionatore);
			progetti.get(2).addSelezionatore(selezionatore);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// stampo i selezionatori di ogni progetto
		for (Progetto p : progetti) {
			List<Esperto> esperti = p.getSelezionatori();
			LOG.debug(p.getTitolo());
			for (Esperto e : esperti) {
				LOG.debug("    " + e.getNome());
			}
		}

		// cerco i progetti dove recruiterID e' selezionatore
		List<Progetto> progettiWhereRecruiterAppears = new ArrayList<Progetto>();
		for (Progetto p : progetti) {
			List<Esperto> esperti = p.getSelezionatori();
			// se appare
			if (esperti.contains(selezionatore)) {
				progettiWhereRecruiterAppears.add(p);
			}
		}

		LOG.debug("prima del return");
		for (Progetto p : progettiWhereRecruiterAppears) {
			LOG.debug(p.getTitolo());
		}
		return progettiWhereRecruiterAppears;
	}

	@GetMapping("/creaNuovoProgetto")
	public void creaNuovoProgetto(int propositoreID) {

	}

}
