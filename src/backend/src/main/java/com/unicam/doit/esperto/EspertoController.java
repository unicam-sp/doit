package com.unicam.doit.esperto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EspertoController {

	// Il logger è integrato in spring boot
	private final static Logger LOG = LoggerFactory.getLogger(EspertoController.class);

	@GetMapping("/api/experts")
	public List<Esperto> getEsperti(@RequestHeader Map<String, String> headers) {

		// legge UserID
		// controlla che ruoli ha
		// fa chiamata al database per ottenere gli esperti

		List<Esperto> AllEsperti = new ArrayList<Esperto>();
		AllEsperti.add(new Esperto("email1", "password1", "nome1", "cognome1"));
		AllEsperti.add(new Esperto("email2", "password2", "nome2", "cognome2"));

		return AllEsperti;
	}

}
