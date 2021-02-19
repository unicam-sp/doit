package it.unicam.doit.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.doit.entity.Progetto;

@RestController
@RequestMapping(value="/api/progetti")
public class ProgettoController {

	private final static Logger LOG = LoggerFactory.getLogger(ProgettoController.class);
	
	@GetMapping("/storefront")
	public Object getStorefront() {
		LOG.debug("----------> GET progetti completati");
		return "prova";
	}
	
	@GetMapping("/")
	public Object getProjects(@RequestHeader Map<String, String> headers) {
		headers.forEach((key, value) -> {
			LOG.debug(key + ": " + value);
		});

		// TODO controlla ruoli
		// fa chiamata al database per ottenere i progetti

		// le chiavi nell'header sono tutte in lowecase
		if (headers.containsKey("userid")) {
			// controlla se e' un vero user
			try {
				LOG.debug("ci torno dopo");
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


	private List<Progetto> getRecruitersProject(UUID recruiterID) {

		return null;
	}

	@GetMapping("/creaNuovoProgetto")
	public void creaNuovoProgetto(int propositoreID) {

	}

}
