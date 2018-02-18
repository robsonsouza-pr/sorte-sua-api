package br.com.innovate.sortesuaapi.dtos;

import javax.validation.constraints.NotNull;

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
	
	@NotNull(message="O mínimo de dezenas não pode ser vazio")
	private int minimo;
	@NotNull(message="O máximo de dezenas não pode ser vazio")
	private int maximo;
	@NotNull(message="A quantidade de dezenas de um resultado não pode ser vazia")
	private int resultado;
	
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

	public int getMinimo() {
		return minimo;
	}

	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	public int getMaximo() {
		return maximo;
	}

	public void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	public int getResulltado() {
		return resultado;
	}

	public void setResultado(int resulltado) {
		this.resultado = resulltado;
	}	
}
