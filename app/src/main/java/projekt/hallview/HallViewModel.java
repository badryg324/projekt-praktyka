package projekt.hallview;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import projekt.utils.Movie;

public class HallViewModel {
    public StringProperty movieTitle;
    public Runnable onReturnToListClicked;

    public HallViewModel(Movie movie, Runnable onReturnToListClicked) {
        this.movieTitle = new SimpleStringProperty(movie.getValue());
        this.onReturnToListClicked = onReturnToListClicked;
    }
}
