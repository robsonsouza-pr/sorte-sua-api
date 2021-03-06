package br.com.innovate.sortesuaapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.innovate.sortesuaapi.models.Resultado;
import br.com.innovate.sortesuaapi.repositories.ResultadoRepository;

@Service
public class ResultadoService {
	
	@Autowired
	private ResultadoRepository resultadoRepository;
	
	public List<Resultado> listar(){
		return resultadoRepository.findAll();
	}

	public void salvar(Resultado resultado) {
		resultadoRepository.save(resultado);		
	}

	public Resultado buscarPorNumeroETipoSorteio(Long tipoId, String numero) {
		return resultadoRepository.pegaNoMeu(tipoId, numero);
	}
}
