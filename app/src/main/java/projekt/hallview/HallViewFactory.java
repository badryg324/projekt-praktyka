package projekt.hallview;

import javafx.stage.Stage;
import projekt.loginview.LoginView;
import projekt.loginview.LoginViewModel;

public class HallViewFactory {
    public HallView create(HallViewModel viewModel, Stage stage) {
        return new HallView(viewModel, stage);
    }
}
