package com.devgeda.fx.formatters;

import javafx.scene.control.TextFormatter;

public class TextFieldFormatters {

	public TextFormatter<String> formatterNumerico = new TextFormatter<>(alteracao -> {
		String newText = alteracao.getControlNewText();
		return newText.matches("\\d*") ? alteracao : null;
	});

	public TextFormatter<String> formatterLetra = new TextFormatter<>(alteracao -> {
		String newText = alteracao.getControlNewText().toUpperCase();

		if (newText.matches("[A-Z\s\\d,./-]*")) {
			alteracao.setText(alteracao.getText().toUpperCase());
			return alteracao;
		}
		return null;
	});
	
	public TextFormatter<String> formatterCodigo = new TextFormatter<>(alteracao -> {
		String newText = alteracao.getControlNewText().toUpperCase();

		if (newText.matches("[A-Z\\d-]*")) {
			alteracao.setText(alteracao.getText().toUpperCase());
			return alteracao;
		}
		return null;
	});

}
