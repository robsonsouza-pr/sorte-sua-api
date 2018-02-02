package br.com.innovate.sortesuaapi.dtos;

import java.io.Serializable;

import br.com.innovate.sortesuaapi.models.Resultado;

public class ResultadoDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long idSorteio;
	
	private String numeroSorteio;
	
	private String dezenas;
	
	public ResultadoDto() {}
	
	public ResultadoDto(Resultado item) {
		this.id = item.getId();
		this.idSorteio = item.getSorteio().getId();
		this.numeroSorteio = item.getSorteio().getNumero();
		this.dezenas = item.getDezenasFormatadas();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdSorteio() {
		return idSorteio;
	}
	public void setIdSorteio(Long idSorteio) {
		this.idSorteio = idSorteio;
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
}
