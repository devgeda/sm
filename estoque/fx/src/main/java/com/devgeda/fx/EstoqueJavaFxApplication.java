package com.devgeda.fx;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.devgeda.api.EstoqueSpringbootApplication;
import com.devgeda.fx.events.StageReadyEvent;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class EstoqueJavaFxApplication extends Application {
	private ConfigurableApplicationContext applicationContext;

	public static void main(String[] args) {
		Application.launch(EstoqueJavaFxApplication.class, args);
	}

	@Override
	public void init() throws Exception {
		this.applicationContext = new SpringApplicationBuilder(EstoqueSpringbootApplication.class)
				.sources(EstoqueSpringbootApplication.class)
				.properties("spring.config.location=classpath:/config/")
				.run(getParameters().getRaw().toArray(new String[0]));
	}

	@Override
	public void start(Stage stage) throws Exception {
		applicationContext.publishEvent(new StageReadyEvent(stage));
	}

	@Override
	public void stop() throws Exception {
		this.applicationContext.close();
		Platform.exit();
	}

}
