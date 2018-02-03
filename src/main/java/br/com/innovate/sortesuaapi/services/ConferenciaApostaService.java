package br.com.innovate.sortesuaapi.services;

import org.springframework.stereotype.Service;

import br.com.innovate.sortesuaapi.dtos.ConferenciaApostaDto;
import br.com.innovate.sortesuaapi.models.Resultado;
import br.com.innovate.sortesuaapi.utils.DezenaUtils;

@Service
public class ConferenciaApostaService {

	public void conferir(ConferenciaApostaDto conferenciaApostaDto, Resultado resultado) {
		
		int acertos = DezenaUtils.contarAcertos(resultado, conferenciaApostaDto.getDezenas());
		conferenciaApostaDto.setAcertos(acertos);
	}

}
