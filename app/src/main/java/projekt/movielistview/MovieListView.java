package projekt.movielistview;

import javafx.beans.binding.ListBinding;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MovieListView {
    public MovieListView(MovieListViewModel viewModel, Stage stage){

        String pom;
        String title;
        String director;
        String hall;
        String date;

        ArrayList<HBox> boxes = new ArrayList<HBox>();
        Label filmList = new Label("Film list:");

        HBox greetings = new HBox();
        greetings.getChildren().add(filmList);
        boxes.add(greetings);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/movieList.txt"), StandardCharsets.UTF_8));

        try {
            while ((pom = bufferedReader.readLine()) != null) {

                title = pom.substring(0, pom.indexOf(';'));
                pom = pom.substring(pom.indexOf(';')+1);
                director = pom.substring(0, pom.indexOf(';'));
                pom = pom.substring(pom.indexOf(';')+1);
                hall = pom.substring(0, pom.indexOf(';'));
                pom = pom.substring(pom.indexOf(';')+1);
                date = pom.substring(0, pom.indexOf(';'));

                HBox list = new HBox();

                Button butt = new Button();
                butt.setOnAction(event -> viewModel.addHallReference());

                list.getChildren().addAll(butt, new Label(title), new Label(director), new Label(hall), new Label(date), new Button("Delete"));
                boxes.add(list);
            }
            bufferedReader.close();
        } catch(IOException e){
            System.out.println("problem");
        }









        VBox adminUsage = new VBox();

        TextField titleArea = new TextField();
        titleArea.setPromptText("Title");
        TextField directorArea = new TextField();
        directorArea.setPromptText("Director");
        TextField hallArea = new TextField();
        hallArea.setPromptText("Hall");
        TextField dateArea= new TextField();
        dateArea.setPromptText("Date");
        Button addPosition= new Button("Add position");

        addPosition.disableProperty().bind(viewModel.cannotAddPosition());
        addPosition.setOnAction(event -> viewModel.addPosition());





        HBox adminVariables = new HBox();
        adminVariables.getChildren().addAll(titleArea,directorArea,hallArea,dateArea,addPosition);

        Label adminTools = new Label("Admin tools");


        adminUsage.getChildren().addAll(adminTools, adminVariables);
        adminUsage.visibleProperty().bind(viewModel.isAdmin);
        adminUsage.managedProperty().bind(viewModel.isAdmin);












        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(5);
        layout.getChildren().addAll(boxes);
        layout.getChildren().add(adminUsage);

        stage.setScene(new Scene(layout));
        stage.show();


        // bindings



        titleArea.textProperty().bindBidirectional(viewModel.getAreaProperty());
        directorArea.textProperty().bindBidirectional(viewModel.getDirectorArea());
        hallArea.textProperty().bindBidirectional(viewModel.getHallArea());
        dateArea.textProperty().bindBidirectional(viewModel.getDateArea());





    }



}
