package projekt.movielistview;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MovieListViewModel {
    public final BooleanProperty isAdmin;

    private final StringProperty titleArea;
    private final StringProperty directorArea;
    private final StringProperty hallArea ;
    private final StringProperty dateArea ;
    //private final ListProperty boxes;


    //private final ObjectProperty vboxLayout;


    private final BooleanBinding cannotAddPosition;



    public MovieListViewModel(boolean isAdmin) {
        this.isAdmin = new SimpleBooleanProperty(isAdmin);


        titleArea = new SimpleStringProperty();
        directorArea = new SimpleStringProperty();
        hallArea = new SimpleStringProperty();
        dateArea = new SimpleStringProperty();
        //vboxLayout=null;



        cannotAddPosition=titleArea.isNull().or(directorArea.isNull()).or(hallArea.isNull()).or(dateArea.isNull());






    }

    public StringProperty getAreaProperty() {
        return titleArea;
    }
    public StringProperty getDirectorArea() {
        return directorArea;
    }
    public StringProperty getHallArea() {
        return hallArea;
    }
    public StringProperty getDateArea() {
        return dateArea;
    }
    //public ObjectProperty getVboxLayout() { return vboxLayout; }
    //public ListProperty getBoxes() {return boxes;}


    public ObservableValue<Boolean> cannotAddPosition() {return cannotAddPosition;}
    public ObservableValue<VBox> refreshPositionObservable() {return refreshPositionObservable();}

    public void addPosition() {

        try{
            FileWriter fw=new FileWriter("app/src/main/resources/movieList.txt" ,StandardCharsets.UTF_8, true);
            fw.write(titleArea.get()+";"+directorArea.get()+";"+hallArea.get()+";"+dateArea.get()+";"+System.getProperty( "line.separator" ));
            fw.close();

        }catch(Exception e){System.out.println(e);}
        System.out.println("Success...");

        refreshPositions();
    }

    public void refreshPositions(){

    }

    public void addHallReference() {

    }
}

