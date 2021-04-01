/**
 * Classe responsável pelo controller da entidade Veículo
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

import com.acme.acmeveiculos.api.model.VeiculoInputModel;
import com.acme.acmeveiculos.api.model.VeiculoModel;
import com.acme.acmeveiculos.domain.model.Veiculo;
import com.acme.acmeveiculos.domain.repository.VeiculoRepository;
import com.acme.acmeveiculos.domain.service.CadastroVeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	@Autowired
	private CadastroVeiculoService cadastroVeiculo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<VeiculoModel> listar() {
		return this.toModel(veiculoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<VeiculoModel> buscar(@PathVariable Long id) {
		
		Optional<Veiculo> veiculo = veiculoRepository.findById(id);
				
		if (veiculo.isPresent()) {
			VeiculoModel veiculoModel = this.toModel(veiculo.get());
			return ResponseEntity.ok(veiculoModel);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VeiculoModel adicionar(@Valid @RequestBody VeiculoInputModel veiculoInputModel) {
		Veiculo veiculo = this.toEntity(veiculoInputModel);
		return this.toModel(cadastroVeiculo.salvar(veiculo));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<VeiculoModel> atualizar(@Valid @PathVariable Long id, 
			@Valid @RequestBody VeiculoInputModel veiculoInputModel) {
		
		if(!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		Veiculo veiculo = this.toEntity(veiculoInputModel);
		veiculo.setId(id);
		veiculo = cadastroVeiculo.salvar(veiculo);
		
		return ResponseEntity.ok(this.toModel(veiculo));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		
		if(!veiculoRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroVeiculo.excluir(id);
		
		return ResponseEntity.noContent().build();	
	}
	
	private VeiculoModel toModel(Veiculo veiculo) {
		return modelMapper.map(veiculo, VeiculoModel.class);
	}
	
	private List<VeiculoModel> toModel(List<Veiculo> veiculos) {
		return veiculos.stream()
				.map(veiculo -> toModel(veiculo))
				.collect(Collectors.toList());
	}
	
	private Veiculo toEntity(VeiculoInputModel veiculoInputModel) {
		return modelMapper.map(veiculoInputModel, Veiculo.class);
	}
}
