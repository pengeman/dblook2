package org.peng.note;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class NoteDia {
    public static void main(String[] args) {
        NoteFX.main(args);
    }
    public void main(){
        // todo 打开记事本
        Parent root = null;
        javafx.stage.Stage dbsourceStage = new Stage();

        URL url = this.getClass().getResource("note.fxml");
        try {
            root = FXMLLoader.load(url);
            dbsourceStage.setTitle("记事本");
            dbsourceStage.setScene(new Scene(root));
            dbsourceStage.initModality(Modality.WINDOW_MODAL);
            dbsourceStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
