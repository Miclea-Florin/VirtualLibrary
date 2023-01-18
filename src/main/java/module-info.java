module com.example.proiect {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    //requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;
    requires java.sql;
    //requires eu.hansolo.tilesfx;

    opens com.example.proiect to javafx.fxml;
    exports com.example.proiect;
}