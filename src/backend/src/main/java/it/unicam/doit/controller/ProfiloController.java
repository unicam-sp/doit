package it.unicam.doit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import it.unicam.doit.dao.CvDAO;
import it.unicam.doit.dao.DoitUserDAO;
import it.unicam.doit.dao.ProfiloDAO;
import it.unicam.doit.entity.Cv;
import it.unicam.doit.entity.DoitUser;
import it.unicam.doit.entity.Profilo;
import it.unicam.doit.model.DoitUserDetails;
import it.unicam.doit.security.DoitConfiguration;

@RestController
@RequestMapping(value = "/api/profilo/")
public class ProfiloController {
	
	private final static Logger LOG = LoggerFactory.getLogger(DoitConfiguration.class);
	
	@Autowired
	private CvDAO cvDAO;
	
	@Autowired
	private ProfiloDAO profiloDAO;
	
	@Autowired
	private DoitUserDAO doitUserDAO;
	
	@PostMapping("getWithUsername")
	public Profilo getWithUsername(@RequestBody String username) {
		LOG.debug("----> richiesta di un profilo tramite username");
		Profilo p = profiloDAO.findProfileOf(username);
		return p;
	}


	@GetMapping("profili")
	public List<String> getProfili() {
		List<String> user_con_profilo = profiloDAO.findUserNamesWithProfile();
		return user_con_profilo;
	}

	@PostMapping(value = "loadCv")
    public ResponseEntity<String> loadCv(@RequestParam("file") MultipartFile multipartFile, @RequestParam("title") String title) throws Exception {
    	
    	LOG.debug(" ----> richiesta caricamento file");
    	
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (principal instanceof DoitUserDetails) {
    		DoitUserDetails doitUserDetails = (DoitUserDetails) principal;
    		
    		DoitUser du = doitUserDetails.getDoitUser();
    		Profilo p = du.getProfilo();
    		
    		if(p == null) p = new Profilo();
    		
    		Cv cv = new Cv();
    		cv.setId(du.getId());
        	cv.setTitle(title);
        	cv.setBlob_type(multipartFile.getContentType());
        	cv.setContent(multipartFile.getBytes());
            cvDAO.save(cv);
            
            p.setId(du.getId());
            p.setCv(cv);
    		profiloDAO.save(p);
    		
    		du.setProfilo(p);
    		doitUserDAO.save(du);
            
            return ResponseEntity.ok("CV salvato");
		} else {
    		LOG.debug(principal.toString());
    		throw new Exception("Utente non riconosciuto");
		}
    }
    
	
}
