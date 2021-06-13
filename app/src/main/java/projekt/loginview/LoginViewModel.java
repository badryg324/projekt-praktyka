package projekt.loginview;

import io.reactivex.Completable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import projekt.authentication.UserAuthentiactor;

import java.io.IOException;

public class LoginViewModel {
    private final UserAuthentiactor userAuthenticator;

    private final StringProperty username;
    private final StringProperty password;
    private final BooleanProperty loginInProgress ;
    private final BooleanProperty cannotChangeUsername;
    private final BooleanProperty cannotChangePassword;
    private final BooleanBinding cannotLogin;
    private final BooleanProperty isAuthenticated ;

    private final SimpleStringProperty usernameHint;
    private final SimpleStringProperty passwordHint;
    private final BooleanBinding shouldShowUsernameHint;
    private final BooleanBinding shouldShowPasswordHint;

    private final Runnable onUserAuthenticated;

    public LoginViewModel(UserAuthentiactor userAuthenticator, Runnable onUserAuthenticated) {
        this.userAuthenticator = userAuthenticator;
        this.onUserAuthenticated = onUserAuthenticated;

        username = new SimpleStringProperty();
        password = new SimpleStringProperty();
        usernameHint = new SimpleStringProperty();
        passwordHint = new SimpleStringProperty();
        loginInProgress = new SimpleBooleanProperty(false);
        isAuthenticated = new SimpleBooleanProperty(false);
        username.addListener((observable,oldValue,newValue) -> {
            if(newValue.isBlank()) usernameHint.set("Username cannot be empty");
            else if (newValue.length() < 3) usernameHint.set("Username cannot be shorter than 3 characters");
            else if (newValue.length() > 30) usernameHint.set("Username cannot be longer than 30 characters");
            else usernameHint.set(null);
        });
        shouldShowUsernameHint = usernameHint.isNotEmpty();
        password.addListener((observable,oldValue,newValue) -> {
            if (newValue.isBlank()) passwordHint.set("Password cannot be empty");
            else if (newValue.length()<8) passwordHint.set("Password cannot be shorter than 8 characters");
            else passwordHint.set(null);
        });
        shouldShowPasswordHint = passwordHint.isNotEmpty();

        cannotChangeUsername = loginInProgress;
        cannotChangePassword = loginInProgress;
        cannotLogin = loginInProgress
                .or(username.isNull()).or(usernameHint.isNotEmpty())
                .or(password.isNull()).or(passwordHint.isNotEmpty());



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
        Completable.fromRunnable(
                () -> {
                    try {
                        isAuthenticated.setValue(userAuthenticator.authenticate(username.get(), password.get()));

                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .doFinally(() -> loginInProgress.set(false))
                .subscribe(
                        () -> {
                            if (isAuthenticated.get()) {
                                onUserAuthenticated.run();
                            }
                        },
                        throwable -> {
                            // ...
                        }
                );
    }

    public void cancelLoginClicked() {
        loginInProgress.set(false);
    }

    public ObservableValue<String> usernameHint(){
        return usernameHint;
    }

    public ObservableValue<String> passwordHint(){
        return passwordHint;
    }

    public ObservableValue<Boolean> shouldShowPasswordHint() {
        return shouldShowPasswordHint;
    }

    public ObservableValue<Boolean> shouldShowUsernameHint() {
        return shouldShowUsernameHint;
    }


}
