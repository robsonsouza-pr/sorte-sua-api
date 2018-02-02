package br.com.innovate.sortesuaapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.innovate.sortesuaapi.models.Resultado;

public class ResultadoDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message="O sorteio não pode ser vazio")
	private Long idSorteio;
	
	@NotNull(message = "O número do sorteio não pode ser vazio")
	private String numeroSorteio;
	
	@NotEmpty(message = "As dezenas não podem ser vazias")
	@Length(min = 1, max = 100,
	message = "O número do concurso deve conter entre 1 e 100 caracteres.")
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
