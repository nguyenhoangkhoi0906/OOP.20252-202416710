package hust.soict.hedspi.view;

import hust.soict.hedspi.controller.DateController;
import hust.soict.hedspi.model.DateModel;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateView extends Application {
    private DateModel model;
    private DateController controller;

    @Override
    public void start(Stage primaryStage) {

        model = new DateModel();
        controller = new DateController(model);

        primaryStage.setTitle("Date Display GUI 2 - JavaFX");


        Label lblSelectedDateText = new Label("Selected Date Display: ");
        Label lblDynamicDate = new Label(); // Nhãn hiển thị ngày động sinh ra nhờ Binding
        lblDynamicDate.setStyle("-fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        DatePicker datePicker = new DatePicker(LocalDate.now());

        ComboBox<String> cbFormat = new ComboBox<>(FXCollections.observableArrayList(
                "dd/MM/yyyy", "MM/dd/yyyy", "dd.MM.yyyy", "yyyy-MM-dd"
        ));
        cbFormat.setValue("dd/MM/yyyy");

        TextField txtX = new TextField();
        txtX.setPromptText("Enter an integer x");


        ToggleGroup directionGroup = new ToggleGroup();
        RadioButton rbAfter = new RadioButton("After");
        rbAfter.setToggleGroup(directionGroup);
        rbAfter.setSelected(true);
        RadioButton rbBefore = new RadioButton("Before");
        rbBefore.setToggleGroup(directionGroup);
        HBox hbDirection = new HBox(10, rbAfter, rbBefore);


        ComboBox<String> cbUnit = new ComboBox<>(FXCollections.observableArrayList("Days", "Months", "Years"));
        cbUnit.setValue("Days");

        Button btnApply = new Button("Apply Operation");
        Label lblError = new Label();
        lblError.setTextFill(Color.RED);

        Label lblResultTitle = new Label("Result Date: ");
        Label lblResultValue = new Label("N/A");
        lblResultValue.setStyle("-fx-font-weight: bold; -fx-text-fill: #27ae60;");


        model.selectedDateProperty().bind(datePicker.valueProperty());
        model.dateFormatProperty().bind(cbFormat.valueProperty());


        lblDynamicDate.textProperty().bind(Bindings.createStringBinding(() -> {
            LocalDate date = model.getSelectedDate();
            String format = model.getDateFormat();
            if (date != null && format != null) {
                return date.format(DateTimeFormatter.ofPattern(format));
            }
            return "";
        }, model.selectedDateProperty(), model.dateFormatProperty()));


        btnApply.disableProperty().bind(Bindings.createBooleanBinding(() -> {
            String text = txtX.getText();
            if (controller.validateInput(text)) {
                lblError.setText(""); // Xóa lỗi nếu hợp lệ
                return false;         // Không disable nút Apply
            } else {
                if (text == null || text.trim().isEmpty()) {
                    lblError.setText("Input number cannot be empty.");
                } else {
                    lblError.setText("Invalid input! Must be an integer.");
                }
                return true;          // Disable nút Apply
            }
        }, txtX.textProperty()));


        lblResultValue.textProperty().bind(Bindings.createStringBinding(() -> {
            LocalDate resDate = model.resultDateProperty().get();
            String format = model.getDateFormat();
            if (resDate != null && format != null) {
                return resDate.format(DateTimeFormatter.ofPattern(format));
            }
            return "N/A";
        }, model.resultDateProperty(), model.dateFormatProperty()));


        btnApply.setOnAction(e -> {
            int amount = Integer.parseInt(txtX.getText().trim());
            RadioButton selectedRadio = (RadioButton) directionGroup.getSelectedToggle();
            String direction = selectedRadio.getText();
            String unit = cbUnit.getValue();

            controller.calculateResultDate(amount, direction, unit);
        });


        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(12);
        grid.setPadding(new Insets(20));
        grid.setAlignment(Pos.CENTER);

        grid.add(lblSelectedDateText, 0, 0);
        grid.add(lblDynamicDate, 1, 0);

        grid.add(new Label("Choose Base Date:"), 0, 1);
        grid.add(datePicker, 1, 1);

        grid.add(new Label("Select Date Format:"), 0, 2);
        grid.add(cbFormat, 1, 2);

        grid.add(new Label("Enter Number (x):"), 0, 3);
        grid.add(txtX, 1, 3);

        grid.add(new Label("Direction:"), 0, 4);
        grid.add(hbDirection, 1, 4);

        grid.add(new Label("Time Unit:"), 0, 5);
        grid.add(cbUnit, 1, 5);

        grid.add(btnApply, 1, 6);
        grid.add(lblError, 1, 7);


        HBox hbResult = new HBox(5, lblResultTitle, lblResultValue);
        hbResult.setAlignment(Pos.CENTER);
        hbResult.setPadding(new Insets(10));
        hbResult.setStyle("-fx-background-color: #ecf0f1; -fx-background-radius: 5;");
        grid.add(hbResult, 0, 8, 2, 1);

        Scene scene = new Scene(grid, 450, 480);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
