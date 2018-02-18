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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.innovate.sortesuaapi.dtos.LoteriaDto;
import br.com.innovate.sortesuaapi.models.Loteria;
import br.com.innovate.sortesuaapi.response.Response;
import br.com.innovate.sortesuaapi.services.LoteriaService;

@RestController
@RequestMapping("/api/loterias")
@CrossOrigin(origins = "*")
public class LoteriaController {
	
	@Autowired
	private LoteriaService loteriaService;
	
	private static final Logger log = LoggerFactory.getLogger(LoteriaController.class);
	
	@GetMapping
	public ResponseEntity<Response<List<LoteriaDto>>> listar(){
		log.info("Listando as loterias");
		Response<List<LoteriaDto>> response = new Response<List<LoteriaDto>>();
		List<Loteria> loterias = loteriaService.findAll();
		if(loterias.isEmpty()) {
			log.info("Nenhuma loteria foi encontrada");
			response.getErrors().add("Nenhuma loteria encontrada");
			return ResponseEntity.badRequest().body(response);
		}
		
		List<LoteriaDto> loteriasDto = new ArrayList<>();
		loterias.stream().forEach(item -> loteriasDto.add(converterParaDto(item)));
		response.setData(loteriasDto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<LoteriaDto>> cadastrar(@Valid @RequestBody LoteriaDto loteriaDto, BindingResult result){
		log.info("Cadastrando uma loteria");
		Response<LoteriaDto> response = new Response<LoteriaDto>();
		validarLoteria(loteriaDto, result);
		if(result.hasErrors()) {
			log.error("Erro validando dados de cadastro de Loteria: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		Loteria loteria = converterParaEntidade(loteriaDto);
		loteriaService.cadastrar(loteria);
		response.setData(converterParaDto(loteria));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<Response<LoteriaDto>> atualizar(@Valid @RequestBody LoteriaDto loteriaDto, BindingResult result){
		log.info("Atualizando  uma loteria");
		Response<LoteriaDto> response = new Response<LoteriaDto>();
		validarLoteriaAtualizacao(loteriaDto, result);
		if(result.hasErrors()) {
			log.error("Erro validando dados de cadastro de Loteria: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		Loteria loteria = converterParaEntidade(loteriaDto);
		loteriaService.cadastrar(loteria);
		response.setData(converterParaDto(loteria));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping(value = "/remover/{id}")
	public ResponseEntity<Response<String>> deletar(@PathVariable("id") Long id){
		log.info("Removendo  loteria: ", id);
		Response<String> response = new Response<String>();
		Loteria loteria = loteriaService.find(id);
		if(loteria == null) {
			log.error("Não foi encontrada nenhuma loteria com o id:  {}", id);
			response.getErrors().add("Loteria não encontrada");
			return ResponseEntity.badRequest().body(response);
		}
		loteriaService.deletar(id);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping(value = "/detalhar/{id}")
	public ResponseEntity<Response<LoteriaDto>> detalhar(@PathVariable("id") Long id){
		log.info("Detalhando  loteria:{} ", id);
		Response<LoteriaDto> response = new Response<LoteriaDto>();
		Loteria loteria = loteriaService.find(id);
		if(loteria == null) {
			log.error("Não foi encontrada nenhuma loteria com o id:  {}", id);
			response.getErrors().add("Loteria não encontrada");
			return ResponseEntity.badRequest().body(response);
		}
		response.setData(this.converterParaDto(loteria));
		return ResponseEntity.ok(response);
	}


	private void validarLoteria(LoteriaDto loteriaDto, BindingResult result) {
		Loteria loteria = loteriaService.findByNome(loteriaDto.getNome());
		
		if (loteria != null) {
			result.addError(new ObjectError("Loteria", "Loteria já cadastrada."));
		}
		
	}
	
	private void validarLoteriaAtualizacao(LoteriaDto loteriaDto, BindingResult result) {
		Loteria loteria = loteriaService.find(loteriaDto.getId());
		
		if (loteria == null) {
			result.addError(new ObjectError("Loteria", "Loteria não cadastrada."));
		}
	}

	private LoteriaDto converterParaDto(Loteria item) {
		LoteriaDto loteriaDto = new LoteriaDto();
		loteriaDto.setId(item.getId());
		loteriaDto.setNome(item.getNome());
		loteriaDto.setDescricao(item.getDescricao());
		loteriaDto.setMaximo(item.getMaximo());
		loteriaDto.setMinimo(item.getMinimo());
		loteriaDto.setResultado(item.getResultado());
		return loteriaDto;
	}
	
	private Loteria converterParaEntidade(LoteriaDto loteriaDto) {
		Loteria loteria = new Loteria();
		loteria.setId(loteriaDto.getId() == null || loteriaDto.getId() ==0 ? null : loteriaDto.getId()  );
		loteria.setNome(loteriaDto.getNome());
		loteria.setDescricao(loteriaDto.getDescricao());
		loteria.setMaximo(loteriaDto.getMaximo());
		loteria.setMinimo(loteriaDto.getMinimo());
		loteria.setResultado(loteriaDto.getResulltado());
		return loteria;
	}

}
