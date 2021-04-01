/**
 * Classe resposável pelo cadastro da entidade Tipo Veiculo
 * 
 * @author Daniel Holer
 */
package com.acme.acmeveiculos.api.model;

import javax.validation.constraints.NotBlank;

public class TipoVeiculoInputModel {

	@NotBlank
	private String tipo;
	
	@NotBlank
	private String descricao;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
