package br.com.innovate.sortesuaapi.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import br.com.innovate.sortesuaapi.models.Usuario;

public class JwtUserFactory {
	
	private JwtUserFactory() {}
	
	public static JwtUser create(Usuario usuario) {
		
		return new JwtUser(usuario.getId(), usuario.getEmail(), usuario.getSenha(),
				mapToGrantedAuthorities(usuario.getPerfil()));
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(PerfilEnum perfil) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(perfil.toString()));
		return authorities;
	}

	
}
