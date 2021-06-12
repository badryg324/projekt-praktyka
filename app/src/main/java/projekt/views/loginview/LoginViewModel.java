package projekt.loginview;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableBooleanValue;

public class LoginViewModel {
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private final BooleanProperty loginInProgress = new SimpleBooleanProperty(false);
    private final BooleanProperty cannotChangeUsername;
    private final BooleanProperty cannotChangePassword;
    private final BooleanBinding cannotLogin;

    public LoginViewModel() {
        BooleanBinding anyFieldInvalid = username.isEmpty()
                .or(username.length().lessThan(3))
                .or(username.length().greaterThan(30))
                .or(password.isEmpty())
                .or(password.length().lessThan(8))
                .or(password.length().greaterThan(60));

        cannotLogin = loginInProgress.or(anyFieldInvalid);
        cannotChangeUsername = loginInProgress;
        cannotChangePassword = loginInProgress;
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public ObservableBooleanValue cannotLogin() {
        return cannotLogin;
    }

    public ObservableBooleanValue cannotChangeUsername() {
        return cannotChangeUsername;
    }

    public ObservableBooleanValue cannotChangePassword() {
        return cannotChangePassword;
    }

    public void loginClicked() {
        loginInProgress.set(true);

        // ...
    }

    public void cancelLoginClicked() {
        loginInProgress.set(false);
    }
}
