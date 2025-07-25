package com.devgeda.fx.controllers;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/views/MainView.fxml")
public class MainController implements Initializable {

	@FXML
	public Label labelTitle;
	@FXML
	public Label perfilData;
	@FXML
	private BorderPane inicialPagina;

	private String titulo = "S&M";

	private final FxWeaver fxWeaver;

	public MainController(FxWeaver fxWeaver) {
		this.fxWeaver = fxWeaver;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		labelTitle.setText(titulo);

		perfilData.setText(LocalDate.now().toString() + " - Usuário.");

		carregarTabelaBaixoEstoque();

	}

	public void mostrarAdicionarPeca_Action() {
		carregarPagina(AddController.class);
		labelTitle.setText("S&M - " + AddController.getTitulo());
	}

	public void mostrarVisualizarPeca_Action() {
		carregarPagina(ViewController.class);
		labelTitle.setText("S&M - " + ViewController.getTitulo());
	}

	public void mostrarEditarPeca_Action() {
		carregarPagina(EditController.class);
		labelTitle.setText("S&M - " + EditController.getTitulo());
	}

	private void carregarPagina(Class<?> controller) {
		Node conteudo = fxWeaver.loadView(controller);
		inicialPagina.setCenter(conteudo);
	}

	private void carregarTabelaBaixoEstoque() {
		Node conteudo = fxWeaver.loadView(TabelaBaixoEstoqueController.class);
		inicialPagina.setRight(conteudo);

	}

}
