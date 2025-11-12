module com.example.assign3zms {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.assign3zms to javafx.fxml;
    exports com.example.assign3zms;
    exports com.example.assign3zms.Controllers;
    opens com.example.assign3zms.Controllers to javafx.fxml;
    exports com.example.assign3zms.Model;
    opens com.example.assign3zms.Model to javafx.fxml;
    exports Controllers;
    opens Controllers to javafx.fxml;
}