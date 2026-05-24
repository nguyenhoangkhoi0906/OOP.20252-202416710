package dateapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateModel {
    private LocalDate currentDate;
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("'Ngày' dd 'tháng' MM 'năm' yyyy");

    public DateModel() {
        currentDate = LocalDate.now();
    }

    public String getFormattedDate() {
        return currentDate.format(formatter);
    }

    public void plusDays(int days) {
        currentDate = currentDate.plusDays(days);
    }

    public void minusDays(int days) {
        currentDate = currentDate.minusDays(days);
    }

    public void plusMonths(int months) {
        currentDate = currentDate.plusMonths(months);
    }

    public void minusMonths(int months) {
        currentDate = currentDate.minusMonths(months);
    }
}