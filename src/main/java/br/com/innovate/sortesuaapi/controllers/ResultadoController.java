package br.com.innovate.sortesuaapi.controllers;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.innovate.sortesuaapi.dtos.ResultadoDto;
import br.com.innovate.sortesuaapi.enums.LoteriaEnum;
import br.com.innovate.sortesuaapi.models.Dezena;
import br.com.innovate.sortesuaapi.models.Resultado;
import br.com.innovate.sortesuaapi.models.Sorteio;
import br.com.innovate.sortesuaapi.response.Response;
import br.com.innovate.sortesuaapi.services.ResultadoService;
import br.com.innovate.sortesuaapi.services.SorteioService;
import br.com.innovate.sortesuaapi.utils.DezenaUtils;

@RestController
@RequestMapping(value = "/api/resultados")
@CrossOrigin(origins = "*")
public class ResultadoController {

	@Autowired
	private ResultadoService resultadoService;

	@Autowired
	private SorteioService sorteioService;

	private static final Logger log = LoggerFactory.getLogger(LoteriaController.class);

	@GetMapping
	public ResponseEntity<Response<List<ResultadoDto>>> listar() {
		Response<List<ResultadoDto>> response = new Response<List<ResultadoDto>>();
		List<Resultado> resultados = resultadoService.listar();
		if (resultados.isEmpty()) {
			response.getErrors().add("Nenhum resultado encontrado");
			return ResponseEntity.badRequest().body(response);
		}
		List<ResultadoDto> dtos = new ArrayList<>();

		resultados.stream().forEach(item -> dtos.add(new ResultadoDto(item)));
		response.setData(dtos);
		return ResponseEntity.ok(response);
	}

	@PostMapping
	public ResponseEntity<Response<ResultadoDto>> cadastrar(@Valid @RequestBody ResultadoDto resultadoDto,
			BindingResult result) {
		Response<ResultadoDto> response = new Response<ResultadoDto>();
		Resultado resultado = validar(resultadoDto, result);
		if (result.hasErrors()) {
			log.info("Foram encontrados erros durante a validação do resultado");
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		resultadoService.salvar(resultado);
		response.setData(new ResultadoDto(resultado));
		return ResponseEntity.ok(response);

	}

	private Resultado validar(ResultadoDto resultadoDto, BindingResult result) {
		Resultado resultado = new Resultado();

		if (resultadoDto.getId() != null) {
			resultado.setId(resultadoDto.getId());
		}

		if (EnumUtils.isValidEnum(LoteriaEnum.class, resultadoDto.getLoteria())) {
			Long idLoteria = LoteriaEnum.valueOf(resultadoDto.getLoteria()).getId();
			Sorteio sorteio = sorteioService.findByLoteriaAndNumero(idLoteria, resultadoDto.getNumeroSorteio());
			if (sorteio == null) {
				result.addError(
						new ObjectError("Sorteio", "Não foi encontrado nenhum sorteio para essa loteria e número"));
			}
			resultado.setSorteio(sorteio);

			List<Dezena> dezenas = DezenaUtils.converterParaDezenas(resultadoDto.getDezenas());
			if (dezenas.isEmpty()) {
				result.addError(new ObjectError("Dezena", "Não foram encontradas dezenas válidas para o resultado"));
			} else if (!dezenas.isEmpty() && dezenas.size() != sorteio.getLoteria().getResultado()) {
				result.addError(new ObjectError("Dezena",
						"A quantidade de dezenas excedeu a quantidade máxima para o tipo de sorteio"));
			}
			
			resultado.setDezenas(dezenas);

		} else {
			result.addError(new ObjectError("Loteria", "Loteria inválida."));
		}

		return resultado;
	}

}
