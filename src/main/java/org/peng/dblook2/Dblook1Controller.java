package org.peng.dblook2;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Dblook1Controller implements Initializable {

    @FXML
    TableView table1;

    @FXML
    public void b_execute(ActionEvent actionEvent) {
        // todo 执行sql语句
        /* 新增1行*/
        Person per = new Person();
        per.setName1("1111").setName2("2222");

        this.table1.getItems().addAll(per);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TableColumn name1 = new TableColumn("name1");
        name1.setCellValueFactory(new PropertyValueFactory<>("name1"));

        TableColumn name2 = new TableColumn("name2");
        name2.setCellValueFactory(new PropertyValueFactory<>("name2"));

        table1.getColumns().clear();
        table1.getColumns().addAll(name1, name2);
    }
}
