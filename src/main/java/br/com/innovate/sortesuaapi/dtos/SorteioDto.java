package br.com.innovate.sortesuaapi.dtos;

import java.text.SimpleDateFormat;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.innovate.sortesuaapi.models.Sorteio;

public class SorteioDto {
	
	private Long id;
	@NotEmpty(message = "Número não pode  ser vazio.")
	@Length(min = 1, max = 10,
	message = "O número do concurso deve conter entre 1 e 10 caracteres.")
	private String numero;
	private String dataSorteio;
	
	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	
	public SorteioDto() {}
	
	public SorteioDto(Sorteio sorteio) {
		this.setId(sorteio.getId());
		//this.setDataSorteio(fmt.format(sorteio.getDataSorteio().getTime()));
		this.setNumero(sorteio.getNumero());
	}
	
	@NotNull
	private Long tipoId;
	private String sorteado;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getDataSorteio() {
		return dataSorteio;
	}
	public void setDataSorteio(String dataSorteio) {
		this.dataSorteio = dataSorteio;
	}
	public Long getTipoId() {
		return tipoId;
	}
	public void setTipoId(Long tipoId) {
		this.tipoId = tipoId;
	}
	public String getSorteado() {
		return sorteado;
	}
	public void setSorteado(String sorteado) {
		this.sorteado = sorteado;
	}
	
	
	
}
