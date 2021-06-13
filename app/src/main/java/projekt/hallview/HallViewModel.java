package projekt.hallview;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import projekt.utils.MovieTitle;

public class HallViewModel {
    public StringProperty movieTitle;
    public Runnable onReturnToListClicked;

    public HallViewModel(MovieTitle movieTitle, Runnable onReturnToListClicked) {
        this.movieTitle = new SimpleStringProperty(movieTitle.getValue());
        this.onReturnToListClicked = onReturnToListClicked;
    }
}
