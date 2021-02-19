package it.unicam.doit.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import it.unicam.doit.model.DoitUserDetails;

@Component
public class JwtUtil {
	
	private final static Logger LOG = LoggerFactory.getLogger(JwtUtil.class);
		
	// TODO ottenere la chiave da un file protetto
	// private key ottenuta da una stringa casuale -> codificata in Base64 -> poi hash in HMAC-SHA-256
	private Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	public String generateToken(DoitUserDetails doitUserDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("username", doitUserDetails.getUsername());
		claims.put("roles", doitUserDetails.getAuthorities());
		// TODO get the claims from userDetails
		return createToken(claims, doitUserDetails.getUsername());
	}
	
	// Builder pattern
	// 1000 * 60 * 60 * 10 = 10 ore
	private String createToken(Map<String, Object> claims, String username) {
		return Jwts.builder()
				.setSubject(username)
				.setClaims(claims)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SECRET_KEY)
				.compact();
	}
	

	/*
		@return i dati decriptati del token
		
		la SECRET_KEY viene usata per verificare e 
		validare la firma del token ricevuto.
		Controlla anche la data di scadenza.
		
		@exception SignatureException (extends JwtException)
	 */
	private Claims extractAllClaims(String token) {
		Claims body = null;
		try {
			// ottenere tutti i claims + subject
			Jws<Claims> jwt = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
			body = jwt.getBody();	
		} catch (JwtException e) { throw e;}
		return body;
	}
	
	// senza aver selezionato niente, nei claims ci dovrebbe essere solo l'username e la data di scadenza
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	public String extractUsername(String token) {
		Claims claims = extractAllClaims(token);
		return (String) claims.get("username");
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
}
