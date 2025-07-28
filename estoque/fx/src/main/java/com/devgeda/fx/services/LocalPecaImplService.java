package com.devgeda.fx.services;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.devgeda.infrastructure.repositories.RepositorioPecasJpa;
import com.devgeda.shared.dtos.PecaDTO;

@Service
@Profile("dev-local")
public class LocalPecaImplService implements ServicePeca {
	private final RepositorioPecasJpa repositorio;

	public LocalPecaImplService(RepositorioPecasJpa repositorio) {
		this.repositorio = repositorio;
	}

	@Override
	public List<PecaDTO> selecionarEntidade() {
		//TERMINAR SERVICE DO FX, 	
		return repositorio.findAll();
	}
}
