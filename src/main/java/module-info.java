module org.peng.dblook2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.lang3;


    opens org.peng.dblook2 to javafx.fxml;
    exports org.peng.dblook2;
}