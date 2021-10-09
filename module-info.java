module com.example.exercise {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens com.example.exercise to javafx.fxml;
    exports com.example.exercise;
}