package br.com.innovate.sortesuaapi.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.innovate.sortesuaapi.dtos.RelatorioDto;
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
	
	public Set<Integer> sortearNumeros(int quantidade) {
		Set<Integer> numeros  = new  HashSet<>();
		Random random = new Random();
		do {
			Integer numero = random.ints(1, 25).findFirst().getAsInt();
			numeros.add(numero);
		}while(numeros.size()<quantidade);
		return numeros;
	}
	
	public List<RelatorioDto> findDezenasMaisSorteadas(Long idLoteria) {
		
		List<Object[]> relatorios = apostaRepository.countBySorteioId(idLoteria);
		
		List<RelatorioDto> dtos = convterParaRelatorioDto(relatorios);
		return dtos ;
		
	}

	private List<RelatorioDto> convterParaRelatorioDto(List<Object[]> relatorios) {
		 List<RelatorioDto> dtos = new  ArrayList<RelatorioDto>();
		 relatorios.stream().forEach(item-> {
		 			String valor = (String) item[0];
		 			BigInteger quantidade = (BigInteger) item[1];
		 			dtos.add(new RelatorioDto(valor, quantidade.longValue()));
		 });
		return dtos;
	}
}
