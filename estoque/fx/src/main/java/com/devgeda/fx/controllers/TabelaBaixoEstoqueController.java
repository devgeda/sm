package com.devgeda.fx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.devgeda.domain.models.Peca;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/views/TabelaBaixoEstoque.fxml")
public class TabelaBaixoEstoqueController implements Initializable {
	
	@FXML
	public TableView<Peca> tabelaBaixoEstoque;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		for (TableColumn<?, ?> col : tabelaBaixoEstoque.getColumns()) {
			col.setReorderable(false);
		}
	}
}
