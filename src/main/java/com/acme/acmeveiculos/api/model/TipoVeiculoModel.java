/**
 * Classe resposável por enviar os dados da entidade Tipo de Veículo para a API
 * 
 * @author Daniel Holer
 */
package com.acme.acmeveiculos.api.model;

public class TipoVeiculoModel {

	private Long id;
	private String tipo;
	private String descricao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
