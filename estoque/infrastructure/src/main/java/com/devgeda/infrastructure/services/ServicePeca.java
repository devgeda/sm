package com.devgeda.infrastructure.services;

import java.util.List;
import java.util.Optional;

import com.devgeda.domain.models.Peca;

public interface ServicePeca {

	public List<Peca> selecionarEntidade();
	
	public Optional<Peca> selecionarEntidadeById(Integer id);

	public Peca inserirEntidade(Peca entidade);
	
	public Peca updateEntidade(Integer id, Peca entidade);
	
	public void removerEntidade(Integer id);
}
