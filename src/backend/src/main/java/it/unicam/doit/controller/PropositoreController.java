 package it.unicam.doit.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropositoreController {

	private final static Logger LOG = LoggerFactory.getLogger(PropositoreController.class);
	
	@RequestMapping("/hello/world")
	public String helloworld(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("-----------------------------------");
	    Enumeration<String> names = request.getParameterNames();
	    LOG.debug(request.getMethod());
	    while (names.hasMoreElements()) {
			String paramName = names.nextElement();
			LOG.debug(paramName);
			String[] paramValues = request.getParameterValues(paramName);
			for (int i = 0; i < paramValues.length; i++) {
				String paramValue = paramValues[i];
				LOG.debug(paramValue);
			}

		}
	    
	    return "test per leggere i parametri della request";
	}

}
