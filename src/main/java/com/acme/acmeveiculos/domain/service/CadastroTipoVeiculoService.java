/**
 * Classe resposável pelas regras de negócio do cadastro dos tipos de veículos
 * 
 * @author Daniel Holer
 */
package com.acme.acmeveiculos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.acmeveiculos.domain.exception.DomainException;
import com.acme.acmeveiculos.domain.model.TipoVeiculo;
import com.acme.acmeveiculos.domain.repository.TipoVeiculoRepository;

@Service
public class CadastroTipoVeiculoService {
	
	@Autowired
	private TipoVeiculoRepository tipoVeiculoRepository;

	public TipoVeiculo salvar(TipoVeiculo tipoVeiculo) {
		TipoVeiculo tipoVeiculoExistente = tipoVeiculoRepository.findByTipo(tipoVeiculo.getTipo());
		
		//valida se já existe um tipo de veículo cadastrado com o mesmo nome
		if (tipoVeiculoExistente != null && !tipoVeiculo.equals(tipoVeiculoExistente)) {
			throw new DomainException("Já existe um tipo de veículo cadastrado com este nome.");
		}
		
		return tipoVeiculoRepository.save(tipoVeiculo);
	}
	
	public void excluir(Long id) {
		tipoVeiculoRepository.deleteById(id);
	}
	
}
