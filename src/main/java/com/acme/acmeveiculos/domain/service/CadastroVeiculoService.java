/**
 * Classe resposável pelas regras de negócio do cadastro dos veículos
 * 
 * @author Daniel Holer
 */
package com.acme.acmeveiculos.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.acmeveiculos.domain.exception.DomainException;
import com.acme.acmeveiculos.domain.model.TipoVeiculo;
import com.acme.acmeveiculos.domain.model.Veiculo;
import com.acme.acmeveiculos.domain.repository.TipoVeiculoRepository;
import com.acme.acmeveiculos.domain.repository.VeiculoRepository;

@Service
public class CadastroVeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private TipoVeiculoRepository tipoVeiculoRepository;

	
	public Veiculo salvar(Veiculo veiculo) {
		
		Veiculo veiculoExistente = veiculoRepository.findByPlaca(veiculo.getPlaca());
		
		//valida se já existe um veículo cadastrado com a mesma placa
		if (veiculoExistente != null && !veiculo.equals(veiculoExistente)) {
			throw new DomainException("Já existe um veículo cadastrado com esta placa.");
		}
				
		//valida se o tipo de veículo existe
		TipoVeiculo	tipoVeiculo = tipoVeiculoRepository.findById(veiculo.getTipoVeiculo().getId())
					.orElseThrow(() -> new DomainException("Tipo de Veículo não encontrado"));
		
		veiculo.setTipoVeiculo(tipoVeiculo);
		
		return veiculoRepository.save(veiculo);
	}
	
	public void excluir(Long id) {
		veiculoRepository.deleteById(id);
	}
	
}
