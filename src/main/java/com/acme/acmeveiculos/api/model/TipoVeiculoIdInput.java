/**
 * Classe resposável pelo cadastro do id tipoVeiculo da entidade Veiculo
 * 
 * @author Daniel Holer
 */
package com.acme.acmeveiculos.api.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class TipoVeiculoIdInput {

	@Valid
	@NotNull
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
