package com.devgeda.fx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.devgeda.fx.controllers.MainController;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.devgeda.fx.events.*;

import net.rgielen.fxweaver.core.FxWeaver;

@Component
public class EstoqueJavaFxApplicationStageInitializer implements ApplicationListener<StageReadyEvent> {

	private final FxWeaver fxWeaver;

	@Autowired
	public EstoqueJavaFxApplicationStageInitializer(FxWeaver fxWeaver) {
		this.fxWeaver = fxWeaver;
	}

	@Override
	public void onApplicationEvent(StageReadyEvent event) {
		Stage stage = event.getStage();
		BorderPane root = fxWeaver.loadView(MainController.class);
		Scene scene = new Scene(root, 1280, 720);
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.show();
	}
}
