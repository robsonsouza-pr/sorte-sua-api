package br.com.innovate.sortesuaapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.innovate.sortesuaapi.models.Resultado;
import br.com.innovate.sortesuaapi.utils.DezenaUtils;

public class ResultadoDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message="A loteria não pode ser vazia")
	private String loteria;
	
	@NotNull(message = "O número do sorteio não pode ser vazio")
	private String numeroSorteio;
	
	@NotEmpty(message = "As dezenas não podem ser vazias")
	@Length(min = 1, max = 100,
	message = "O número do concurso deve conter entre 1 e 100 caracteres.")
	private String dezenas;
	
	public ResultadoDto() {}
	
	public ResultadoDto(Resultado item) {
		this.id = item.getId();
		this.loteria = item.getSorteio().getLoteria().getEnum().toString();
		this.numeroSorteio = item.getSorteio().getNumero();
		this.dezenas = DezenaUtils.getDezenasFormatadas(item.getDezenas());
	}

	public Long getId() {
		return id;
	}
		
	public String getLoteria() {
		return loteria;
	}

	public void setLoteria(String loteria) {
		this.loteria = loteria;
	}

	public void setId(Long id) {
		this.id = id;
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
