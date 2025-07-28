package com.devgeda.shared.dtos;

public class PecaDTO {
	private Integer id;
	private String descricao;
	private String codigo;
	private String codigoAntigo;
	private Integer quantidade;
	private Integer aberto;
	private Integer fechado;
	private Integer coluna;
	private String prateleira;
	private String linha;

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
