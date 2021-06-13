package projekt.hallview;

import javafx.stage.Stage;


public class HallViewFactory {
    public HallView create (HallViewModel viewModel, Stage stage) {
        return new HallView(viewModel,stage);
    }
}
