module org.example.practicasql {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.practicasql to javafx.fxml;
    opens org.example.practicasql.model to javafx.base;
    exports org.example.practicasql;
    exports org.example.practicasql.controller to javafx.fxml;
    opens org.example.practicasql.controller to javafx.fxml;
}