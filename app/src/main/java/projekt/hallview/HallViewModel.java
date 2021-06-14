package projekt.hallview;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import projekt.utils.Movie;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HallViewModel {
    public StringProperty movieTitle;
    public Runnable onReturnToListClicked;
    public ObservableList<Node> seats = FXCollections.<Node>observableArrayList();
    public ArrayList reserved = new ArrayList<Integer>();

    public HallViewModel(Movie movie, Runnable onReturnToListClicked) {
        this.movieTitle = new SimpleStringProperty(movie.getValue());
        this.onReturnToListClicked = onReturnToListClicked;






    }





    public void loadSeats(){
        seats.clear();
        try {
            Boolean isReserved;
            Integer counter = 1;
            String line;
            HBox row = new HBox();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("app/src/main/resources/movies/"+ movieTitle.getValue()+".txt"), StandardCharsets.UTF_8)); //getClass().getResourceAsStream("/movieList.txt")
            while ((line = bufferedReader.readLine()) !=null) {


                Button seatButton = new Button(counter.toString());
                Integer finalCounter = counter;
                seatButton.setOnAction(event -> toggleSeatReserved(event,finalCounter));
                isReserved = Boolean.parseBoolean(line);
                seatButton.disableProperty().setValue(isReserved);
                if(isReserved){
                    reserved.add(counter);
                }
                row.getChildren().add(seatButton);
                if(counter%6==0){
                    seats.add(row);
                    row = new HBox();
                }






                counter++;

            }

            bufferedReader.close();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    private void toggleSeatReserved(ActionEvent event, Integer finalCounter) {
        Button source = (Button) event.getSource();
        if(reserved.contains(finalCounter)){
            reserved.remove(finalCounter);
            source.setStyle("-fx-background-color: #929090,linear-gradient(#e9e9e9, #aeaeae)");

        }
        else {
            reserved.add(finalCounter);
            source.setStyle("-fx-background-color: green");
        }



    }

    public void reserveSeats() {
        String line;
        Integer counter = 0;
        File newFile = new File("app/src/main/resources/movies/tmp.txt");
        try {
            FileWriter fw=new FileWriter(newFile,StandardCharsets.UTF_8, true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("app/src/main/resources/movies/"+ movieTitle.getValue()+".txt"), StandardCharsets.UTF_8)); //getClass().getResourceAsStream("/movieList.txt")
            while ((line = bufferedReader.readLine()) != null) {
                if(reserved.contains(counter+1)){
                    fw.write("true"+System.getProperty( "line.separator" ));
                }
                else {
                    fw.write("false"+System.getProperty( "line.separator" ));
                }
                counter++;
            }
            bufferedReader.close();
            fw.close();

        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        File oldFile = new File("app/src/main/resources/movies/"+ movieTitle.getValue()+".txt");
        oldFile.delete();
        newFile.renameTo(oldFile);
        reserved.clear();
        loadSeats();
    }
    public void returnToList(){
        onReturnToListClicked.run();
    }
}
