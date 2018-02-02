package br.com.innovate.sortesuaapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.innovate.sortesuaapi.dtos.ResultadoDto;
import br.com.innovate.sortesuaapi.models.Resultado;
import br.com.innovate.sortesuaapi.response.Response;
import br.com.innovate.sortesuaapi.services.ResultadoService;

@RestController
@RequestMapping(value="/api/resultados")
@CrossOrigin(origins = "*")
public class ResultadoController {
	
	@Autowired
	private ResultadoService resultadoService;
	
	@GetMapping
	public ResponseEntity <Response<List<ResultadoDto>>> listar(){
		Response<List<ResultadoDto>> response = new Response<List<ResultadoDto>>();
		List<Resultado> resultados = resultadoService.listar();
		if(resultados.isEmpty()) {
			response.getErrors().add("Nenhum resultado encontrado");
			return ResponseEntity.badRequest().body(response);
		}
		List<ResultadoDto> dtos = new ArrayList<>();
		
		resultados.stream().forEach(item->dtos.add(new ResultadoDto(item)));
		response.setData(dtos);
		return ResponseEntity.ok(response);
	}

}
