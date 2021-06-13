package projekt.movielistview;

import javafx.stage.Stage;
import projekt.authentication.UserAuthentiactor;
import projekt.loginview.LoginView;
import projekt.loginview.LoginViewModel;
import projekt.utils.MovieTitle;

public class MovieListViewFactory {
    public MovieListView create (Stage stage, boolean isAdmin, MovieTitle movieTitle,Runnable onMovieSelected) {
        MovieListViewModel viewModel = new MovieListViewModel(isAdmin,movieTitle,onMovieSelected);
        return new MovieListView(viewModel,stage);
    }
}
