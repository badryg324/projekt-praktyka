package projekt.movielistview;

import javafx.stage.Stage;
import projekt.utils.Movie;

public class MovieListViewFactory {
    public MovieListView create (Stage stage, boolean isAdmin, Movie movie, Runnable onMovieSelected) {
        MovieListViewModel viewModel = new MovieListViewModel(isAdmin, movie,onMovieSelected);
        return new MovieListView(viewModel,stage);
    }
}
