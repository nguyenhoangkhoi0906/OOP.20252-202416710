package dateapp.controller;

import dateapp.model.DateModel;
import dateapp.view.DateView;

public class DateController {
    private DateModel model;
    private DateView view;

    public DateController(DateModel model, DateView view) {
        this.model = model;
        this.view = view;

        view.setDateText(model.getFormattedDate());
        addEventListeners();
    }

    private void addEventListeners() {
        view.getPreviousDaysButton().addActionListener(e -> moveDaysBefore());
        view.getNextDaysButton().addActionListener(e -> moveDaysAfter());
        view.getPreviousMonthsButton().addActionListener(e -> moveMonthsBefore());
        view.getNextMonthsButton().addActionListener(e -> moveMonthsAfter());
    }

    private int getValidInput() {
        String input = view.getInputValue().trim();

        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty.");
        }

        int value;

        try {
            value = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Input must be a number.");
        }

        if (value < 0) {
            throw new IllegalArgumentException("Input cannot be negative.");
        }

        if (value > 10000) {
            throw new IllegalArgumentException("Input value is too large.");
        }

        return value;
    }

    private void moveDaysBefore() {
        try {
            int x = getValidInput();
            model.minusDays(x);
            view.setDateText(model.getFormattedDate());
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    private void moveDaysAfter() {
        try {
            int x = getValidInput();
            model.plusDays(x);
            view.setDateText(model.getFormattedDate());
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    private void moveMonthsBefore() {
        try {
            int x = getValidInput();
            model.minusMonths(x);
            view.setDateText(model.getFormattedDate());
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }

    private void moveMonthsAfter() {
        try {
            int x = getValidInput();
            model.plusMonths(x);
            view.setDateText(model.getFormattedDate());
        } catch (IllegalArgumentException e) {
            view.showError(e.getMessage());
        }
    }
}