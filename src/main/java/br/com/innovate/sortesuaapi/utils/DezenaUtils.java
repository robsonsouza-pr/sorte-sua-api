package br.com.innovate.sortesuaapi.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.innovate.sortesuaapi.models.Dezena;
import br.com.innovate.sortesuaapi.models.Loteria;
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

	public static boolean isRangeApostaValido(int size, Loteria loteria) {
		return size >=loteria.getMinimo() && size <=loteria.getMaximo();
	}

	public static String getDezenasFormatadas(List<Dezena> dezenas) {
		StringBuilder dezenasFormatadas = new StringBuilder();
		if (dezenas != null && !dezenas.isEmpty()) {
			dezenas.sort((Dezena o1, Dezena o2) -> o1.getId().intValue() - o2.getId().intValue());
			dezenas.stream().forEach(item -> dezenasFormatadas.append(item.getValor() + " - "));
			return dezenasFormatadas.substring(0, dezenasFormatadas.lastIndexOf("-") - 1);
		}
		return dezenasFormatadas.toString();
	}

	public static String getDezenasFormatadas(Set<Integer> numeros) {
		StringBuilder dezenasFormatadas = new StringBuilder();
		if (numeros != null && !numeros.isEmpty()) {
			List<Integer> lista = new ArrayList<>(numeros);
			Collections.sort(lista);
			lista.stream().forEach(item -> dezenasFormatadas.append(item + " - "));
			return dezenasFormatadas.substring(0, dezenasFormatadas.lastIndexOf("-") - 1);
		}
		return dezenasFormatadas.toString();
	}

	public static String formatarListaValores(List<String> valores) {
		StringBuilder dezenasFormatadas = new StringBuilder();
		if (valores != null && !valores.isEmpty()) {
			Collections.sort(valores);
			valores.stream().forEach(item -> dezenasFormatadas.append(item + " - "));
			return dezenasFormatadas.substring(0, dezenasFormatadas.lastIndexOf("-") - 1);
		}
		return dezenasFormatadas.toString();
	}
}
