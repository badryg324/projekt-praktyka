package projekt;

import javafx.application.Application;
import javafx.stage.Stage;
import projekt.authentication.UserAuthentiactor;
import projekt.hallview.HallViewFactory;
import projekt.loginview.LoginView;
import projekt.loginview.LoginViewFactory;
import projekt.loginview.LoginViewModel;
import projekt.movielistview.MovieListViewFactory;


public class    FxApplication extends Application {
    private final Router router;
    public FxApplication(){
        UserAuthentiactor userAuthenticator = new UserAuthentiactor();

        router = new Router(
                new LoginViewFactory(userAuthenticator),new MovieListViewFactory()
        );
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        router.setMainWindowStage(primaryStage);
        router.showLoginView();

       /* LoginViewFactory loginViewFactory = new LoginViewFactory(userAuthenticator);
        LoginViewModel viewModel = new LoginViewModel(userAuthenticator, onUserAuthenticated);*/



        /*HallViewFactory hallViewFactory = new HallViewFactory();
        HallViewModel viewModel = new HallViewModel();

        HallView hallView = hallViewFactory.create(viewModel, primaryStage);*/

    }
}
