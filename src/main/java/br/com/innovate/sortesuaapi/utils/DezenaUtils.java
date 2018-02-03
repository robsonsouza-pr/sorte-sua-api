package br.com.innovate.sortesuaapi.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.innovate.sortesuaapi.models.Dezena;
import br.com.innovate.sortesuaapi.models.Resultado;

public class DezenaUtils {

	public static List<Dezena> converterParaDezenas(String dezenas) {
		List<Dezena> retorno = new ArrayList<>();
		List<String> novasDezenas = Arrays.asList(dezenas.split(";"));

		novasDezenas.stream().forEach(item -> {
			if (StringUtils.isNumeric(item)) {
				Dezena dezena = new Dezena();
				Long id = Long.valueOf(item.trim());
				if (id == 0L) {
					dezena.setId(100L);
				} else {
					dezena.setId(id);
				}
				retorno.add(dezena);
			}
		});
		return retorno;
	}

	public static int contarAcertos(Resultado resultado, String dezenas) {
		int quantidade = 0;

		List<Dezena> dezenasApostadas = converterParaDezenas(dezenas);
		List<Dezena> coincidentes = dezenasApostadas.stream().filter(p ->
			resultado.getDezenas().contains(p)
		).collect(Collectors.toList());

		quantidade = coincidentes.size();
		return quantidade;
	}

	public static boolean isRangeApostaValido(int size, Long idLoteria) {
		boolean retorno = false;
		
		switch (idLoteria.intValue()) {
		case 1:
			retorno = size>=15 && size <=18;
			break;
		case 2:
			retorno = size>=6 && size<=15;
			break;
		default:
			retorno = false;
		}		
		return retorno;
	}

}
