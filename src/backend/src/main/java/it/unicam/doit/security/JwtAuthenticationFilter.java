package it.unicam.doit.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import it.unicam.doit.model.DoitUserDetails;
import it.unicam.doit.service.DoitAuthenticationProvider;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private final static Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private DoitAuthenticationProvider doitAuthenticationProvider;
	
	public static final String TOKEN_PREFIX = "Bearer";
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		LOG.debug(" -----> doFilterInternal");
		
		Enumeration<String> header = request.getHeaderNames();
		header.asIterator().forEachRemaining(LOG::debug);
		
		final String authorizationHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwt = null;
		
		if(authorizationHeader != null) {
			jwt = authorizationHeader.replace(TOKEN_PREFIX, "").trim();
			username = jwtUtil.extractUsername(jwt); // extractAllClaims validera' il token
			
			DoitUserDetails doitUserDetails = doitAuthenticationProvider.loadUserByUsername(username);
			LOG.debug(doitUserDetails.getUsername());
			
			UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(
			doitUserDetails, null, doitUserDetails.getAuthorities());
			LOG.debug(doitUserDetails.getUsername());
			
			upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			LOG.debug(doitUserDetails.getUsername());
			SecurityContextHolder.getContext().setAuthentication(upat);
			LOG.debug(" -----> security context loaded for " + username );
		}
		
		LOG.debug(" -----> primo filtro superato" );
		filterChain.doFilter(request, response);
	}

}

// permette al client di inviare il jwt tramite header
//response.addHeader("Access-Control-Allow-Origin", "http://localhost:8080");
//response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD");
//response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
//response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
//response.addHeader("Access-Control-Allow-Credentials", "true");
//response.addIntHeader("Access-Control-Max-Age", 10);

/*
		@Override
	protected boolean shouldNotFilter(HttpServletRequest req)
	  throws ServletException {
	    StringBuffer path = req.getRequestURL();
	    String status = req.getMethod();
	    
	    LOG.debug(path.toString());
	    LOG.debug(status);
	    
	    if(status.equals("POST")) {
	    	if(path.equals("/api/auth/login")) return true;
	    	if(path.equals("/api/auth/signUpEnte")) return true;
	    }
	    
	    if(status.equals("GET")) {
	    	if(path.equals("/api/progetti/storefront")) return true;
	    }
	    
	    return false;
	}
	
*/

/*
	Enumeration<String> attributeNames = req.getAttributeNames();
    attributeNames.asIterator().forEachRemaining(LOG::debug);
    Enumeration<String> headers = req.getHeaderNames();
    headers.asIterator().forEachRemaining(LOG::debug);
    Enumeration<String> params = req.getParameterNames();
    params.asIterator().forEachRemaining(LOG::debug);
    String contextPath = req.getContextPath();   // /mywebapp
    LOG.debug(contextPath);
    String servletPath = req.getServletPath();   // /servlet/MyServlet
    LOG.debug(servletPath);
    String pathInfo = req.getPathInfo();         // /a/b;c=123
    LOG.debug(pathInfo);
    String queryString = req.getQueryString();   // d=789
    LOG.debug(queryString);
*/