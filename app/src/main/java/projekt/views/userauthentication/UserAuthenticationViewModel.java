package projekt.views.userauthentication;

import io.reactivex.Completable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import projekt.user.auth.Credentials;
import projekt.user.auth.UserAuthenticator;


public class UserAuthenticationViewModel {
    private final UserAuthenticator userAuthenticator;

    private final SimpleStringProperty enteredUsername;
    private final SimpleStringProperty enteredPassword;
    private final SimpleStringProperty usernameHintMessage;
    private final SimpleStringProperty passwordHintMessage;
    private final SimpleBooleanProperty shouldShowPasswordHint;
    private final SimpleBooleanProperty loginInProgress = new SimpleBooleanProperty();

    private final ObservableBooleanValue cannotChangeUsername;
    private final ObservableBooleanValue cannotChangePassword;
    private final BooleanBinding cannotRequestLogin;
    private final BooleanBinding shouldShowUsernameHint;

    private final Runnable onUserAuthenticated;

    public UserAuthenticationViewModel(UserAuthenticator userAuthenticator, Runnable onUserAuthenticated) {
        // Services
        this.userAuthenticator = userAuthenticator;

        // Callbacks
        this.onUserAuthenticated = onUserAuthenticated;

        // Properties & observables
        enteredUsername = new SimpleStringProperty();
        enteredPassword = new SimpleStringProperty();
        usernameHintMessage = new SimpleStringProperty();
        passwordHintMessage = new SimpleStringProperty();
        shouldShowPasswordHint = new SimpleBooleanProperty();

        enteredUsername.addListener((observable, oldValue, newValue) -> {
            if (newValue.isBlank()) usernameHintMessage.set("Login nie może być pusty");
            else if (newValue.length() < 3) usernameHintMessage.set("Login nie może być krótszy niż 3 znaki");
            else if (newValue.length() > 30) usernameHintMessage.set("Login nie może być dłuższy niż 30 znaków");
            else usernameHintMessage.set(null);
        });

        shouldShowUsernameHint = usernameHintMessage.isNotEmpty();

        cannotChangeUsername = loginInProgress;
        cannotChangePassword = loginInProgress;

        cannotRequestLogin = loginInProgress
                .or(enteredUsername.isNull()).or(usernameHintMessage.isNotEmpty())
                .or(enteredPassword.isNull()).or(enteredPassword.isEmpty());
    }

    public void loginRequested() {
        loginInProgress.set(true);

        Completable
                .fromRunnable(() -> {
                    Credentials credentials = new Credentials(enteredUsername.get(), enteredPassword.get());
                    userAuthenticator.authenticate(credentials);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(JavaFxScheduler.platform())
                .doFinally(() -> loginInProgress.set(false))
                .subscribe(
                        () -> {
                            onUserAuthenticated.run();
                        },
                        throwable -> {
                            // ...
                        }
                );
    }

    public void loginRequested_traditional() {
        loginInProgress.set(true);
        System.out.println(Thread.currentThread().getName());

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            Credentials credentials = new Credentials(enteredUsername.get(), enteredPassword.get());
            userAuthenticator.authenticate(credentials);


            Platform.runLater(() -> {
                System.out.println(Thread.currentThread().getName());
                loginInProgress.set(false);
            });
        });
        thread.start();

    }

    public ObservableValue<Boolean> cannotChangeUsername() {
        return cannotChangeUsername;
    }

    public Property<String> enteredUsername() {
        return enteredUsername;
    }

    public ObservableValue<Boolean> cannotChangePassword() {
        return cannotChangePassword;
    }

    public Property<String> enteredPassword() {
        return enteredPassword;
    }

    public ObservableValue<String> usernameHintMessage() {
        return usernameHintMessage;
    }

    public ObservableValue<Boolean> shouldShowUsernameHint() {
        return shouldShowUsernameHint;
    }

    public ObservableValue<String> passwordHintMessage() {
        return passwordHintMessage;
    }

    public ObservableValue<Boolean> shouldShowPasswordHint() {
        return shouldShowPasswordHint;
    }

    public ObservableValue<Boolean> cannotRequestLogin() {
        return cannotRequestLogin;
    }
}
