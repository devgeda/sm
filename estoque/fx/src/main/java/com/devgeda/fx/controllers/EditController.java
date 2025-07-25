package com.devgeda.fx.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.devgeda.domain.models.Peca;
import com.devgeda.fx.formatters.TextFieldFormatters;
import com.devgeda.fx.verifiers.VerificadorTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/views/EditView.fxml")
public class EditController implements Initializable {
	@FXML
	private TableView<Peca> resultadosPecas;
	@FXML
	private Label labelId;
	@FXML
	private TextField textfieldEditarDescricao;
	@FXML
	private TextField textfieldEditarCodigo;
	@FXML
	private TextField textfieldEditarCodigoAntigo;
	@FXML
	private TextField textfieldEditarQuantidade;
	@FXML
	private TextField textfieldEditarAberto;
	@FXML
	private TextField textfieldEditarFechado;
	@FXML
	private TextField textfieldEditarColuna;
	@FXML
	private TextField textfieldEditarPrateleira;
	@FXML
	private TextField textfieldEditarLinha;
	@FXML
	private MenuButton menuFiltros;
	@FXML
	private Button botaoPesquisar;
	@FXML
	private Button botaoRedefinir;
	@FXML
	private TextField textfieldPesquisaRapida;

	private static String titulo = "EDITAR PEÇA";

	public static String getTitulo() {
		return titulo;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		formatarTextFields();
		carregarMenuFiltros();

		for (TableColumn<?, ?> col : resultadosPecas.getColumns()) {
			col.setReorderable(false);
		}

		this.resultadosPecas.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		this.resultadosPecas.getSelectionModel().selectedItemProperty().addListener((n, o, novaPeca) -> {
			if (novaPeca != null) {
				labelId.setText(String.valueOf(novaPeca.getId()));
				textfieldEditarDescricao.setText(novaPeca.getDescricao());
				textfieldEditarCodigo.setText(novaPeca.getCodigo());
				textfieldEditarCodigoAntigo.setText(novaPeca.getCodigoAntigo());
				textfieldEditarQuantidade.setText(String.valueOf(novaPeca.getQuantidade()));
				textfieldEditarAberto.setText(String.valueOf(novaPeca.getAberto()));
				textfieldEditarFechado.setText(String.valueOf(novaPeca.getFechado()));
				textfieldEditarColuna.setText(String.valueOf(novaPeca.getColuna()));
				textfieldEditarPrateleira.setText(novaPeca.getPrateleira());
				textfieldEditarLinha.setText(novaPeca.getLinha());
			}
		});

		VerificadorTextField.verificarCampo(textfieldEditarQuantidade, textfieldEditarAberto, textfieldEditarFechado,
				textfieldEditarColuna);

	}

	private void formatarTextFields() {

		TextFieldFormatters textfieldDescricaoFormatter = new TextFieldFormatters();
		textfieldEditarDescricao.setTextFormatter(textfieldDescricaoFormatter.formatterLetra);

		TextFieldFormatters textfieldCodigoFormatter = new TextFieldFormatters();
		textfieldEditarCodigo.setTextFormatter(textfieldCodigoFormatter.formatterCodigo);

		TextFieldFormatters textfieldCodigoAntigoFormatter = new TextFieldFormatters();
		textfieldEditarCodigoAntigo.setTextFormatter(textfieldCodigoAntigoFormatter.formatterCodigo);

		TextFieldFormatters textfieldQuantidadeFormatter = new TextFieldFormatters();
		textfieldEditarQuantidade.setTextFormatter(textfieldQuantidadeFormatter.formatterNumerico);

		TextFieldFormatters textfieldAbertoFormatter = new TextFieldFormatters();
		textfieldEditarAberto.setTextFormatter(textfieldAbertoFormatter.formatterNumerico);

		TextFieldFormatters textfieldFechadoFormatter = new TextFieldFormatters();
		textfieldEditarFechado.setTextFormatter(textfieldFechadoFormatter.formatterNumerico);

		TextFieldFormatters textfieldColunaFormatter = new TextFieldFormatters();
		textfieldEditarColuna.setTextFormatter(textfieldColunaFormatter.formatterNumerico);

		TextFieldFormatters textfieldPrateleiraFormatter = new TextFieldFormatters();
		textfieldEditarPrateleira.setTextFormatter(textfieldPrateleiraFormatter.formatterLetra);

		TextFieldFormatters textfieldLinhaFormatter = new TextFieldFormatters();
		textfieldEditarLinha.setTextFormatter(textfieldLinhaFormatter.formatterLetra);

	}

	@FXML
	public void verificarTextFieldEditarQuantidade_Action() {
		VerificadorTextField.verificarCampo(textfieldEditarQuantidade);
	}

	@FXML
	public void verificarTextFieldEditarAberto_Action() {
		VerificadorTextField.verificarCampo(textfieldEditarAberto);
	}

	@FXML
	public void verificarTextFieldEditarCodigoAntigo_Action() {
		VerificadorTextField.verificarCampo(textfieldEditarFechado);
	}

	@FXML
	public void verificarTextFieldEditarColuna_Action() {

		VerificadorTextField.verificarCampo(textfieldEditarColuna);
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

	@FXML
	public void pesquisarPeca_Action() throws FileNotFoundException, IOException {
//		try {
//			String pesquisa = textfieldPesquisaRapida.getText().toUpperCase();
//			RepositorioEstoque<Peca> repositoryPecas = new RepositorioPecasJpa();
//			List<Peca> pecas = repositoryPecas.selecionarEntidade();
//			List<Peca> pecasFiltradas = new ArrayList<Peca>();
//			Stream<Peca> streamPecas = pecas.stream();
//			Stream<Peca> streamPecasFiltradas = streamPecas.filter(pec -> {
//				if (menuFiltros.getText().contains("Descrição")) {
//					return pec.getDescricao().contains(pesquisa);
//				}
//				if (menuFiltros.getText().contains("Código")) {
//					return pec.getCodigo().contains(pesquisa);
//				}
//				if (menuFiltros.getText().contains("Código Antigo")) {
//					return pec.getCodigoAntigo().contains(pesquisa);
//				}
//				if (menuFiltros.getText().contains("Prateleira")) {
//					return pec.getPrateleira().contains(pesquisa);
//				}
//				if (menuFiltros.getText().contains("Linha")) {
//					return pec.getLinha().contains(pesquisa);
//				}
//				if (menuFiltros.getText().isEmpty()) {
//					return pec.getDescricao().contains(pesquisa);
//				}
//				if (!menuFiltros.getText().contains("\\b(Descrição|Código(?: Antigo)?|Prateleira|Linha)\\b")) {
//					return pec.getDescricao().contains(pesquisa);
//				}
//				return false;
//			});
//			streamPecasFiltradas.forEach((pec) -> pecasFiltradas.add(pec));
//			ObservableList<Peca> pecasObservableList = FXCollections.observableArrayList(pecasFiltradas);
//			this.resultadosPecas.setItems(pecasObservableList);
//		} catch (SQLException e) {
//			Alert exceptionAlert = new Alert(AlertType.ERROR);
//			exceptionAlert.setContentText(e.getMessage());
//			exceptionAlert.showAndWait();
//		}

	}

	private void carregarTabelaPecas() throws FileNotFoundException, IOException {
//		try {
//			RepositorioEstoque<Peca> repositoryPecas = new RepositorioPecasJpa();
//			List<Peca> pecas = repositoryPecas.selecionarEntidade();
//			ObservableList<Peca> pecasObservableList = FXCollections.observableArrayList(pecas);
//			this.resultadosPecas.getItems().setAll(pecasObservableList);
//
//		} catch (SQLException e) {
//			Alert exceptionAlert = new Alert(AlertType.ERROR);
//			exceptionAlert.setContentText(e.getMessage());
//			exceptionAlert.showAndWait();
//
//		}
	}

	@FXML
	public void limparTextFields_Action() {
		VerificadorTextField.limparCampos(new TextField[] { textfieldEditarDescricao, textfieldEditarCodigo,
				textfieldEditarCodigoAntigo, textfieldEditarQuantidade, textfieldEditarAberto, textfieldEditarFechado,
				textfieldEditarColuna, textfieldEditarPrateleira, textfieldEditarLinha });
	}

	@FXML
	public void redefinir_Action() throws FileNotFoundException, IOException {
		carregarTabelaPecas();
	}

	@FXML
	public void cancelar_Action() throws FileNotFoundException, IOException {
		limparTextFields_Action();
	}

	@FXML
	public void remover_Action() throws FileNotFoundException, IOException, SQLException {
		Map<String, Label> camposVerificados = new LinkedHashMap<>();
		camposVerificados.put("Id", labelId);
		VerificadorTextField textFieldVerifier = new VerificadorTextField();
		List<String> camposVazios = textFieldVerifier.verificarLabelVazio(camposVerificados);

		try {
			Peca peca = resultadosPecas.getSelectionModel().getSelectedItem();
			if (peca != null) {
				if (!camposVazios.isEmpty()) {
					String msg = String.join(", ", camposVazios);

					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Falha ao remover a peça!");
					alert.setHeaderText("A peça não foi removida.");
					alert.setContentText("Os seguintes campos estão vazios: " + msg);
					alert.showAndWait();
					return;

				}

			}

			Alert confirmarSalvar = new Alert(AlertType.CONFIRMATION);
			confirmarSalvar.setTitle("REMOVER PEÇA");
			confirmarSalvar
					.setHeaderText(String.format("Você deseja prosseguir e remover a peça: %s?", peca.getDescricao()));
			Optional<ButtonType> reConf = confirmarSalvar.showAndWait();
			if (reConf.isPresent() && reConf.get() == ButtonType.OK) {

				carregarTabelaPecas();
			}
		} catch (NumberFormatException e) {
			if (!camposVazios.isEmpty()) {
				String msg = String.join(", ", camposVazios);

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Falha ao remover a peça!");
				alert.setHeaderText("A peça não foi removida.");
				alert.setContentText("Os seguintes campos estão vazios: " + msg);
				alert.showAndWait();
				return;

			}

		}

	}

	@FXML
	public void atualizar_Action() throws SQLException, IOException {
		Map<String, TextField> camposVerificados = new LinkedHashMap<>();
		camposVerificados.put("Descrição", textfieldEditarDescricao);
		camposVerificados.put("Código", textfieldEditarCodigo);
		camposVerificados.put("Código Antigo", textfieldEditarCodigoAntigo);
		camposVerificados.put("Quantidade", textfieldEditarQuantidade);
		camposVerificados.put("Aberto", textfieldEditarAberto);
		camposVerificados.put("Fechado", textfieldEditarFechado);
		camposVerificados.put("Coluna", textfieldEditarColuna);
		camposVerificados.put("Prateleira", textfieldEditarPrateleira);
		camposVerificados.put("Linha", textfieldEditarLinha);
		VerificadorTextField textFieldVerifier = new VerificadorTextField();
		List<String> camposVazios = textFieldVerifier.verificarCamposVazios(camposVerificados);

		try {
			Peca peca = new Peca();
			peca.setId(Integer.parseInt(labelId.getText()));
			peca.setDescricao(textfieldEditarDescricao.getText());
			peca.setCodigo(textfieldEditarCodigo.getText());
			peca.setCodigoAntigo(textfieldEditarCodigoAntigo.getText());
			peca.setQuantidade(Integer.parseInt(textfieldEditarQuantidade.getText()));
			peca.setAberto(Integer.parseInt(textfieldEditarAberto.getText()));
			peca.setFechado(Integer.parseInt(textfieldEditarFechado.getText()));
			peca.setColuna(Integer.parseInt(textfieldEditarColuna.getText()));
			peca.setPrateleira(textfieldEditarPrateleira.getText());
			peca.setLinha(textfieldEditarLinha.getText());
			if (peca != null) {
				if (!camposVazios.isEmpty()) {
					String msg = String.join(", ", camposVazios);

					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Falha ao atualizar a peça!");
					alert.setHeaderText("A peça não foi atualizada.");
					alert.setContentText("Os seguintes campos estão vazios: " + msg);
					alert.showAndWait();
					return;

				}

			}

			Alert confirmarSalvar = new Alert(AlertType.CONFIRMATION);
			confirmarSalvar.setTitle("ATUALIZAR PEÇA");
			confirmarSalvar.setHeaderText(
					String.format("Você deseja prosseguir e atualizar a peça: %s?", peca.getDescricao()));
			Optional<ButtonType> reConf = confirmarSalvar.showAndWait();
			if (reConf.isPresent() && reConf.get() == ButtonType.OK) {
				carregarTabelaPecas();
			}
		} catch (NumberFormatException e) {
			if (!camposVazios.isEmpty()) {
				String msg = String.join(", ", camposVazios);

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Falha ao atualizar a peça!");
				alert.setHeaderText("A peça não foi atualizada.");
				alert.setContentText("Os seguintes campos estão vazios: " + msg);
				alert.showAndWait();
				return;

			}

		}

	}

}
