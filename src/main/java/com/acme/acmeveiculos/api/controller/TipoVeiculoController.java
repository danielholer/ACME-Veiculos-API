/**
 * Classe responsável pelo controller da entidade Tipo de Veículo
 * 
 * @author Daniel Holer
 */
package com.acme.acmeveiculos.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.acme.acmeveiculos.api.model.TipoVeiculoInputModel;
import com.acme.acmeveiculos.api.model.TipoVeiculoModel;
import com.acme.acmeveiculos.domain.model.TipoVeiculo;
import com.acme.acmeveiculos.domain.repository.TipoVeiculoRepository;
import com.acme.acmeveiculos.domain.service.CadastroTipoVeiculoService;

@RestController
@RequestMapping("/tipos-veiculos")
public class TipoVeiculoController {
	
	@Autowired
	private TipoVeiculoRepository tipoVeiculoRepository;
	
	@Autowired
	private CadastroTipoVeiculoService cadastroTipoVeiculo;
	
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping
	public List<TipoVeiculoModel> listar() {
		return this.toModel(tipoVeiculoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoVeiculoModel> buscar(@PathVariable Long id) {
		
		Optional<TipoVeiculo> tipoVeiculo = tipoVeiculoRepository.findById(id);
		
		if (tipoVeiculo.isPresent()) {
			TipoVeiculoModel tipoVeiculoModel = this.toModel(tipoVeiculo.get());
			return ResponseEntity.ok(tipoVeiculoModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public TipoVeiculoModel adicionar(@Valid @RequestBody TipoVeiculoInputModel tipoVeiculoInputModel) {
		TipoVeiculo tipoVeiculo = this.toEntity(tipoVeiculoInputModel);
		return this.toModel(cadastroTipoVeiculo.salvar(tipoVeiculo));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TipoVeiculoModel> atualizar(@Valid @PathVariable Long id, 
			@Valid @RequestBody TipoVeiculoInputModel tipoVeiculoInputModel) {
		
		if(!tipoVeiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		TipoVeiculo tipoVeiculo = this.toEntity(tipoVeiculoInputModel);
		tipoVeiculo.setId(id);
		tipoVeiculo = cadastroTipoVeiculo.salvar(tipoVeiculo);
		
		return ResponseEntity.ok(this.toModel(tipoVeiculo));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		
		if(!tipoVeiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroTipoVeiculo.excluir(id);
		
		return ResponseEntity.noContent().build();
		
	}

	private TipoVeiculoModel toModel(TipoVeiculo tipoVeiculo) {
		return modelMapper.map(tipoVeiculo, TipoVeiculoModel.class);
	}

	private List<TipoVeiculoModel> toModel(List<TipoVeiculo> tiposVeiculos) {
		return tiposVeiculos.stream()
				.map(tipoVeiculo -> toModel(tipoVeiculo))
				.collect(Collectors.toList());
	}
	
	private TipoVeiculo toEntity(TipoVeiculoInputModel tipoVeiculoInputModel) {
		return modelMapper.map(tipoVeiculoInputModel, TipoVeiculo.class);
	}

}
