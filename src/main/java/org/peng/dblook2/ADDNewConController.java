package org.peng.dblook2;

import fileHelp.FileHelp;
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
        String project2 = null;
        StringBuffer propertiesContent = new StringBuffer();
        FileHelp fileHelp = new FileHelp();
        propertiesContent = fileHelp.read(proFile); //  读出原书properties文件的内容
        int indexn = propertiesContent.indexOf("\n");
        String project1 = propertiesContent.substring(0, indexn); // project = mysql,qq,ww
        project1 = project1.substring(project1.indexOf("=")).trim(); // 取出原project的内容

        // 得到新连接项数据
        project2 = text_project.getText();
        String driver = text_driver.getText();
        String dburl = text_url.getText();
        String database = text_database.getText();
        String user = text_user.getText();
        String pwd = text_pwd.getText();

        StringBuffer propertiesContent2 = new StringBuffer();
        project1 = project1 + "," + project2;
        //propertiesContent2.append("project = " + project + "\n");
        propertiesContent2.append(project2 + ".driver = " + driver + "\n");
        propertiesContent2.append(project2 + ".url = " + dburl + "\n");
        propertiesContent2.append(project2 + ".database = " + database + "\n");
        propertiesContent2.append(project2 + ".username = " + user + "\n");
        propertiesContent2.append(project2 + ".password = " + pwd + "\n");
        propertiesContent2.append(project2 + ".characterEncoding =  utf-8 \n");
        propertiesContent2.append(project2 + ".InitialSize = 1 \n");
        propertiesContent2.append(project2 + ".MaxActive= 1 \n");
        propertiesContent2.append(project2 + ".MaxWait = 1 \n");
        propertiesContent2.append(project2 + ".MinIdle = 1 \n");

        // 将原properties中的第一行的project项修改
        propertiesContent = propertiesContent.delete(0, indexn).insert(0, "project = " + project1);

        // 将数据写入属性文件
        fileHelp.write(proFile, propertiesContent.append(propertiesContent2));
    }
}
