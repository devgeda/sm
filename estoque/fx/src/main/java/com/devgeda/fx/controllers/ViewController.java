package com.devgeda.fx.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.devgeda.fx.services.LocalPecaImplService;
import com.devgeda.shared.dtos.PecaDTO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/views/VisualizarView.fxml")
public class ViewController implements Initializable {
	@Autowired
	private LocalPecaImplService pecaService;
	@FXML
	private TableView<PecaDTO> tabelaPecas;
	@FXML
	private MenuButton menuFiltros;
	@FXML
	private Button botaoPesquisar;
	@FXML
	private Button botaoRedefinir;
	@FXML
	private TextField textfieldPesquisar;

	private static String titulo = "VISUALIZAR PEÇAS";

	public static String getTitulo() {
		return titulo;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		for (TableColumn<?, ?> col : tabelaPecas.getColumns()) {
			col.setReorderable(false);
		}

		this.tabelaPecas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		carregarTabelaPecas();
		carregarMenuFiltros();
	}

	private void carregarMenuFiltros() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();

		MenuItem mItemDescricao = new MenuItem("Descrição");
		menuItems.add(mItemDescricao);
		MenuItem mItemCodigo = new MenuItem("Código");
		menuItems.add(mItemCodigo);
		MenuItem mItemCodigoAntigo = new MenuItem("Código Antigo");
		menuItems.add(mItemCodigoAntigo);
		MenuItem mItemPrateleira = new MenuItem("Prateleira");
		menuItems.add(mItemPrateleira);
		MenuItem mItemLinha = new MenuItem("Linha");
		menuItems.add(mItemLinha);
		ObservableList<MenuItem> menuItemsObservableList = FXCollections.observableArrayList(menuItems);
		menuFiltros.getItems().addAll(menuItemsObservableList);

		mItemDescricao.setOnAction(event -> menuFiltros.setText("Filtro: Descrição"));
		mItemCodigo.setOnAction(event -> menuFiltros.setText("Filtro: Código"));
		mItemCodigoAntigo.setOnAction(event -> menuFiltros.setText("Filtro: Código Antigo"));
		mItemPrateleira.setOnAction(event -> menuFiltros.setText("Filtro: Prateleira"));
		mItemLinha.setOnAction(event -> menuFiltros.setText("Filtro: Linha"));
	}

	public void pesquisarPeca_Action() {

		String pesquisa = textfieldPesquisar.getText().toUpperCase();
		List<PecaDTO> pecas = pecaService.selecionarEntidade();
		List<PecaDTO> pecasFiltradas = new ArrayList<PecaDTO>();
		Stream<PecaDTO> streamPecas = pecas.stream();
		Stream<PecaDTO> streamPecasFiltradas = streamPecas.filter(pec -> {
			if (menuFiltros.getText().contains("Descrição")) {
				return pec.getDescricao().contains(pesquisa);
			}
			if (menuFiltros.getText().contains("Código")) {
				return pec.getCodigo().contains(pesquisa);
			}
			if (menuFiltros.getText().contains("Código Antigo")) {
				return pec.getCodigoAntigo().contains(pesquisa);
			}
			if (menuFiltros.getText().contains("Prateleira")) {
				return pec.getPrateleira().contains(pesquisa);
			}
			if (menuFiltros.getText().contains("Linha")) {
				return pec.getLinha().contains(pesquisa);
			}
			if (!menuFiltros.getText().contains("\\b(Descrição|Código(?: Antigo)?|Prateleira|Linha)\\b")) {
				return pec.getDescricao().contains(pesquisa);
			}

			return false;
		});
		streamPecasFiltradas.forEach((pec) -> pecasFiltradas.add(pec));
		ObservableList<PecaDTO> pecasObservableList = FXCollections.observableArrayList(pecasFiltradas);
		this.tabelaPecas.getItems().setAll(pecasObservableList);

	}

	public void redefinir_Action() throws FileNotFoundException, IOException {
		carregarTabelaPecas();
	}

	private void carregarTabelaPecas() {

		List<PecaDTO> pecas = pecaService.selecionarEntidade();
		ObservableList<PecaDTO> pecasObservableList = FXCollections.observableArrayList(pecas);
		this.tabelaPecas.getItems().setAll(pecasObservableList);

	}

}
