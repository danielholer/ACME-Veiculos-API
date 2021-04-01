/**
 * Classe resposável pelo cadastro da entidade Veiculo
 * 
 * @author Daniel Holer
 */
package com.acme.acmeveiculos.api.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VeiculoInputModel {

	@NotBlank
	private String marca;
	
	@NotBlank
	private String modelo;
	
	@Valid
	@NotNull
	private TipoVeiculoIdInput tipoVeiculo;
	
	@NotBlank
	private String placa;
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public TipoVeiculoIdInput getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(TipoVeiculoIdInput tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
}
