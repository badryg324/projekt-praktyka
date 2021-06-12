package projekt;

import javafx.stage.Stage;
import projekt.views.hallview.HallViewFactory;
import projekt.views.moviesList.MoviesListFactory;
import projekt.views.userauthentication.UserAuthenticationViewFactory;


public class Router {
    private final UserAuthenticationViewFactory userAuthenticationViewFactory;
    private final MoviesListFactory moviesListFactory;
    private final HallViewFactory hallViewFactory;

    private Stage mainWindowStage;

    public Router(
            UserAuthenticationViewFactory userAuthenticationViewFactory,
            MoviesListFactory moviesListFactory,
            HallViewFactory hallViewFactory

    ) {
        this.userAuthenticationViewFactory = userAuthenticationViewFactory;
        this.moviesListFactory= moviesListFactory;
        this.hallViewFactory = hallViewFactory;
    }

    public void setMainWindowStage(Stage mainWindowStage) {
        this.mainWindowStage = mainWindowStage;
    }

    public void showAuthenticationView() {
        Runnable onUserAuthenticated = () -> {
            mainWindowStage.close();
            //showMainWindowView();
            showMovieListView();
        };


        userAuthenticationViewFactory.create(mainWindowStage, onUserAuthenticated);
    }


    public void showMainWindowView() {
        Stage otherWindow = new Stage();
        hallViewFactory.create(otherWindow);
    }

    public void showMovieListView(){  // autorskie xd
        Stage moviesWindows = new Stage();
        moviesListFactory.create(moviesWindows);
    }
}
