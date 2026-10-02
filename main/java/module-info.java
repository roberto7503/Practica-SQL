module org.example.practicasql {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.practicasql to javafx.fxml;
    exports org.example.practicasql;
}