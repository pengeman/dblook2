module org.peng.dblook2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.peng.dblook2 to javafx.fxml;
    exports org.peng.dblook2;
}