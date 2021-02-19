 package it.unicam.doit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.unicam.doit.dao.DoitUserDAO;
import it.unicam.doit.entity.DoitRole;
import it.unicam.doit.entity.DoitUser;
import it.unicam.doit.model.DoitUserDetails;

@Service
public class DoitAuthenticationProvider implements UserDetailsService {
	
	private final static Logger LOG = LoggerFactory.getLogger(DoitAuthenticationProvider.class);
	
	@Autowired
	private DoitUserDAO doitUserDAO;
	
	
	/*
		@return un'istanza di DoitUserDetails se un DoitUser nel database con lo stesso username
		
		In altre parole, dato un username se lo trova nel database
		crea e ritorna un'istanza di DoitUserDetails che ad esempio servirà per 
		settare il SecurityContext
	*/
	@Override
	public DoitUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOG.debug(" -----> loadUserByUsername");
		
		DoitUser user = doitUserDAO.findByUsername(username);
		if(user==null)throw new UsernameNotFoundException("credenziali errate"); // 403
		
		Set<DoitRole> rolesNames = user.getRoles();
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (rolesNames != null) {
			for (DoitRole role : rolesNames) { // ROLE_USER, ROLE_ADMIN,..
	            GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
	            grantList.add(authority);
	        }
		}
		return new DoitUserDetails(user, grantList);
	}
	
	
}

/*
	Iterator<DoitRole> it = rolesNames.iterator();
	while(it.hasNext()) {
		DoitRole dr = it.next();
		LOG.debug(dr.getName());
	}
*/













