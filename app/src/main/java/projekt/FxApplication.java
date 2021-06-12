package projekt;

import javafx.application.Application;
import javafx.stage.Stage;
import projekt.user.auth.DummyUserAuthenticator;
import projekt.user.auth.UserAuthenticator;

import projekt.views.hallview.HallViewFactory;
import projekt.views.moviesList.MoviesListFactory;
import projekt.views.userauthentication.UserAuthenticationViewFactory;


public class FxApplication extends Application {
    private final Router router;

    public FxApplication() {
        UserAuthenticator userAuthenticator = new DummyUserAuthenticator();

        router = new Router(
                new UserAuthenticationViewFactory(userAuthenticator), new MoviesListFactory(), new HallViewFactory() //hmmmmm
        );
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        router.setMainWindowStage(primaryStage);
        router.showAuthenticationView();
    }
}
