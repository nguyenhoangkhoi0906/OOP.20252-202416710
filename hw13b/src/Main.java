import dateapp.model.DateModel;
import dateapp.view.DateView;
import dateapp.controller.DateController;

public class Main {
    public static void main(String[] args) {
        DateModel model = new DateModel();
        DateView view = new DateView();
        new DateController(model, view);

        view.setVisible(true);
    }
}