package com.devgeda.infrastructure.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devgeda.domain.models.Peca;
import com.devgeda.infrastructure.repositories.RepositorioPecasJpa;

@Service
public class ServiceImplPeca implements ServicePeca {

	@Autowired
	private RepositorioPecasJpa repositorioPecasJpa;

	public ServiceImplPeca(RepositorioPecasJpa repositorioPecasJpa) {
		this.repositorioPecasJpa = repositorioPecasJpa;
	}

	@Override
	public List<Peca> selecionarEntidade() {
		return repositorioPecasJpa.findAll();
	}

	@Override
	public Optional<Peca> selecionarEntidadeById(Integer id) {
		return repositorioPecasJpa.findById(id);
	}

	@Override
	public Peca inserirEntidade(Peca entidade) {
		return this.repositorioPecasJpa.save(entidade);
	}

	@Override
	public Peca updateEntidade(Integer id, Peca entidade) {
		return this.repositorioPecasJpa.save(entidade);
	}

	@Override
	public void removerEntidade(Integer id) {
		this.repositorioPecasJpa.deleteById(id);
		
	}

}