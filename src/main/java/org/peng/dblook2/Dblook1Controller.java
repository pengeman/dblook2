package org.peng.dblook2;

import dbhelp.DataSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.peng.dblook2.util.Common;
import org.peng.note.NoteDia;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class Dblook1Controller implements Initializable {

    @FXML
    public ToolBar toolBar;
    @FXML
    public Button b_doSQL;
    @FXML
    public TextArea t_sql, t_d;
    @FXML
    public AnchorPane pane;
    @FXML
    AnchorPane spane1, spane2;
    @FXML
    public SplitPane s_pane;
    @FXML
    TableView table1;


    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
                t_sql.setPrefHeight(height * ((double) newv));
                table1.setPrefHeight(height - height * ((double) newv) - 40);

            }
        });
    }

    @FXML
    public void b_lookforjdbc(ActionEvent actionEvent) {
        // todo 选择数据源，链接数据库
        Parent root = null;
        javafx.stage.Stage dbsourceStage = new Stage();

        URL url = this.getClass().getResource("dbsource.fxml");
        try {
//            FXMLLoader loader = new FXMLLoader();
//            loader.setController(this);
//            loader.setLocation(url);
//            root = loader.load();
            //root = FXMLLoader.load(url);
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            Scene scene = new Scene(fxmlLoader.load());
            dbsourceStage.setTitle("选择数据源");
            dbsourceStage.setScene(scene);
            dbsourceStage.initModality(Modality.APPLICATION_MODAL);
            dbsourceStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void b_execute(ActionEvent actionEvent) {
        // todo 执行sql语句
        String t_sql = this.t_sql.getSelectedText();
        if (t_sql == null || t_sql.isEmpty()) {
            String msg = "sql语句没有发现";
            showText(msg);
            return;
        }
        t_sql = t_sql.trim();
        String first_sql = t_sql.substring(0,6).toLowerCase();

        if (first_sql.substring(0, 6).equals("select")) {// 是select语句
            DataSet ds = Common.dataBase.query(t_sql);
            //List dsls = ds.generateList();
            // 显示到table里
            showtable(ds);
        }

        // 执行DDL语句
        if (first_sql.equals("insert") || first_sql.equals("update") || first_sql.equals("delete")){
            int r = Common.dataBase.execute(t_sql);
            showText("执行完成，影响了" + r + "条语句");
        }

        // 执行DML语句
        //Common.dataBase.execute(t_sql);
        //showText("执行完成");


        // 分析sql语句,非阻塞
        this.explain(t_sql);

    }

    public void b_book(ActionEvent actionEvent) {
        // todo 打开记事本，用于记录一些知识点

        org.peng.note.NoteDia nd = new NoteDia();
        nd.main();
//                KeyCombination kc = new KeyCodeCombination(KeyCode.F5);
//        Mnemonic mnemonic = new Mnemonic(b_doSQL,kc);
//        this.pane.getScene().addMnemonic(mnemonic);
    }

    private void showText(String msg) {
        // todo 在textView中现实信息
        this.t_d.setText(msg);
        this.t_d.setVisible(true);
        this.table1.setVisible(false);
    }

    /**
     * todo 将dataset数据显示到table里
     *
     * @return
     */
    private void showtable(DataSet ds) {
        ObservableList<Map> data = FXCollections.observableArrayList();

        TableColumn t_col[];
        List<String> colNames = ds.getColNameSet();
        t_col = new TableColumn[colNames.size()];
        for (int i = 0; i < t_col.length; i++) {
            String colName = colNames.get(i);
            t_col[i] = new TableColumn(colName);
            t_col[i].setCellValueFactory(new MapValueFactory<String>(colName));
        }

        // 将ds中的数据写入ObservableList
        List<Map<String, Object>> rss = ds.getDataTable(); // 源头
        for (int i = 0; i < rss.size(); i++) {
            data.add(rss.get(i));
        }

        this.table1.getItems().clear();
        this.table1.getColumns().clear();
        this.table1.getColumns().addAll(t_col);
        this.table1.setItems(data);
        this.table1.setVisible(true);
        this.t_d.setVisible(false);


    }

    // sql语句分析
    private void explain(String sql) {
        // 使用explain分析mysql的语句，结果显示在
    }

    public void zoomStarted(ZoomEvent zoomEvent) {
        // todo 窗口开始时
        System.out.println("zoom Started .... ");
    }
}
