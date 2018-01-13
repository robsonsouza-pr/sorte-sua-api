package br.com.innovate.sortesuaapi.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SenhaUtils {
	
	public String gerarSenha(String senha) {
		
		BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
		return senha.isEmpty() ? senha : enconder.encode(senha);
		
	}
	
	public boolean isValid(String senha, String senhaCriptografada) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(senha, senhaCriptografada);
	}

}
