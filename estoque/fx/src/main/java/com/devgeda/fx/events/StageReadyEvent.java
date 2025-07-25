package com.devgeda.fx.events;

import org.springframework.context.ApplicationEvent;

import javafx.stage.Stage;

public class StageReadyEvent extends ApplicationEvent {
	private static final long serialVersionUID = 291456505996670082L;

	public StageReadyEvent(Stage stage) {
		super(stage);
	}

	public Stage getStage() {
		return ((Stage) getSource());
	}

}