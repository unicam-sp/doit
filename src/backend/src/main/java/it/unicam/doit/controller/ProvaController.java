package it.unicam.doit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.doit.security.DoitConfiguration;

@RestController
@RequestMapping(value="/")
public class ProvaController {
	
	private final static Logger LOG = LoggerFactory.getLogger(DoitConfiguration.class);
	
	@RequestMapping(value="login", method=RequestMethod.POST) 
	public String getLogin() {
		LOG.debug(" -------- > POST login");
		return "login richiesto";
	}
	
}
