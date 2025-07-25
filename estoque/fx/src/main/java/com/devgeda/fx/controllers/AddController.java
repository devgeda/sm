package com.devgeda.fx.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.devgeda.domain.models.Peca;
import com.devgeda.fx.formatters.TextFieldFormatters;
import com.devgeda.fx.verifiers.VerificadorTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/views/AdicionarView.fxml")
public class AddController implements Initializable {
	@FXML
	private TextField textfieldDescricao;
	@FXML
	private TextField textfieldCodigo;
	@FXML
	private TextField textfieldCodigoAntigo;
	@FXML
	private TextField textfieldQuantidade;
	@FXML
	private TextField textfieldAberto;
	@FXML
	private TextField textfieldFechado;
	@FXML
	private TextField textfieldColuna;
	@FXML
	private TextField textfieldPrateleira;
	@FXML
	private TextField textfieldLinha;
	@FXML

	private static String titulo = "ADICIONAR PEÇA";

	public static String getTitulo() {
		return titulo;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		formatarTextFields();

		VerificadorTextField.verificarCampo(textfieldQuantidade, textfieldAberto, textfieldFechado, textfieldColuna);
	}

	private void formatarTextFields() {

		TextFieldFormatters textfieldDescricaoFormatter = new TextFieldFormatters();
		textfieldDescricao.setTextFormatter(textfieldDescricaoFormatter.formatterLetra);

		TextFieldFormatters textfieldCodigoFormatter = new TextFieldFormatters();
		textfieldCodigo.setTextFormatter(textfieldCodigoFormatter.formatterCodigo);

		TextFieldFormatters textfieldCodigoAntigoFormatter = new TextFieldFormatters();
		textfieldCodigoAntigo.setTextFormatter(textfieldCodigoAntigoFormatter.formatterCodigo);

		TextFieldFormatters textfieldQuantidadeFormatter = new TextFieldFormatters();
		textfieldQuantidade.setTextFormatter(textfieldQuantidadeFormatter.formatterNumerico);

		TextFieldFormatters textfieldAbertoFormatter = new TextFieldFormatters();
		textfieldAberto.setTextFormatter(textfieldAbertoFormatter.formatterNumerico);

		TextFieldFormatters textfieldFechadoFormatter = new TextFieldFormatters();
		textfieldFechado.setTextFormatter(textfieldFechadoFormatter.formatterNumerico);

		TextFieldFormatters textfieldColunaFormatter = new TextFieldFormatters();
		textfieldColuna.setTextFormatter(textfieldColunaFormatter.formatterNumerico);

		TextFieldFormatters textfieldPrateleiraFormatter = new TextFieldFormatters();
		textfieldPrateleira.setTextFormatter(textfieldPrateleiraFormatter.formatterLetra);

		TextFieldFormatters textfieldLinhaFormatter = new TextFieldFormatters();
		textfieldLinha.setTextFormatter(textfieldLinhaFormatter.formatterLetra);

	}

	@FXML
	public void limparTextFields_Action() {
		VerificadorTextField.limparCampos(
				new TextField[] { textfieldDescricao, textfieldCodigo, textfieldCodigoAntigo, textfieldQuantidade,
						textfieldAberto, textfieldFechado, textfieldColuna, textfieldPrateleira, textfieldLinha });
	}

	@FXML
	public void confirmarSalvar_Action() throws SQLException, IOException {
		Map<String, TextField> camposVerificados = new LinkedHashMap<>();
		camposVerificados.put("Descrição", textfieldDescricao);
		camposVerificados.put("Código", textfieldCodigo);
		camposVerificados.put("Código Antigo", textfieldCodigoAntigo);
		camposVerificados.put("Quantidade", textfieldQuantidade);
		camposVerificados.put("Aberto", textfieldAberto);
		camposVerificados.put("Fechado", textfieldFechado);
		camposVerificados.put("Coluna", textfieldColuna);
		camposVerificados.put("Prateleira", textfieldPrateleira);
		camposVerificados.put("Linha", textfieldLinha);

		VerificadorTextField textFieldVerifier = new VerificadorTextField();
		List<String> camposVazios = textFieldVerifier.verificarCamposVazios(camposVerificados);

		try {
			Peca peca = new Peca();
			peca.setDescricao(textfieldDescricao.getText());
			peca.setCodigo(textfieldCodigo.getText());
			peca.setCodigoAntigo(textfieldCodigoAntigo.getText());
			peca.setQuantidade(Integer.parseInt(textfieldQuantidade.getText()));
			peca.setAberto(Integer.parseInt(textfieldAberto.getText()));
			peca.setFechado(Integer.parseInt(textfieldFechado.getText()));
			peca.setColuna(Integer.parseInt(textfieldColuna.getText()));
			peca.setPrateleira(textfieldPrateleira.getText());
			peca.setLinha(textfieldLinha.getText());
			if (peca != null) {
				if (!camposVazios.isEmpty()) {
					String msg = String.join(", ", camposVazios);

					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Falha ao adicionar peça!");
					alert.setHeaderText("A peça não foi adicionada.");
					alert.setContentText("Os seguintes campos estão vazios: " + msg);
					alert.showAndWait();
					return;

				}

			}

			Alert confirmarSalvar = new Alert(AlertType.CONFIRMATION);
			confirmarSalvar.setTitle("ADICIONAR PEÇA");
			confirmarSalvar.setHeaderText("Você deseja prosseguir?");
			Optional<ButtonType> reConf = confirmarSalvar.showAndWait();
			if (reConf.isPresent() && reConf.get() == ButtonType.OK) {

			}
		} catch (NumberFormatException e) {
			if (!camposVazios.isEmpty()) {
				String msg = String.join(", ", camposVazios);

				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Falha ao adicionar peça!");
				alert.setHeaderText("A peça não foi adicionada.");
				alert.setContentText("Os seguintes campos estão vazios: " + msg);
				alert.showAndWait();
				return;

			}

		}

	}
}