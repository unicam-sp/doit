package it.unicam.doit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.doit.dao.DoitUserDAO;
import it.unicam.doit.model.Id;

@RestController
@RequestMapping(value="/api/selezionatore/")
public class SelezionatoreController {
	
	private final static Logger LOG = LoggerFactory.getLogger(ProgettoController.class);
	
	@Autowired
	private DoitUserDAO doitUserDAO;
	
	@PostMapping(value = "getUsernameById")
	public String getUsernameById(@RequestBody Id id) throws Exception {
		LOG.debug(" ---> richiesta di ricerca di username");
		return doitUserDAO.getUsernameById(id.getId());
	}
	
}
