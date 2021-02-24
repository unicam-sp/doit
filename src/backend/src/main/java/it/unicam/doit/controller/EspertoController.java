package it.unicam.doit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unicam.doit.dao.EspertoDAO;

@RestController
@RequestMapping(value = "/api/expert/")
public class EspertoController {

	private final static Logger LOG = LoggerFactory.getLogger(EspertoController.class);
	
	@Autowired
	private EspertoDAO espertoDAO;

	@GetMapping("")
	public List<String> getEsperti() {
		return espertoDAO.findAllExpertsUsername(); // serialize loop
	}

}
