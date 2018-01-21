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

import br.com.innovate.sortesuaapi.dtos.SorteioDto;
import br.com.innovate.sortesuaapi.models.Sorteio;
import br.com.innovate.sortesuaapi.rsponse.Response;
import br.com.innovate.sortesuaapi.services.SorteioService;

@RestController
@RequestMapping("/api/sorteios")
@CrossOrigin(origins = "*")
public class SorteioController {
	
	@Autowired
	private SorteioService sorteioService;
	
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
		sorteios.stream().forEach(item -> sorteiosDto.add(new SorteioDto(item)));
		response.setData(sorteiosDto);;
		return ResponseEntity.ok(response);
	}

}
