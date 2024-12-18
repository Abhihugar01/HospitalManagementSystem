package hospital.managment;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HospitalManagementSystem extends Application {

    private final List<Patient> patients = new ArrayList<>();
    private final List<Ward> wards = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hospital Management System 2.0");

        // Main Menu Buttons
        Button patientButton = new Button("Patient Management");
        Button wardButton = new Button("Ward Management");
        Button exitButton = new Button("Exit");

        // Layout for Main Menu
        VBox mainMenu = new VBox(15, patientButton, wardButton, exitButton);
        mainMenu.setPadding(new Insets(20));
        mainMenu.setStyle("-fx-alignment: center; -fx-background-color: #f4f4f4;");

        Scene mainScene = new Scene(mainMenu, 400, 300);

        // Patient Management Scene
        VBox patientLayout = new VBox(10);
        patientLayout.setPadding(new Insets(20));
        Button addPatientButton = new Button("Add Patient");
        Button viewPatientsButton = new Button("View Patients");
        Button removePatientButton = new Button("Remove Patient");
        Button backFromPatientButton = new Button("Back");
        patientLayout.getChildren().addAll(addPatientButton, viewPatientsButton, removePatientButton, backFromPatientButton);

        Scene patientScene = new Scene(patientLayout, 400, 300);

        // Ward Management Scene
        VBox wardLayout = new VBox(10);
        wardLayout.setPadding(new Insets(20));
        Button addWardButton = new Button("Add Ward");
        Button viewWardsButton = new Button("View Wards");
        Button removeWardButton = new Button("Remove Ward");
        Button backFromWardButton = new Button("Back");
        wardLayout.getChildren().addAll(addWardButton, viewWardsButton, removeWardButton, backFromWardButton);

        Scene wardScene = new Scene(wardLayout, 400, 300);

        // Main Menu Actions
        patientButton.setOnAction(e -> primaryStage.setScene(patientScene));
        wardButton.setOnAction(e -> primaryStage.setScene(wardScene));
        exitButton.setOnAction(e -> primaryStage.close());

        // Patient Management Actions
        addPatientButton.setOnAction(e -> showAddPatientDialog());
        viewPatientsButton.setOnAction(e -> showPatientsList());
        removePatientButton.setOnAction(e -> showRemovePatientDialog());
        backFromPatientButton.setOnAction(e -> primaryStage.setScene(mainScene));

        // Ward Management Actions
        addWardButton.setOnAction(e -> showAddWardDialog());
        viewWardsButton.setOnAction(e -> showWardsList());
        removeWardButton.setOnAction(e -> showRemoveWardDialog(viewWardsButton));  // Pass viewWardsButton to refresh the list
        backFromWardButton.setOnAction(e -> primaryStage.setScene(mainScene));

        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    // Add Patient Dialog
    private void showAddPatientDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Add Patient");

        Label idLabel = new Label("Patient ID:");
        TextField idField = new TextField();

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label ailmentLabel = new Label("Ailment:");
        TextField ailmentField = new TextField();

        Button addButton = new Button("Add");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox(10, addButton, cancelButton);
        buttons.setStyle("-fx-alignment: center;");

        VBox layout = new VBox(10, idLabel, idField, nameLabel, nameField, ailmentLabel, ailmentField, buttons);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 300);
        dialog.setScene(scene);

        addButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String ailment = ailmentField.getText();

                patients.add(new Patient(id, name, ailment));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient added successfully.");
                alert.showAndWait();
                dialog.close();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Please enter valid data.");
                alert.showAndWait();
            }
        });

        cancelButton.setOnAction(e -> dialog.close());
        dialog.showAndWait();
    }

    // View Patients
    private void showPatientsList() {
        Stage dialog = new Stage();
        dialog.setTitle("Patient List");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        if (patients.isEmpty()) {
            layout.getChildren().add(new Label("No patients available."));
        } else {
            for (Patient patient : patients) {
                layout.getChildren().add(new Label(patient.toString()));
            }
        }

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> dialog.close());
        layout.getChildren().add(closeButton);

        Scene scene = new Scene(layout, 300, 300);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    // Remove Patient
    private void showRemovePatientDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Remove Patient");

        Label idLabel = new Label("Enter Patient ID:");
        TextField idField = new TextField();

        Button removeButton = new Button("Remove");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox(10, removeButton, cancelButton);
        buttons.setStyle("-fx-alignment: center;");

        VBox layout = new VBox(10, idLabel, idField, buttons);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 200);
        dialog.setScene(scene);

        removeButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                patients.removeIf(patient -> patient.id == id);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Patient removed successfully.");
                alert.showAndWait();
                dialog.close();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Please enter a valid ID.");
                alert.showAndWait();
            }
        });

        cancelButton.setOnAction(e -> dialog.close());
        dialog.showAndWait();
    }

    // Add Ward Dialog
    private void showAddWardDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Add Ward");

        Label wardNumberLabel = new Label("Ward Number:");
        TextField wardNumberField = new TextField();

        Label capacityLabel = new Label("Capacity:");
        TextField capacityField = new TextField();

        Label locationLabel = new Label("Location:");
        TextField locationField = new TextField();

        Button addButton = new Button("Add");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox(10, addButton, cancelButton);
        buttons.setStyle("-fx-alignment: center;");

        VBox layout = new VBox(10, wardNumberLabel, wardNumberField, capacityLabel, capacityField, locationLabel, locationField, buttons);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 300);
        dialog.setScene(scene);

        addButton.setOnAction(e -> {
            try {
                int wardNumber = Integer.parseInt(wardNumberField.getText());
                int capacity = Integer.parseInt(capacityField.getText());
                String location = locationField.getText();

                wards.add(new Ward(wardNumber, capacity, location));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ward added successfully.");
                alert.showAndWait();
                dialog.close();
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Please enter valid data.");
                alert.showAndWait();
            }
        });

        cancelButton.setOnAction(e -> dialog.close());
        dialog.showAndWait();
    }

    // View Wards
    private void showWardsList() {
        Stage dialog = new Stage();
        dialog.setTitle("Ward List");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        if (wards.isEmpty()) {
            layout.getChildren().add(new Label("No wards available."));
        } else {
            for (Ward ward : wards) {
                layout.getChildren().add(new Label(ward.toString()));
            }
        }

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> dialog.close());
        layout.getChildren().add(closeButton);

        Scene scene = new Scene(layout, 300, 300);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    // Remove Ward Dialog
    private void showRemoveWardDialog(Button viewWardsButton) {
        Stage dialog = new Stage();
        dialog.setTitle("Remove Ward");

        Label wardNumberLabel = new Label("Enter Ward Number:");
        TextField wardNumberField = new TextField();

        Button removeButton = new Button("Remove");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox(10, removeButton, cancelButton);
        buttons.setStyle("-fx-alignment: center;");

        VBox layout = new VBox(10, wardNumberLabel, wardNumberField, buttons);
        layout.setPadding(new Insets(20));

        Scene scene = new Scene(layout, 300, 200);
        dialog.setScene(scene);

        removeButton.setOnAction(e -> {
            try {
                int wardNumber = Integer.parseInt(wardNumberField.getText());
                wards.removeIf(ward -> ward.wardNumber == wardNumber);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Ward removed successfully.");
                alert.showAndWait();
                dialog.close();
                showWardsList();  // Refresh the ward list
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid input. Please enter a valid ward number.");
                alert.showAndWait();
            }
        });

        cancelButton.setOnAction(e -> dialog.close());
        dialog.showAndWait();
    }

    // Patient Class
    static class Patient {
        int id;
        String name;
        String ailment;

        Patient(int id, String name, String ailment) {
            this.id = id;
            this.name = name;
            this.ailment = ailment;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Ailment: " + ailment;
        }
    }

    // Ward Class
    static class Ward {
        int wardNumber;
        int capacity;
        String location;

        Ward(int wardNumber, int capacity, String location) {
            this.wardNumber = wardNumber;
            this.capacity = capacity;
            this.location = location;
        }

        @Override
        public String toString() {
            return "Ward Number: " + wardNumber + ", Capacity: " + capacity + ", Location: " + location;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
