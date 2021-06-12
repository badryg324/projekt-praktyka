package projekt.loginview;

import javafx.stage.Stage;

public class LoginViewFactory {
    public LoginView create(LoginViewModel viewModel, Stage stage) {
        return new LoginView(viewModel, stage);
    }
}
