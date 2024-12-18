module com.example.hospitalmanagment {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hospitalmanagment to javafx.fxml;
    exports hospital.managment;
}