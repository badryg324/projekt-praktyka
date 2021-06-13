package projekt;

import javafx.stage.Stage;
import projekt.hallview.HallViewFactory;
import projekt.loginview.LoginViewFactory;
import projekt.movielistview.MovieListViewFactory;
import projekt.utils.Movie;

public class Router {
    private final LoginViewFactory loginViewFactory;
    private final MovieListViewFactory moviesListViewFactory;
    private final HallViewFactory hallViewFactory;
    private final Movie movie;

    private Stage mainWindowStage;

    public Router(LoginViewFactory loginViewFactory, MovieListViewFactory moviesListViewFactory, HallViewFactory hallViewFactory, Movie movie) {
        this.loginViewFactory = loginViewFactory;
        this.moviesListViewFactory = moviesListViewFactory;
        this.hallViewFactory = hallViewFactory;
        this.movie = movie;
    }

    public void setMainWindowStage(Stage mainWindow) {
        this.mainWindowStage = mainWindow;
    }

    public void showLoginView(){
        Runnable onUserAuthenticated = () ->{
            mainWindowStage.close();
            showMoviesListView();
        };
        loginViewFactory.create(mainWindowStage,onUserAuthenticated);
    }

    public void showMoviesListView() {
        Runnable onMovieSelected = () ->{
            mainWindowStage.close();
            showHallView();
        };
        Stage otherWindow = new Stage();
        moviesListViewFactory.create(mainWindowStage,loginViewFactory.userAuthenticator.isAdmin, movie,onMovieSelected);
    }

    public void showHallView(){
        Runnable onReturnToListClicked = () ->{
            mainWindowStage.close();
            showHallView();
        };
        hallViewFactory.create(mainWindowStage, movie,onReturnToListClicked);
    }
}
