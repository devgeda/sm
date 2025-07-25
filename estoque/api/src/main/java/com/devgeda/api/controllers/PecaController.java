package com.devgeda.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.devgeda.api.dtos.PecaDTO;
import com.devgeda.api.mappers.PecaMapper;
import com.devgeda.domain.models.Peca;
import com.devgeda.infrastructure.services.ServicePeca;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pecas")
public class PecaController {

	private final ServicePeca servicePeca;
	private final PecaMapper mapper;

	public PecaController(ServicePeca servicePeca, PecaMapper mapper) {
		this.servicePeca = servicePeca;
		this.mapper = mapper;
	}

	@GetMapping
	@ResponseStatus
	public ResponseEntity<PecaDTO> selecionarEntidade(@RequestBody @Valid PecaDTO dto) {
		Peca peca = mapper.toEntity(dto);
		return ResponseEntity.ok(mapper.toDto(peca));
	}

	@PostMapping
	public ResponseEntity<PecaDTO> inserirEntidade(@RequestBody @Valid PecaDTO dto) {
		Peca peca = mapper.toEntity(dto);
		Peca salvarPeca = servicePeca.inserirEntidade(peca);
		return ResponseEntity.ok(mapper.toDto(salvarPeca));
	};

//	@PutMapping("/{id}")
//	@ResponseStatus(code = HttpStatus.NO_CONTENT)
//	public ResponseEntity<Void> updateEntidade(@PathVariable("id") Integer id) {
//		Peca peca = mapper.toEntity(dto);
//		servicePeca.selecionarEntidadeById(id).map(existingPeca -> {
//			existingPeca.setId(peca.getId());
//			existingPeca.setDescricao(peca.getDescricao());
//			existingPeca.setCodigo(peca.getCodigo());
//			existingPeca.setCodigoAntigo(peca.getCodigoAntigo());
//			existingPeca.setQuantidade(peca.getQuantidade());
//			existingPeca.setAberto(peca.getAberto());
//			existingPeca.setFechado(peca.getFechado());
//			existingPeca.setColuna(peca.getColuna());
//			existingPeca.setPrateleira(peca.getPrateleira());
//			existingPeca.setLinha(peca.getLinha());
//			Peca existingPecaUpdated = servicePeca.inserirEntidade(existingPeca);
//			return ResponseEntity.ok(mapper.toDto(existingPecaUpdated));
//		});
//	return ResponseEntity.notFound().build();
//	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerEntidade(@PathVariable("id") @Valid Integer id) {
		servicePeca.removerEntidade(id);
		return ResponseEntity.notFound().build();
	}
}