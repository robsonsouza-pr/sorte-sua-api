package br.com.innovate.sortesuaapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ConferenciaApostaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Long idLoteria;

	@NotNull
	private String numeroSorteio;

	private String dezenas;

	private int acertos;

	public ConferenciaApostaDto() {
	}

	public Long getIdLoteria() {
		return idLoteria;
	}

	public void setIdLoteria(Long idLoteria) {
		this.idLoteria = idLoteria;
	}

	public String getNumeroSorteio() {
		return numeroSorteio;
	}

	public void setNumeroSorteio(String numeroSorteio) {
		this.numeroSorteio = numeroSorteio;
	}

	public String getDezenas() {
		return dezenas;
	}

	public void setDezenas(String dezenas) {
		this.dezenas = dezenas;
	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}
}
