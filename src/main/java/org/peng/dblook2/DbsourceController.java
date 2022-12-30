package org.peng.dblook2;

import dbhelp.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @param
 * @version 1.0
 * @Description
 * @Author pengweitao 2022/5/3 下午11:10
 * @exception
 * @return
 */

public class DbsourceController implements Initializable {

    private String curProject; // 当前选中的工程名称
    private String driver, url, db, username, pwd;

    @FXML
    private TextArea textArea;
    @FXML
    private Button b_cancel;
    @FXML
    private Button b_confirm;
    @FXML private Button b_addnew;
    @FXML
    private ListView listView;
    @FXML
    private SplitPane splitPane;
    @FXML
    private AnchorPane parentPane;

    private int dbsource_index;
    Properties pro = new Properties();

    public DbsourceController() {
        this(1);
    }

    public DbsourceController(int index) {
        // 根据数据源序号,设置数据源序号
        this.dbsource_index = index;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            String dblook_properties = Common.dblook_conf;
            //URL url2 = getClass().getClassLoader().getResource(dblook_properties);
            File dblookfile = new File(dblook_properties);

            pro.load(new FileInputStream(dblookfile));
if (pro.size() == 0){
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setContentText("连接项配置文件错误");
    alert.show();
    return;
}
            //得到数据源properties文件,并显示列表
            List<String> projectlist = this.getAllProject();
            ObservableList<String> dblist = FXCollections.observableArrayList();

            for (String db : projectlist) {
                dblist.add(db);
            }

            this.listView.setItems(dblist);
            this.textArea.setText("hahahah");

            this.listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    // listView item被点击
                    System.out.println("clicked...." + newValue);

                    DbsourceController.this.curProject = (String) newValue;

                    driver   = pro.getProperty(newValue + ".driver");
                    url      = pro.getProperty(newValue + ".url");
                    db       = pro.getProperty(newValue + ".database");
                    username = pro.getProperty(newValue + ".username");
                    pwd      = pro.getProperty(newValue + ".password");
                    textArea.setText("driver = " + driver + "\n" +
                            "url = " + url + "\n" +
                            "database = " + db + "\n" +
                            "username = " + username + "\n" +
                            "password = " + pwd);
                }
            });

            b_cancel.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    btnClose_Click();
                }
            });
            b_confirm.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    btnConfirm_click();
                }
            });
            b_addnew.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    // TODO 新增连接项
                    // 打开新对话框
                    try {
                        URL url = this.getClass().getResource("ADDNewCon.fxml");
                        Parent root = FXMLLoader.load(url);
                        Scene scene = new Scene(root);
                        Stage addNewConStage = new Stage();
                        addNewConStage.setScene(scene);
                        addNewConStage.setTitle("新增连接项");
                        addNewConStage.initModality(Modality.APPLICATION_MODAL);
                        addNewConStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            /*this.parentPane.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    btnClose_Click();
                }
            });*/
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    private void btnConfirm_click() {
        System.out.println("确定,链接数据库");
        // 链接数据库,创建数据库对象
        DBConnection con = new DBConnection(driver,url,username,pwd);
        dbhelp.DataBase dataBase = con.createDatabase();

        Common.dataBase = dataBase;

        Stage stage = (Stage) this.b_confirm.getScene().getWindow();
        stage.close();
    }

    private void btnClose_Click() {
        System.out.println("关闭窗口");
        Stage stage = (Stage) this.b_confirm.getScene().getWindow();
        stage.close();

    }

    private List getAllProject() {
        //String projects = dbhelpx.DBHelp.getInstanc().getProperties().getProperty("project");
        String projects = null;  // 所有的工程名称
        List prol = null;

        if (!pro.isEmpty()) {
            projects = pro.getProperty("project");
            String[] pros = projects.split(",");
            prol = new ArrayList();
            for (String prop : pros) {
                prol.add(prop);
            }
        }
        return prol;

    }

}