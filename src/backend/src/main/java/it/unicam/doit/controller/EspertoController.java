package it.unicam.doit.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.doit.entity.Esperto;

@RestController
public class EspertoController {

	// Il logger e' integrato in spring boot
	private final static Logger LOG = LoggerFactory.getLogger(EspertoController.class);

	@GetMapping("/api/experts")
	public List<Esperto> getEsperti(@RequestHeader Map<String, String> headers) {

		// legge UserID
		// controlla che ruoli ha
		// fa chiamata al database per ottenere gli esperti
		return null;
	}

}
