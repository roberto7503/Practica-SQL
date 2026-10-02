package org.example.practicasql;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroEmpleadosApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistroEmpleadosApplication.class.getResource("biblioteca.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Registro Empleado");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}