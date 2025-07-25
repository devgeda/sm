package com.devgeda.api.dtos;

import jakarta.validation.constraints.NotNull;

public class PecaDTO {
	private Integer id;
	private String descricao;
	private String codigo;
	private String codigoAntigo;
	@NotNull
	private Integer quantidade;
	@NotNull
	private Integer aberto;
	@NotNull
	private Integer fechado;
	@NotNull
	private Integer coluna;
	private String prateleira;
	private String linha;

	public PecaDTO() {
	}
	
	public PecaDTO(Integer id, String descricao, String codigo, String codigoAntigo, @NotNull Integer quantidade,
			@NotNull Integer aberto, @NotNull Integer fechado, @NotNull Integer coluna, String prateleira,
			String linha) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.codigo = codigo;
		this.codigoAntigo = codigoAntigo;
		this.quantidade = quantidade;
		this.aberto = aberto;
		this.fechado = fechado;
		this.coluna = coluna;
		this.prateleira = prateleira;
		this.linha = linha;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoAntigo() {
		return codigoAntigo;
	}

	public void setCodigoAntigo(String codigoAntigo) {
		this.codigoAntigo = codigoAntigo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getAberto() {
		return aberto;
	}

	public void setAberto(Integer aberto) {
		this.aberto = aberto;
	}

	public Integer getFechado() {
		return fechado;
	}

	public void setFechado(Integer fechado) {
		this.fechado = fechado;
	}

	public Integer getColuna() {
		return coluna;
	}

	public void setColuna(Integer coluna) {
		this.coluna = coluna;
	}

	public String getPrateleira() {
		return prateleira;
	}

	public void setPrateleira(String prateleira) {
		this.prateleira = prateleira;
	}

	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}
}