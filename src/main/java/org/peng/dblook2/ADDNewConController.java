package org.peng.dblook2;

import fileHelp.FileHelp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.peng.dblook2.util.Common;
import org.peng.dblook2.util.Device;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class ADDNewConController implements Initializable {
    org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger();
    @FXML
    Label smi;
    @FXML
    Button b_confirm, b_cancel;
    @FXML
    TextField text_project, text_driver, text_url, text_database, text_user, text_pwd;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        smi.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //笑脸被鼠标点击
                smi_clicked(mouseEvent);
            }
        });
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

    private void smi_clicked(MouseEvent mouseEvent) {
        String project = text_project.getText();
        String[] devices = Device.project.get(project);
        if (devices != null) {
            String driver = devices[0];
            String url = devices[1];
            this.text_driver.setText(driver);
            this.text_url.setText(url);
        }
    }


    private void b_onfirm_clicked(ActionEvent actionEvent) {
        // 点击确定按钮
        String dblook_properties = Common.dblook_conf;
        this.writeProperties(new File(dblook_properties));
        javax.swing.JOptionPane.showMessageDialog(null,"新增连接项完成");
        this.b_cancel_clicked(actionEvent);
    }

    private void b_cancel_clicked(ActionEvent actionEvent) {
        System.out.println("关闭窗口");
        Stage stage = (Stage) this.b_cancel.getScene().getWindow();
        stage.close();
    }

    private void writeProperties(File proFile) {
        String project2 = null;
        StringBuffer propertiesContent = new StringBuffer();
        FileHelp fileHelp = new FileHelp();
        propertiesContent = fileHelp.read(proFile); //  读出原书properties文件的内容
        int indexn = propertiesContent.indexOf("\n");
        String project1 = propertiesContent.substring(0, indexn); // project = mysql,qq,ww
        project1 = project1.substring(project1.indexOf("=")+1).trim(); // 取出原project的内容

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
