package projekt.movielistview;



import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public ObservableList<Node> movieList = FXCollections.<Node>observableArrayList();


    public MovieListViewModel(boolean isAdmin) {
        this.isAdmin = new SimpleBooleanProperty(isAdmin);
        titleArea = new SimpleStringProperty();
        directorArea = new SimpleStringProperty();
        hallArea = new SimpleStringProperty();
        dateArea = new SimpleStringProperty();
        readMovies();


        cannotAddPosition=titleArea.isEmpty().or(directorArea.isEmpty()).or(hallArea.isEmpty()).or(dateArea.isEmpty());






    }

    public StringProperty getTitleArea() {
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
            FileWriter fw=new FileWriter("app/src/main/resources/movieList.txt",StandardCharsets.UTF_8, true); //getClass().getResource("/movieList.txt").getPath()
            fw.write(titleArea.get()+";"+directorArea.get()+";"+hallArea.get()+";"+dateArea.get()+";"+System.getProperty( "line.separator" ));
            fw.close();

        }catch(Exception e){System.out.println(e);}
        System.out.println("Success...");

        readMovies();
    }

    public void refreshPositions(){

    }

    public void addHallReference() {

    }

    public void readMovies(){
        movieList.clear();


        String title;
        String director;
        String hall;
        String date;

        String line;

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("app/src/main/resources/movieList.txt"), StandardCharsets.UTF_8)); //getClass().getResourceAsStream("/movieList.txt")
            while ((line = bufferedReader.readLine()) != null) {

                title = line.substring(0, line.indexOf(';'));
                line = line.substring(line.indexOf(';')+1);
                director = line.substring(0, line.indexOf(';'));
                line = line.substring(line.indexOf(';')+1);
                hall = line.substring(0, line.indexOf(';'));
                line = line.substring(line.indexOf(';')+1);
                date = line.substring(0, line.indexOf(';'));

                HBox movie = new HBox();

                Button openMovieButton = new Button("Rezerwuj");
                openMovieButton.setOnAction(event -> openHallView());

                movie.getChildren().addAll(openMovieButton, new Label(title), new Label(director), new Label(hall), new Label(date), new Button("Delete"));
                movieList.add(movie);

            }
            bufferedReader.close();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    private void openHallView(){
        System.out.println("BANG");
    }
}

