package br.com.innovate.sortesuaapi.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class LoteriaDto {
	
	private Long id;
	
	@NotEmpty(message = "Nome não pode ser vazio.")
	@Length(min = 5, max = 100,
	message = "O Nome deve conter entre 5 e 100 caracteres.")
	private String nome;
	
	@Length(min = 5, max = 100,
	message = "A descriçãp deve conter entre 5 e 100 caracteres.")
	private String descricao;
	
	private int digitos;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getDigitos() {
		return digitos;
	}

	public void setDigitos(int digitos) {
		this.digitos = digitos;
	}
	
}
