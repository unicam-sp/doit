package it.unicam.doit.security;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import it.unicam.doit.service.DoitAuthenticationProvider;

/* 

	Classe per la configurazione di springSecurityFilterChain 

	Tipo di autenticazione: UsernamePasswordAuthentication
*/

@Configuration
@EnableWebSecurity
public class DoitConfiguration extends WebSecurityConfigurerAdapter {

	private final static Logger LOG = LoggerFactory.getLogger(DoitConfiguration.class);

	@Autowired
	private DoitAuthenticationProvider doitAuthenticationProvider;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	/* Authorization */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// by default uses a Bean by the name of corsConfigurationSource
		http.cors().and().csrf().disable()
		.authorizeRequests()
		// quando dal client viene fatta una richiesta che non rispetta le policy cors
		// viene inviato prima una richiesta di tipo OPTIONS che deve essere lasciata passare
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.antMatchers("/api/auth/login").permitAll()
		.anyRequest().authenticated();

		// impostazione per dire a Spring di non creare delle sessioni per gli utenti
		// questo perche' verranno usati i JWT
		// in altre parole, il SecurityContext viene impostato ad ogni richiesta su JwtAuthenticationFilter
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	/*
		Minimal set up should be
		
		Access-Control-Allow-Origin: *
		Access-Control-Allow-Methods: GET, POST, PUT, DELETE
		Access-Control-Allow-Headers: Authorization
	*/
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		LOG.debug("CORS settati da DoitConfiguration");
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:8080"));
		configuration.setAllowedMethods(Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS", "HEAD"));
		configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "X-Auth-Token", "Origin", "Content-Type", "Accept",
	            "Authorization", "Access-Control-Allow-Credentials", "Access-Control-Allow-Headers", "Access-Control-Allow-Methods",
	            "Access-Control-Allow-Origin", "Access-Control-Expose-Headers", "Access-Control-Max-Age",
	            "Access-Control-Request-Headers", "Access-Control-Request-Method", "Age", "Allow", "Alternates",
	            "Content-Range", "Content-Disposition", "Content-Description"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	/* Authentication */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		// autenticazione con utente trovato dal database
		auth.userDetailsService(doitAuthenticationProvider);
	}

	// se non viene annotato come bean non puo' essere collegato a un @Autowired
	// collegato su DoitUserController
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	// abilitare il salvataggio di password senza codifica
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}

// API solo autenticati
// e' di default grazie a super.configure(http)
// http.authorizeRequests().anyRequest().authenticated();

/*
 * 
 * 
 * // configurati sulla classe WebConfig http.cors(); // disable csrf do enable
 * POST and DELETE requests http.csrf().disable();
 * 
 * http.authorizeRequests(authReq -> authReq.antMatchers(HttpMethod.GET,
 * "/api/progetti/storefront").permitAll() .antMatchers(HttpMethod.POST,
 * "/api/auth/signUpPersona", "/api/auth/login", "/api/auth/signUpEnte")
 * .permitAll());
 * 
 * 
 * 
 * TO CHECK
 * 
 * PasswordEncoder encoder =
 * PasswordEncoderFactories.createDelegatingPasswordEncoder(); // utenticazione
 * con utenti in memoria auth.inMemoryAuthentication()
 * .withUser("user").password(encoder.encode("user")).roles("USER")
 * .and().withUser("admin").password(encoder.encode("admin")).roles("USER",
 * "ADMIN");
 * 
 * --------------------------------------------------------------------------
 * 
 * http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.
 * STATELESS).and()
 * 
 * requestCache().requestCache(new NullRequestCache()).and().
 * httpBasic().authenticationEntryPoint(restAuthEntryPoint).and().cors();
 * 
 * // salva il token di autenticazione http.authorizeRequests().and()
 * .rememberMe().tokenRepository(this.persistentTokenRepository())
 * .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
 * 
 * --------------------------------------------------------------------------
 * 
 * @Bean public PersistentTokenRepository persistentTokenRepository() {
 * JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
 * db.setDataSource(dataSource); return db; }
 * 
 * --------------------------------------------------------------------------
 * 
 * @Import(RestSecurityConfig.TokenAuthenticationProvider.class)// one of the
 * way to create spring bean
 * 
 */

/*
 * WebSecurity vs HttpSecurity
 * 
 * HttpSecurity -> ROLES -> gestisce le richieste dopo l'autenticazione
 */

/*
 * http.authorizeRequests(authReq -> authReq.antMatchers(HttpMethod.GET,
 * "/api/progetti/storefront") .permitAll() .antMatchers(HttpMethod.POST,
 * "/api/auth/signUpPersona", "/api/auth/login", "/api/auth/signUpEnte")
 * .permitAll());
 * 
 * 
 * @Override public void configure(WebSecurity web) throws Exception {
 * web.ignoring().antMatchers(HttpMethod.GET, "/api/progetti/storefront")
 * .antMatchers(HttpMethod.POST, "/api/auth/signUpPersona", "/api/auth/login",
 * "/api/auth/signUpEnte"); }
 */
