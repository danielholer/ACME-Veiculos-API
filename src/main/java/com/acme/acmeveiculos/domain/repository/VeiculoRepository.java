/**
 * Classe resposável pelo repositório da entidade Veículo
 * 
 * @author Daniel Holer
 */
package com.acme.acmeveiculos.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acme.acmeveiculos.domain.model.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long>{
	
	Veiculo findByPlaca(String placa);
	
}
