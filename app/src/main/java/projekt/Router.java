package projekt;

import javafx.stage.Stage;
import projekt.hallview.HallViewFactory;
import projekt.hallview.HallViewModel;
import projekt.loginview.LoginViewFactory;
import projekt.movielistview.MovieListViewFactory;
import projekt.movielistview.MovieListViewModel;

public class Router {
    private final LoginViewFactory loginViewFactory;
    private final MovieListViewFactory moviesListViewFactory;

    private Stage mainWindowStage;

    public Router(LoginViewFactory loginViewFactory, MovieListViewFactory moviesListViewFactory) {
        this.loginViewFactory = loginViewFactory;
        this.moviesListViewFactory = moviesListViewFactory;
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
        Stage otherWindow = new Stage();
        moviesListViewFactory.create(new MovieListViewModel(loginViewFactory.userAuthenticator.isAdmin), mainWindowStage);
    }
}
