package br.com.innovate.sortesuaapi.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//Essa classe é obrigatória, uma vez que o Spring depende de uma classe que implement UserDetail
//ou seja, uma classe que será utilizada para autenticar o usuario
public class JwtUser implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String username;
	private String password;
	private Collection <? extends GrantedAuthority> authorities;
	
	

	public JwtUser(Long id, String userName, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.username = userName;
		this.password = password;
		this.authorities = authorities;
	}
	
	
	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
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

}
