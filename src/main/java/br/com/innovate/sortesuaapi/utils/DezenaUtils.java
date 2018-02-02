package br.com.innovate.sortesuaapi.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.innovate.sortesuaapi.models.Dezena;

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

}
