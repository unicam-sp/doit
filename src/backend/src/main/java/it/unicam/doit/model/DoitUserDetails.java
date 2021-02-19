package it.unicam.doit.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import it.unicam.doit.entity.DoitUser;

/*
	Questa classe e' l'istanza di un utente contenuta nel server
	mentre l'utente e' loggato.
	
	E' differente da DoitUser.
	Questa contiene tutte le possibili informazioni su un utente.
*/
public class DoitUserDetails implements UserDetails {
	
	private DoitUser doitUser;
	private Integer id;
	private String username;
	private String password;
	private String email;
	private List<GrantedAuthority> autorizzazioni;

	public DoitUserDetails(DoitUser du,  List<GrantedAuthority> grantList) {
		this.doitUser = du;
		this.id = du.getId();
		this.username = du.getUsername();
		this.password = du.getPassword();
		this.email = du.getEmail();
		this.autorizzazioni = grantList;
	}
	
	public DoitUser getDoitUser() {
		return this.doitUser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autorizzazioni;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}



	public Integer getId() {
		return id;
	}

}
