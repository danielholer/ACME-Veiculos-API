/**
 * Classe resposável pelas regras de negócio do cadastro dos tipos de veículos
 * 
 * @author Daniel Holer
 */
package com.acme.acmeveiculos.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.acmeveiculos.domain.exception.DomainException;
import com.acme.acmeveiculos.domain.model.TipoVeiculo;
import com.acme.acmeveiculos.domain.model.Veiculo;
import com.acme.acmeveiculos.domain.repository.TipoVeiculoRepository;
import com.acme.acmeveiculos.domain.repository.VeiculoRepository;

@Service
public class CadastroTipoVeiculoService {
	
	@Autowired
	private TipoVeiculoRepository tipoVeiculoRepository;

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	public TipoVeiculo salvar(TipoVeiculo tipoVeiculo) {
		TipoVeiculo tipoVeiculoExistente = tipoVeiculoRepository.findByTipo(tipoVeiculo.getTipo());
		
		//valida se já existe um tipo de veículo cadastrado com o mesmo nome
		if (tipoVeiculoExistente != null && !tipoVeiculo.equals(tipoVeiculoExistente)) {
			throw new DomainException("Já existe um tipo de veículo cadastrado com este nome.");
		}
		
		return tipoVeiculoRepository.save(tipoVeiculo);
	}
	
	public void excluir(Long id) {
		List<Veiculo> listaVeiculos = veiculoRepository.findByTipoVeiculo(tipoVeiculoRepository.findById(id).get());
		
		//valida se existe algum veículo cadastrado com o tipo de veículo que vai excluir
		if (!listaVeiculos.isEmpty()) {
			throw new DomainException("Para excluir este tipo de veículo, é preciso excluir todos os veículos deste tipo antes.");
		}
		
		tipoVeiculoRepository.deleteById(id);
	}
	
}
