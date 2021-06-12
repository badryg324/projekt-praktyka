package projekt.views.userauthentication;

import javafx.stage.Stage;
import projekt.user.auth.UserAuthenticator;


public class UserAuthenticationViewFactory {
    private final UserAuthenticator userAuthenticator;

    public UserAuthenticationViewFactory(UserAuthenticator userAuthenticator) {
        this.userAuthenticator = userAuthenticator;
    }

    public UserAuthenticationView create(Stage stage, Runnable onUserAuthenticated) {
        UserAuthenticationViewModel viewModel = new UserAuthenticationViewModel(userAuthenticator, onUserAuthenticated);

        return new UserAuthenticationView(viewModel, stage);
    }
}