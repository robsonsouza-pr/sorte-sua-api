package br.com.innovate.sortesuaapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.innovate.sortesuaapi.dtos.LoteriaDto;
import br.com.innovate.sortesuaapi.models.Loteria;
import br.com.innovate.sortesuaapi.rsponse.Response;
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

	private LoteriaDto converterParaDto(Loteria item) {
		LoteriaDto loteriaDto = new LoteriaDto();
		loteriaDto.setId(item.getId());
		loteriaDto.setNome(item.getNome());
		return loteriaDto;
	}

}
