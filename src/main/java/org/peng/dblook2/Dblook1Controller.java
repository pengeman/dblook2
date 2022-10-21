package org.peng.dblook2;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.SetChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

import static javafx.scene.input.KeyCode.F5;

public class Dblook1Controller implements Initializable {

    @FXML
    public ToolBar toolBar;
    @FXML
    public Button b_doSQL;
    @FXML
    public TextArea t_sql;
    @FXML
    public AnchorPane pane;
    @FXML  AnchorPane spane1, spane2;
    @FXML
    public SplitPane s_pane;
    @FXML
    TableView table1;



    @Override
@FXML    public void initialize(URL url, ResourceBundle resourceBundle) {
        // todo dblook主界面初始化
        this.pane.setPrefWidth(800);
        this.pane.setPrefHeight(600);

        this.toolBar.prefWidthProperty().bind(pane.widthProperty());
        this.s_pane.prefWidthProperty().bind(pane.widthProperty());
        this.s_pane.prefHeightProperty().bind(pane.heightProperty());
        this.t_sql.prefWidthProperty().bind(s_pane.widthProperty());
        this.table1.prefWidthProperty().bind(s_pane.widthProperty());

//        KeyCombination kc = new KeyCodeCombination(KeyCode.F5);
//        Mnemonic mnemonic = new Mnemonic(b_doSQL,kc);
//        this.pane.getScene().addMnemonic(mnemonic);



        // todo 设置事件动作
        this.pane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                var paneheight1 = spane1.getHeight();
                var paneheight2 = spane2.getHeight();

                t_sql.setPrefHeight(paneheight1);
                table1.setPrefHeight(paneheight2);

//                KeyCombination kc = new KeyCodeCombination(KeyCode.F5);
//                Mnemonic mnemonic = new Mnemonic(b_doSQL,kc);
//                Dblook1Controller.this.pane.getScene().addMnemonic(mnemonic);
            }
        });
        this.s_pane.getDividers().get(0).positionProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldv, Number newv) {
                //System.out.println(oldv + "   --   " + newv);
                double height = s_pane.getPrefHeight();
                t_sql.setPrefHeight(height * ((double) newv) );
                table1.setPrefHeight(height - height * ((double) newv) - 40);

            }
        });

        TableColumn name1 = new TableColumn("name1");
        name1.setCellValueFactory(new PropertyValueFactory<>("name1"));

        TableColumn name2 = new TableColumn("name2");
        name2.setCellValueFactory(new PropertyValueFactory<>("name2"));

        table1.getColumns().clear();
        table1.getColumns().addAll(name1, name2);
    }

    @FXML public void b_lookforjdbc(ActionEvent actionEvent) {
        // todo 选择链接字符串，链接数据库

    }
    @FXML
    public void b_execute(ActionEvent actionEvent) {
        // todo 执行sql语句
        /* 新增1行*/
        Person per = new Person();
        per.setName1("1111").setName2("2222");

        this.table1.getItems().addAll(per);

    }

    public void b_book(ActionEvent actionEvent) {
        // todo 打开记事本，用于记录一些知识点
        System.out.println(" b_book ...咨询");
                KeyCombination kc = new KeyCodeCombination(KeyCode.F5);
        Mnemonic mnemonic = new Mnemonic(b_doSQL,kc);
        this.pane.getScene().addMnemonic(mnemonic);
    }

    public void zoomStarted(ZoomEvent zoomEvent) {
        // todo 窗口开始时
        System.out.println("zoom Started .... ");
    }
}
