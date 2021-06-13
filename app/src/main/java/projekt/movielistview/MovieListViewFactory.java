package projekt.movielistview;

import javafx.stage.Stage;
import projekt.authentication.UserAuthentiactor;
import projekt.loginview.LoginView;
import projekt.loginview.LoginViewModel;

public class MovieListViewFactory {
    public MovieListView create (MovieListViewModel viewModel,Stage stage) {
        return new MovieListView(viewModel,stage);
    }
}
