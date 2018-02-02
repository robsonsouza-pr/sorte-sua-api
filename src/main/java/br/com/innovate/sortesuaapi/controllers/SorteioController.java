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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.innovate.sortesuaapi.dtos.SorteioDto;
import br.com.innovate.sortesuaapi.models.Loteria;
import br.com.innovate.sortesuaapi.models.Sorteio;
import br.com.innovate.sortesuaapi.response.Response;
import br.com.innovate.sortesuaapi.services.LoteriaService;
import br.com.innovate.sortesuaapi.services.SorteioService;
import br.com.innovate.sortesuaapi.utils.DateUtils;

@RestController
@RequestMapping("/api/sorteios")
@CrossOrigin(origins = "*")
public class SorteioController {
	
	@Autowired
	private SorteioService sorteioService;
	
	@Autowired
	private LoteriaService loteriaService;
		
	private static final Logger log = LoggerFactory.getLogger(LoteriaController.class);
	
	@GetMapping
	public ResponseEntity<Response<List<SorteioDto>>> listar(){
		log.info("Listando sorteios");
		Response<List<SorteioDto>> response = new Response<List<SorteioDto>>();
		
		List<Sorteio> sorteios = sorteioService.listar();
		
		if(sorteios.isEmpty()) {
			log.info("Não foram encontrados sorteios");
			response.getErrors().add("Não foram encontrados sorteios");
			return ResponseEntity.badRequest().body(response);
		}
		List<SorteioDto> sorteiosDto = new ArrayList<>();
		sorteios.stream().forEach(item -> sorteiosDto.add(new SorteioDto((item))));
		response.setData(sorteiosDto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<SorteioDto>> cadastrar(@Valid @RequestBody SorteioDto sorteio, BindingResult result) {
		Response<SorteioDto> response = new Response<SorteioDto>();
		validarSorteio(sorteio, result);
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		Sorteio s = this.converterParaEntidade(sorteio);
		sorteioService.cadastrar(s);
		response.setData(new SorteioDto(s));
		return ResponseEntity.ok(response);
	}
	
	
	@PutMapping
	public ResponseEntity<Response<SorteioDto>> alterar(@Valid @RequestBody SorteioDto sorteio, BindingResult result) {
		log.info("Alterando o sorteio {}", sorteio.getId());
		Response<SorteioDto> response = new Response<SorteioDto>();
		validarSorteio(sorteio, result);
		if (result.hasErrors()) {
			log.info("Erro ao tentar alterar um sorteio");
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		Sorteio s = this.converterParaEntidade(sorteio);
		sorteioService.cadastrar(s);
		response.setData(new SorteioDto(s));
		return ResponseEntity.ok(response);
	}


	private Sorteio converterParaEntidade(SorteioDto sorteioDto) {
		Sorteio sorteio = new Sorteio();
		Loteria loteria  = loteriaService.find(sorteioDto.getTipoId());
		sorteio.setTipo(loteria);
		sorteio.setNumero(sorteioDto.getNumero());
		sorteio.setPremiado("Sim".equals( sorteioDto.getSorteado())? true : false);
		sorteio.setDataSorteio(DateUtils.converterParaDate(sorteioDto.getDataSorteio()));
		
		return sorteio;
	}


	private void validarSorteio(SorteioDto sorteio, BindingResult result) {
		Loteria loteria = loteriaService.find(sorteio.getTipoId());
		if(loteria == null) {
			new ObjectError("Loteria", "Loteria não cadastrada.");
		}
	}
}
