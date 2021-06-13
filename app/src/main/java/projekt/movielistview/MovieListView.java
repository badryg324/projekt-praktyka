package projekt.movielistview;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ListBinding;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    public MovieListView(MovieListViewModel viewModel, Stage stage) {


        VBox scene = new VBox();

        Label header = new Label("Upcoming films:");
        scene.getChildren().add(header);

        VBox movies = new VBox();
        //scene.getChildren().add(movies);

        ScrollPane moviesScrollPane = new ScrollPane();
        moviesScrollPane.setContent(movies);
        moviesScrollPane.setPrefHeight(400);
        scene.getChildren().add(moviesScrollPane);

        VBox adminUsage = new VBox();

        TextField titleArea = new TextField();
        titleArea.setPromptText("Title");
        TextField directorArea = new TextField();
        directorArea.setPromptText("Director");
        TextField hallArea = new TextField();
        hallArea.setPromptText("Hall");
        TextField dateArea = new TextField();
        dateArea.setPromptText("Date");
        Button addPosition = new Button("Add position");

        addPosition.disableProperty().bind(viewModel.cannotAddPosition());
        addPosition.setOnAction(event -> viewModel.addPosition());



        HBox adminVariables = new HBox();
        adminVariables.getChildren().addAll(titleArea, directorArea, hallArea, dateArea, addPosition);

        Label adminTools = new Label("Admin tools");


        adminUsage.getChildren().addAll(adminTools, adminVariables);
        adminUsage.visibleProperty().bind(viewModel.isAdmin);
        adminUsage.managedProperty().bind(viewModel.isAdmin);


        scene.setPadding(new Insets(10));
        scene.setSpacing(5);

        scene.getChildren().add(adminUsage);




        // bindings


        titleArea.textProperty().bindBidirectional(viewModel.getTitleArea());
        directorArea.textProperty().bindBidirectional(viewModel.getDirectorArea());
        hallArea.textProperty().bindBidirectional(viewModel.getHallArea());
        dateArea.textProperty().bindBidirectional(viewModel.getDateArea());
        Bindings.bindContentBidirectional(viewModel.movieList, movies.getChildren());


        viewModel.readMovies();

        stage.setScene(new Scene(scene));
        stage.sizeToScene();
        stage.setResizable(false);
        stage.show();

    }


}
