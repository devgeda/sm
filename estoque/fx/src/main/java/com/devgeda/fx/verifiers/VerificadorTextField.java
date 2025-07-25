package com.devgeda.fx.verifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class VerificadorTextField {

	public List<String> verificarCamposVazios(Map<String, TextField> campos) {
		List<String> vazios = new ArrayList<>();
		for (Map.Entry<String, TextField> entry : campos.entrySet()) {
			String nomeCampo = entry.getKey();
			TextField conteudo = entry.getValue();
			if (conteudo.getText() == null || conteudo.getText().trim().isEmpty()) {
				vazios.add(nomeCampo);
			}

		}
		return vazios;

	}

	public List<String> verificarLabelVazio(Map<String, Label> label) {
		List<String> vazios = new ArrayList<>();
		for (Entry<String, Label> entry : label.entrySet()) {
			String nomeLabel = entry.getKey();
			Label conteudo = entry.getValue();
			if (conteudo.getText() == null || conteudo.getText().trim().isEmpty()) {
				vazios.add(nomeLabel);
			}

		}
		return vazios;

	}

	public static void limparCampos(TextField[] campos) {
		for (TextField campo : campos) {
			campo.clear();

		}
	}

	public static void verificarCampo(TextField... campos) {
		for (TextField campo : campos) {
			verificarCampo(campo);
			campo.textProperty().addListener((n, o, s) -> verificarCampo(campo));
		}

	}

	public static void verificarCampo(TextField campo) {
		if (campo.getText().isBlank()) {
			campo.setStyle("-fx-border-color: red;");
			campo.setStyle("-fx-border-color: red; -fx-prompt-text-fill: red;");
			campo.setPromptText("Insira uma informação válida.");

		} else {
			campo.setStyle("");
			campo.setPromptText("");
		}

	}

}