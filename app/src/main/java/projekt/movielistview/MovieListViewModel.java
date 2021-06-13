package projekt.movielistview;



import io.reactivex.Completable;
import io.reactivex.rxjavafx.schedulers.JavaFxScheduler;
import io.reactivex.schedulers.Schedulers;
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
import projekt.utils.MovieTitle;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class MovieListViewModel {
    public final BooleanProperty isAdmin;

    private final StringProperty titleArea;
    private final StringProperty directorArea;
    private final StringProperty hallArea ;
    private final StringProperty dateArea ;
    private final MovieTitle movieTitle;
    private final Runnable onMovieSelected;
    //private final ListProperty boxes;


    //private final ObjectProperty vboxLayout;


    private final BooleanBinding cannotAddPosition;
    public ObservableList<Node> movieList = FXCollections.<Node>observableArrayList();


    public MovieListViewModel(boolean isAdmin, MovieTitle movieTitle, Runnable onMovieSelected) {
        this.isAdmin = new SimpleBooleanProperty(isAdmin);
        this.movieTitle = movieTitle;
        this.onMovieSelected = onMovieSelected;
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
        int counter = 0;
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
                String finalTitle = title;
                openMovieButton.setOnAction(event ->openHallView(finalTitle));

                Button deleteButton = new Button("Delete");
                deleteButton.visibleProperty().bind(isAdmin);
                deleteButton.managedProperty().bind(isAdmin);
                int finalCounter = counter;
                deleteButton.setOnAction(event -> deleteMovie(finalCounter));


                movie.getChildren().addAll(openMovieButton, new Label(title), new Label(director), new Label(hall), new Label(date), deleteButton);
                movieList.add(movie);
                counter++;

            }
            bufferedReader.close();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    private void openHallView(String title){
        this.movieTitle.setValue(title);
        onMovieSelected.run();
    }

    private void deleteMovie(int lineNumber){
        String line;
        int counter = 0;
        File newFile = new File("app/src/main/resources/new-movieList.txt");
        try {
            FileWriter fw=new FileWriter(newFile,StandardCharsets.UTF_8, true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("app/src/main/resources/movieList.txt"), StandardCharsets.UTF_8)); //getClass().getResourceAsStream("/movieList.txt")
            while ((line = bufferedReader.readLine()) != null) {
                if(counter!=lineNumber){
                    fw.write(line+System.getProperty( "line.separator" ));
                }
                counter++;
            }
            bufferedReader.close();
            fw.close();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        File oldFile = new File("app/src/main/resources/movieList.txt");
        oldFile.delete();
        newFile.renameTo(oldFile);
        readMovies();
    }
}

