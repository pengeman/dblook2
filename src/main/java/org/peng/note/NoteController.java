package org.peng.note;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class NoteController implements Initializable {
    @FXML
    public TextArea text1;
    @FXML
    public Button b1, b2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
// 打开对话框，读取文件内容
        String s = "";
        StringBuffer text = new StringBuffer();
        String filename = this.getClass().getResource("dblook.txt").getFile();
        System.out.println(filename);
        try {
            File file = new File(filename);
            FileReader read = new FileReader(file);
            BufferedReader in = new BufferedReader(read);
            while ((s = in.readLine()) != null) {
                String dd = new String(s.getBytes("utf-8"), "utf-8");
                text.append(dd + '\n');
            }
            text1.setText(text.toString());
            in.close();
            read.close();
        } catch (IOException e2) {
            System.out.println(e2);
        }

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                b2_close(actionEvent);
            }
        });

    }

    private void b2_close(ActionEvent actionEvent) {
        // todo 关闭当前界面,并保存数据
        saveText();
        System.out.println("关闭窗口");
        Stage stage = (Stage) this.b2.getScene().getWindow();
        stage.close();
    }

    private void saveText() {
        // todo 保存数据
        String filename = this.getClass().getResource("dblook.txt").getFile();
        System.out.println(filename);

        if (filename != null) {
            try {
                File file = new File(filename);
                FileWriter writer = new FileWriter(file);
                BufferedWriter out = new BufferedWriter(writer);
                String text = this.text1.getText();
                out.write(text, 0, text.length());

                out.close();
                writer.close();
            } catch (IOException e2) {
                System.out.println(e2);
            }

        }
    }
}
