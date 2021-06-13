package projekt.hallview;

import javafx.stage.Stage;
import projekt.utils.Movie;


public class HallViewFactory {

    public HallView create(Stage mainWindowStage, Movie movie, Runnable onReturnToListClicked) {
        HallViewModel viewModel = new HallViewModel(movie,onReturnToListClicked);
        return new HallView(viewModel,mainWindowStage);
    }
}
