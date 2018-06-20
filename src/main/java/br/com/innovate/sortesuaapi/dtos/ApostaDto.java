package br.com.innovate.sortesuaapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.innovate.sortesuaapi.models.Aposta;
import br.com.innovate.sortesuaapi.utils.DezenaUtils;

public class ApostaDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull(message="O sorteio não pode ser vazio")
	private String loteria;
	
	@NotNull(message = "O número do sorteio não pode ser vazio")
	private String numeroSorteio;
	
	@NotEmpty(message = "As dezenas não podem ser vazias")
	@Length(min = 1, max = 100,
	message = "O número do concurso deve conter entre 1 e 100 caracteres.")
	private String dezenas;
	
	public ApostaDto() {}
	
	public ApostaDto(Aposta item) {
		this.id = item.getId();
		this.loteria = item.getSorteio().getLoteria().getNome();
		this.numeroSorteio = item.getSorteio().getNumero();
		this.dezenas = DezenaUtils.getDezenasFormatadas(item.getDezenas());
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLoteria() {
		return loteria;
	}

	public void setLoteria(String loteria) {
		this.loteria = loteria;
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
