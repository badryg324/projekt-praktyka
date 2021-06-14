package projekt.hallview;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;



public class HallView {

    public HallView(HallViewModel viewModel, Stage stage)  {
        int numCols = 6;
        int numRows = 5;



        Button returnButton = new Button("Powrót");
        returnButton.setOnAction(event -> viewModel.returnToList());

        Button reserveButton = new Button("Zarezerwuj miejsca");
        reserveButton.setId("buyTicket");


        Label header = new Label("NWM");
        header.setId("hallRepresentation");
        header.textProperty().bind(viewModel.movieTitle);

        // Binding z danymi z ViewModel'u
        reserveButton.setOnAction(event -> viewModel.reserveSeats());





        // Layout i ustawienie
        VBox formLayout = new VBox();
        formLayout.getStyleClass().add("grid");


        formLayout.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
       /* for(int i=0;i<numCols;i++){
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0/numCols);
            formLayout.getColumnConstraints().add(colConst);
        }
        for(int i=0;i<numRows;i++){
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0/numRows);
            formLayout.getRowConstraints().add(rowConst);
        }*/
        Bindings.bindContentBidirectional(formLayout.getChildren(),viewModel.seats);











        HBox menu = new HBox();
        menu.getStyleClass().add("menu");
        menu.getChildren().addAll(returnButton);

        HBox filmname = new HBox();
        filmname.setAlignment(Pos.CENTER);
        filmname.getChildren().add(header);


        HBox downBar = new HBox();
        downBar.getStyleClass().add("downbar");
        downBar.getChildren().addAll(reserveButton);

        VBox center = new VBox();
        center.getChildren().addAll(formLayout,downBar);





        // Layout
        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(5);
        layout.getChildren().addAll(menu,filmname,center);

        Scene hallScene = new Scene(layout);
        hallScene.getStylesheets().add("css/hall-view-style.css");
        viewModel.loadSeats();
        stage.setScene(hallScene);
        stage.sizeToScene();
        stage.show();


    }
}
