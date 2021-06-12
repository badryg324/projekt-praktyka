package projekt.hallview;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class HallViewModel {
    public final BooleanProperty isAdmin;
    public HallViewModel(boolean isAdmin) {
        this.isAdmin = new SimpleBooleanProperty(isAdmin);
    }
}
