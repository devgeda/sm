package com.devgeda.fx.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.devgeda.infrastructure.repositories.RepositorioPecasJpa;
import com.devgeda.shared.dtos.PecaDTO;

@Service
@Profile("dev-local")
public class LocalPecaImplService implements ServicePeca {

	@Autowired
	private final RepositorioPecasJpa repositorio;

	public LocalPecaImplService(RepositorioPecasJpa repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public List<PecaDTO> selecionarEntidade() {
		return repositorio.findAll().stream().map(peca -> {
			PecaDTO dto = new PecaDTO();
			dto.setDescricao(peca.getDescricao());
			dto.setCodigo(peca.getCodigo());
			dto.setCodigoAntigo(peca.getCodigoAntigo());
			dto.setQuantidade(peca.getQuantidade());
			dto.setAberto(peca.getAberto());
			dto.setFechado(peca.getFechado());
			dto.setColuna(peca.getColuna());
			dto.setPrateleira(peca.getPrateleira());
			dto.setLinha(peca.getLinha());
			return dto;
		}).toList();
	}
}
