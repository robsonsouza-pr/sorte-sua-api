package br.com.innovate.sortesuaapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.innovate.sortesuaapi.models.Sorteio;
import br.com.innovate.sortesuaapi.repositories.SorteioRepository;

@Service
public class SorteioService {

	@Autowired
	private SorteioRepository sorteioRepository;
	
	public List<Sorteio> listar(){
		return sorteioRepository.findAll();
	}

	public void cadastrar(Sorteio sorteio) {
		sorteioRepository.save(sorteio);
	}

	public Sorteio findByLoteriaAndNumero(Long id, String numero) {
		
		return sorteioRepository.findByLoteriaIdAndNumero(id, numero);
	}
}
