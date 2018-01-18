package br.com.innovate.sortesuaapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.innovate.sortesuaapi.models.Loteria;
import br.com.innovate.sortesuaapi.repositories.LoteriaRepository;

@Service
public class LoteriaService {
	
	@Autowired
	private LoteriaRepository loteriaRepository;

	public List<Loteria> findAll() {
		
		return loteriaRepository.findAll();
	}

	public Loteria findByNome(String nome) {
		return loteriaRepository.findByNome(nome);
	}

	public void cadastrar(Loteria loteria) {
		loteriaRepository.save(loteria);		
	}

}
