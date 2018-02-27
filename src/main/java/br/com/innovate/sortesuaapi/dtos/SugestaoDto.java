package br.com.innovate.sortesuaapi.dtos;

import java.util.List;

public class SugestaoDto {

	private String jogoAleatorio;
	
	private String jogoMaisSorteados;
	
	List<RelatorioDto> relatorios;

	public String getJogoAleatorio() {
		return jogoAleatorio;
	}

	public void setJogoAleatorio(String jogoAleatorio) {
		this.jogoAleatorio = jogoAleatorio;
	}

	public List<RelatorioDto> getRelatorios() {
		return relatorios;
	}

	public void setRelatorios(List<RelatorioDto> relatorios) {
		this.relatorios = relatorios;
	}

	public String getJogoMaisSorteados() {
		return jogoMaisSorteados;
	}

	public void setJogoMaisSorteados(String jogoMaisSorteados) {
		this.jogoMaisSorteados = jogoMaisSorteados;
	}
}
