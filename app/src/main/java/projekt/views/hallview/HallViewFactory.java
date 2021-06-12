package projekt.views.hallview;

import javafx.stage.Stage;


public class HallViewFactory {

        public HallView create(Stage stage) {return new HallView(new HallViewModel(), stage);}

}
