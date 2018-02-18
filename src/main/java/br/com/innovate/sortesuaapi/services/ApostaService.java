package br.com.innovate.sortesuaapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.innovate.sortesuaapi.models.Aposta;
import br.com.innovate.sortesuaapi.repositories.ApostaRepository;

@Service
public class ApostaService {
	
	@Autowired
	private ApostaRepository apostaRepository;
	
	public List<Aposta> listar(){
		return apostaRepository.findAll();
	}

	public void salvar(Aposta aposta) {
		apostaRepository.save(aposta);		
	}

	public Aposta buscarPorLoteriaENumero(Long tipoId, String numero) {
		return apostaRepository.buscarPorLoteriaENumero(tipoId, numero);
	}
}
