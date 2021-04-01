/**
 * Classe resposável por enviar os dados da entidade Veículo para a API
 * 
 * @author Daniel Holer
 */
package com.acme.acmeveiculos.api.model;

public class VeiculoModel {

	private Long id;
	private String marca;
	private String modelo;
	private String tipo;
	private String placa;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
}
