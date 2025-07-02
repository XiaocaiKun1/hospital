module com.neusoft.neu24.his3 {
    requires java.net.http;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens his2.neusoft.neu24.his3 to javafx.fxml;
    opens his2.neusoft.neu24.his3.controller to javafx.fxml;
    opens his2.neusoft.neu24.his3.entity to javafx.base;
    exports his2.neusoft.neu24.his3;
    exports his2.neusoft.neu24.his3.controller;
}