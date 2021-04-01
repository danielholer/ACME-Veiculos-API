/**
 * Classe resposável pelo repositório da entidade Tipo de Veículo
 * 
 * @author Daniel Holer
 */
package com.acme.acmeveiculos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.acmeveiculos.domain.model.TipoVeiculo;

@Repository
public interface TipoVeiculoRepository extends JpaRepository<TipoVeiculo, Long> {

	TipoVeiculo findByTipo(String tipo);
	
}
