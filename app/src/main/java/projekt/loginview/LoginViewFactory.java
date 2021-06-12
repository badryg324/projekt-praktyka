package projekt.loginview;

import javafx.stage.Stage;
import projekt.authentication.UserAuthentiactor;

public class LoginViewFactory {
    public final UserAuthentiactor userAuthenticator;

    public LoginViewFactory(UserAuthentiactor userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
    }

    public LoginView create( Stage stage , Runnable onUserAuthenticated) {
        LoginViewModel viewModel = new LoginViewModel(userAuthenticator,onUserAuthenticated);
        return new LoginView(viewModel, stage);
    }
}
