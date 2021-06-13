package projekt;

import javafx.stage.Stage;
import projekt.hallview.HallView;
import projekt.hallview.HallViewFactory;
import projekt.hallview.HallViewModel;
import projekt.loginview.LoginViewFactory;
import projekt.movielistview.MovieListViewFactory;
import projekt.movielistview.MovieListViewModel;
import projekt.utils.MovieTitle;

public class Router {
    private final LoginViewFactory loginViewFactory;
    private final MovieListViewFactory moviesListViewFactory;
    private final HallViewFactory hallViewFactory;
    private final MovieTitle movieTitle;

    private Stage mainWindowStage;

    public Router(LoginViewFactory loginViewFactory, MovieListViewFactory moviesListViewFactory, HallViewFactory hallViewFactory, MovieTitle movieTitle) {
        this.loginViewFactory = loginViewFactory;
        this.moviesListViewFactory = moviesListViewFactory;
        this.hallViewFactory = hallViewFactory;
        this.movieTitle = movieTitle;
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
        moviesListViewFactory.create(mainWindowStage,loginViewFactory.userAuthenticator.isAdmin,movieTitle,onMovieSelected);
    }

    public void showHallView(){
        Runnable onReturnToListClicked = () ->{
            mainWindowStage.close();
            showHallView();
        };
        hallViewFactory.create(mainWindowStage,movieTitle,onReturnToListClicked);
    }
}
