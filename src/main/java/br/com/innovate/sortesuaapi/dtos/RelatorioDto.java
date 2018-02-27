package br.com.innovate.sortesuaapi.dtos;

import java.io.Serializable;

public class RelatorioDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String valor;
	private long quantidade;
	
	public RelatorioDto(){}
	
	
	
	public RelatorioDto(String valor, long quantidade) {
		super();
		this.valor = valor;
		this.quantidade = quantidade;
	}



	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}
}
