package org.peng.dblook2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class ADDNewConController implements Initializable {
    @FXML
    Button b_confirm,b_cancel;
    @FXML
    TextField text_project,text_driver,text_url,text_database,text_user,text_pwd;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        b_confirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // 确定按钮
                b_onfirm_clicked(actionEvent);
            }
        });
        b_cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                b_cancel_clicked(actionEvent);
            }
        });
    }



    private void b_onfirm_clicked(ActionEvent actionEvent) {
// 点击确定按钮
        String project = text_project.getText();
        String driver = text_driver.getText();
        String dburl = text_url.getText();
        String database = text_database.getText();
        String user = text_user.getText();
        String pwd = text_pwd.getText();
        try {
            URL dblookUrl = this.getClass().getClassLoader().getResource("dblook.properties");
            Properties pro = new Properties();
            pro.load(new java.io.FileInputStream(dblookUrl.getFile()));

            pro.setProperty("project",project);
            pro.setProperty(project + ".driver",driver);
            pro.setProperty(project+".url" ,dburl);
            pro.setProperty(project+".database" ,database);
            pro.setProperty(project+".user" , user);
            pro.setProperty(project+".pwd" , pwd);
            java.io.BufferedOutputStream bos = new java.io.BufferedOutputStream(new java.io.FileOutputStream("dblook.properties"));
            pro.store(bos,"kdkdkdkdkdkdk");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    private void b_cancel_clicked(ActionEvent actionEvent) {

    }
}
