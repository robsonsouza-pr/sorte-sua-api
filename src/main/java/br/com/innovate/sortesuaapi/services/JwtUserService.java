package br.com.innovate.sortesuaapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.innovate.sortesuaapi.models.Usuario;
import br.com.innovate.sortesuaapi.security.JwtUserFactory;

@Service
public class JwtUserService implements UserDetailsService{
	
	@Autowired
	private UsuarioService usuarioService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioService.buscarPorEmail(username);
		
		if (usuario.isPresent())
			return JwtUserFactory.create(usuario.get());
		else
			throw new UsernameNotFoundException("Email não encontrado");
	}

}
