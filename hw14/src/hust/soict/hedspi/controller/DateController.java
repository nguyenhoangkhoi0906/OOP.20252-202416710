package hust.soict.hedspi.controller;

import hust.soict.hedspi.model.DateModel;
import java.time.LocalDate;

public class DateController {
    private final DateModel model;

    public DateController(DateModel model) {
        this.model = model;
    }


    public boolean validateInput(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(text.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public void calculateResultDate(int amount, String direction, String timeUnit) {
        LocalDate baseDate = model.getSelectedDate();
        if (baseDate == null) return;


        if ("before".equalsIgnoreCase(direction)) {
            amount = -amount;
        }

        LocalDate calculatedDate = baseDate;
        switch (timeUnit.toLowerCase()) {
            case "days":
                calculatedDate = baseDate.plusDays(amount);
                break;
            case "months":
                calculatedDate = baseDate.plusMonths(amount);
                break;
            case "years":
                calculatedDate = baseDate.plusYears(amount);
                break;
        }

        model.setResultDate(calculatedDate);
    }
}