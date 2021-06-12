package projekt.views.moviesList;

import javafx.stage.Stage;

import java.io.IOException;


public class MoviesListFactory {


    public MoviesListView create(Stage stage)  {return new MoviesListView(new MoviesListModel(), stage);}



}
