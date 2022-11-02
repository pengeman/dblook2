package org.peng.dblook2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class dblook extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(dblook.class.getResource("dblook_1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 740);
        stage.setTitle("DBLOOK!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}