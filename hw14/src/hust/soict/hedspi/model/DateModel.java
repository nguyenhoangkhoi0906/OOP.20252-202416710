package hust.soict.hedspi.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateModel {

    private final ObjectProperty<LocalDate> selectedDate = new SimpleObjectProperty<>(LocalDate.now());


    private final StringProperty dateFormat = new SimpleStringProperty("dd/MM/yyyy");


    private final ObjectProperty<LocalDate> resultDate = new SimpleObjectProperty<>();


    public ObjectProperty<LocalDate> selectedDateProperty() { return selectedDate; }
    public StringProperty dateFormatProperty() { return dateFormat; }
    public ObjectProperty<LocalDate> resultDateProperty() { return resultDate; }


    public LocalDate getSelectedDate() { return selectedDate.get(); }
    public void setSelectedDate(LocalDate date) { this.selectedDate.set(date); }

    public String getDateFormat() { return dateFormat.get(); }
    public void setDateFormat(String format) { this.dateFormat.set(format); }

    public LocalDate getResultDate() { return resultDate.get(); }
    public void setResultDate(LocalDate date) { this.resultDate.set(date); }
}