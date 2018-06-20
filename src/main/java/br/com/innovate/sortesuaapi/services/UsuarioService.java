package br.com.innovate.sortesuaapi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.innovate.sortesuaapi.models.Usuario;
import br.com.innovate.sortesuaapi.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> buscarPorEmail(String email){
		return Optional.ofNullable(this.usuarioRepository.findByEmail(email));
	}

	
}
