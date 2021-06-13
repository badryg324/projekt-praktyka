package projekt.hallview;

import javafx.stage.Stage;
import projekt.utils.MovieTitle;


public class HallViewFactory {

    public HallView create(Stage mainWindowStage, MovieTitle movieTitle, Runnable onReturnToListClicked) {
        HallViewModel viewModel = new HallViewModel(movieTitle,onReturnToListClicked);
        return new HallView(viewModel,mainWindowStage);
    }
}
