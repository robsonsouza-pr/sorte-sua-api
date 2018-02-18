package br.com.innovate.sortesuaapi.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import br.com.innovate.sortesuaapi.dtos.ApostaDto;
import br.com.innovate.sortesuaapi.models.Aposta;
import br.com.innovate.sortesuaapi.models.Dezena;
import br.com.innovate.sortesuaapi.models.Loteria;
import br.com.innovate.sortesuaapi.models.Sorteio;
import br.com.innovate.sortesuaapi.response.Response;
import br.com.innovate.sortesuaapi.services.ApostaService;
import br.com.innovate.sortesuaapi.services.SorteioService;
import br.com.innovate.sortesuaapi.utils.DezenaUtils;

@RestController
@RequestMapping(value="/api/apostas")
@CrossOrigin(origins = "*")
public class ApostaController {
	
	@Autowired
	private ApostaService apostaService;
	
	@Autowired
	private SorteioService sorteioService;
	
	private static final Logger log = LoggerFactory.getLogger(LoteriaController.class);
	
	@GetMapping
	public ResponseEntity <Response<List<ApostaDto>>> listar(){
		log.info("Listando apostas");
		Response<List<ApostaDto>> response = new Response<>();
		List<Aposta> resultados = apostaService.listar();
		if(resultados.isEmpty()) {
			log.info("Erro ao listar as  apostas");
			response.getErrors().add("Nenhum resultado encontrado");
			return ResponseEntity.badRequest().body(response);
		}
		List<ApostaDto> dtos = new ArrayList<>();
		
		resultados.stream().forEach(item->dtos.add(new ApostaDto(item)));
		response.setData(dtos);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<ApostaDto>> cadastrar(@Valid @RequestBody ApostaDto apostaDto, BindingResult result){
		Response<ApostaDto> response = new Response<>();
		Aposta aposta = validar(apostaDto, result);
		if(result.hasErrors()) {
			log.info("Foram encontrados erros durante a validação da aposta");
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		apostaService.salvar(aposta);
		response.setData(new ApostaDto(aposta));
		return ResponseEntity.ok(response);
		
	}

	private Aposta validar(ApostaDto apostaDto, BindingResult result) {
	 Aposta aposta = new Aposta();
	 
	 if(apostaDto.getId() != null) {
		 aposta.setId(apostaDto.getId());
	 }
	 
	 Sorteio sorteio = sorteioService.findByIdAndNumero(apostaDto.getIdSorteio(), apostaDto.getNumeroSorteio());
	 if(sorteio == null) {		 
		 result.addError(new ObjectError("Sorteio", "Não foi encontrado nenhum sorteio para esse id e número"));
	 }
	 aposta.setSorteio(sorteio);
	 
	 List<Dezena> dezenas = DezenaUtils.converterParaDezenas(apostaDto.getDezenas());
	 if(dezenas.isEmpty()) {
		 result.addError(new ObjectError("Dezena", "Não foram encontradas dezenas válidas para a aposta"));
	 }else if(! isQuantidadeValida(dezenas.size(), sorteio.getLoteria())) {
		 result.addError(new ObjectError("Dezena", "A quantidade de dezenas excedeu a quantidade máxima para o tipo de sorteio"));
	 }
	 aposta.setDezenas(dezenas);
		return aposta;
	}

	private boolean isQuantidadeValida(int size, Loteria loteria) {
		
		return size >=loteria.getMinimo() && size <=loteria.getMaximo();
	}
}