package projekt.movielistview;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class MovieListViewModel {
    public final BooleanProperty isAdmin;

    public MovieListViewModel(boolean isAdmin) {
        this.isAdmin = new SimpleBooleanProperty(isAdmin);
    }
}
