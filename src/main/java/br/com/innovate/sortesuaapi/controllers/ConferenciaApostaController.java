package br.com.innovate.sortesuaapi.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.EnumUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.innovate.sortesuaapi.dtos.ConferenciaApostaDto;
import br.com.innovate.sortesuaapi.enums.LoteriaEnum;
import br.com.innovate.sortesuaapi.models.Dezena;
import br.com.innovate.sortesuaapi.models.Loteria;
import br.com.innovate.sortesuaapi.models.Resultado;
import br.com.innovate.sortesuaapi.response.Response;
import br.com.innovate.sortesuaapi.services.ConferenciaApostaService;
import br.com.innovate.sortesuaapi.services.LoteriaService;
import br.com.innovate.sortesuaapi.services.ResultadoService;
import br.com.innovate.sortesuaapi.utils.DezenaUtils;

@RestController
@RequestMapping(value = "api/conferir")
@CrossOrigin(origins = "*")
public class ConferenciaApostaController {

	@Autowired
	private ConferenciaApostaService conferenciaService;

	@Autowired
	private ResultadoService resultadoService;
	
	@Autowired
	private LoteriaService loteriaService;

	private static final Logger log = LoggerFactory.getLogger(LoteriaController.class);

	@PostMapping
	public ResponseEntity<Response<ConferenciaApostaDto>> conferir(
			@Valid @RequestBody ConferenciaApostaDto conferenciaApostaDto, BindingResult result) {
		log.info("Iniciando conferencia");
		Response<ConferenciaApostaDto> response = new Response<>();
		Resultado resultado = validarConferencia(conferenciaApostaDto, result);

		if (result.hasErrors()) {
			log.info("Erro ao validar confêrencia");
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		conferenciaService.conferir(conferenciaApostaDto, resultado);
		response.setData(conferenciaApostaDto);
		return ResponseEntity.ok(response);
	}

	private Resultado validarConferencia(ConferenciaApostaDto conferenciaApostaDto, BindingResult result) {
		Resultado resultado = null;

		if (EnumUtils.isValidEnum(LoteriaEnum.class, conferenciaApostaDto.getLoteria())) {
			Long idLoteria = LoteriaEnum.valueOf(conferenciaApostaDto.getLoteria()).getId();
			resultado = resultadoService.buscarPorNumeroETipoSorteio(idLoteria,
					conferenciaApostaDto.getNumeroSorteio());
			if (resultado == null) {
				result.addError(new ObjectError("Não foi enconrado resultado cadastrado para o sorteio {}",
						conferenciaApostaDto.getNumeroSorteio()));
				return resultado;
			}

			List<Dezena> dezenas = DezenaUtils.converterParaDezenas(conferenciaApostaDto.getDezenas());
			Loteria loteria = loteriaService.find(idLoteria);
			
			if (dezenas.isEmpty()) {
				result.addError(new ObjectError("Dezena", "Não foram encontradas dezenas válidas para o resultado"));
			} else if (!dezenas.isEmpty()
					&& !DezenaUtils.isRangeApostaValido(dezenas.size(), loteria)) {
				result.addError(new ObjectError("Dezena", "As dezenas apostadas não estão dentro do range permitido"));
			}
		} else {
			result.addError(new ObjectError("Loteria", "Loteria inválida."));
		}

		return resultado;
	}

}
