package org.peng.dblook2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class ADDNewConController implements Initializable {
    org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger();
    @FXML
    Button b_confirm, b_cancel;
    @FXML
    TextField text_project, text_driver, text_url, text_database, text_user, text_pwd;

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
        String dblook_properties = Common.dblook_conf;
        this.writeProperties(new File(dblook_properties));
//        String project = text_project.getText();
//        String driver = text_driver.getText();
//        String dburl = text_url.getText();
//        String database = text_database.getText();
//        String user = text_user.getText();
//        String pwd = text_pwd.getText();
//        try {
//            String dblook_properties = Common.dblook_conf;
//            URL dblookUrl = this.getClass().getClassLoader().getResource(dblook_properties);
//            Properties pro = new Properties();
//            pro.load(new java.io.FileInputStream(dblookUrl.getFile()));
//
//            pro.setProperty("project",project);
//            pro.setProperty(project + ".driver",driver);
//            pro.setProperty(project+".url" ,dburl);
//            pro.setProperty(project+".database" ,database);
//            pro.setProperty(project+".username" , user);
//            pro.setProperty(project+".password" , pwd);
//            java.io.BufferedOutputStream bos = new java.io.BufferedOutputStream(new java.io.FileOutputStream(dblook_properties));
//            pro.store(bos,"kdkdkdkdkdkdk");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

    }

    private void b_cancel_clicked(ActionEvent actionEvent) {

    }

    private void writeProperties(File proFile) {
        String project = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(proFile));
            InputStreamReader isRead = new InputStreamReader(bis);
            BufferedReader br = new BufferedReader(isRead);
            String strprject = br.readLine();
            if (strprject != null) {
                project = strprject;
            } else {
                project = "";
            }
//    strprject != null ? project = strprject : project = "";
            project = project + text_project.getText();
            String driver = text_driver.getText();
            String dburl = text_url.getText();
            String database = text_database.getText();
            String user = text_user.getText();
            String pwd = text_pwd.getText();

            java.io.BufferedOutputStream bo = new java.io.BufferedOutputStream(new java.io.FileOutputStream(proFile));
            java.io.OutputStreamWriter oswrite = new OutputStreamWriter(bo);

            //strprject = br.readLine();
            do {
                strprject = br.readLine();
                oswrite.write(strprject);
            } while (strprject != null);

            String projectfile = "project = " + project + "\n" +
                    project + ".driver = " + driver + "\n" +
                    project + ".url = " + dburl + "\n" +
                    project + ".database = " + database + "\n" +
                    project + ".username = " + user + "\n" +
                    project + ".password = " + pwd + "\n" +
                    project + ".characterEncoding =  utf-8 \n" +
                    project + ".InitialSize = 1 \n" +
                    project + ".MaxActive= 1 \n" +
                    project + ".MaxWait = 1 \n" +
                    project + ".MinIdle = 1 \n";

            oswrite.write(projectfile);
            oswrite.close();
            bo.close();
            isRead.close();
            bis.close();

        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
