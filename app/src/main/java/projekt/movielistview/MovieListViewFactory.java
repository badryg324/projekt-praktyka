package projekt.movielistview;

import javafx.stage.Stage;

public class MovieListViewFactory {
    public MovieListView create (MovieListViewModel viewModel,Stage stage) {
        return new MovieListView(viewModel,stage);
    }
}
