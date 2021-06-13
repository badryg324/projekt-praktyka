package projekt.hallview;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class HallView {
    public HallView(HallViewModel viewModel, Stage stage)  {


        //PasswordField passwordField = new PasswordField();
        Button logout = new Button("Logout");
        Button exitButton = new Button("Exit");
        Button returnButton = new Button("Return");

        Button buyTicket = new Button("Buy Ticket");
        buyTicket.setId("buyTicket");

        Label goFuckYourSelf = new Label("NWM");
        goFuckYourSelf.setId("hallRepresentation");
        goFuckYourSelf.textProperty().bind(viewModel.movieTitle);

        // Binding z danymi z ViewModel'u
        buyTicket.setOnAction(event -> viewModel.buyTicket());





        // Layout i ustawienie
        GridPane formLayout = new GridPane();
        formLayout.getStyleClass().add("grid");

        //formLayout.setPrefSize(200, 200);
        formLayout.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);

       // ColumnConstraints column1 = new ColumnConstraints(100,100,Double.MAX_VALUE);
       // column1.setHgrow(Priority.ALWAYS);
        //ColumnConstraints column2 = new ColumnConstraints(100);
       // formLayout.getColumnConstraints().addAll(column1, column1, column1, column1, column1, column1);

        Button button1 = new Button("1");
           // button1.setOnAction(event -> viewModel.checkIsFree(1));
        Button button2 = new Button("2");
        Button button3 = new Button("3");
        Button button4 = new Button("4");
        Button button5 = new Button("5");
        Button button6 = new Button("6");
        Button button7 = new Button("7");
        Button button8 = new Button("8");
        Button button9 = new Button("9");
        Button button10 = new Button("10");
        Button button11 = new Button("1");
        Button button12 = new Button("12");
        Button button13 = new Button("13");
        Button button14 = new Button("14");
        Button button15 = new Button("15");
        Button button16 = new Button("16");
        Button button17 = new Button("17");
        Button button18 = new Button("18");
        Button button19 = new Button("19");
        Button button20 = new Button("20");
        Button button21 = new Button("21");
        Button button22 = new Button("22");
        Button button23 = new Button("23");
        Button button24 = new Button("24");
        Button button25 = new Button("25");
        Button button26 = new Button("26");
        Button button27 = new Button("27");
        Button button28 = new Button("28");
        Button button29 = new Button("29");
        Button button30 = new Button("30");


        formLayout.add(button1, 0, 0, 1, 1);
        formLayout.add(button2, 1, 0, 1, 1);
        formLayout.add(button3, 2, 0, 1, 1);
        formLayout.add(button4, 3, 0, 1, 1);
        formLayout.add(button5, 4, 0, 1, 1);
        formLayout.add(button6, 5, 0, 1, 1);
        formLayout.add(button7, 0, 1, 1, 1);
        formLayout.add(button8, 1, 1, 1, 1);
        formLayout.add(button9, 2, 1, 1, 1);
        formLayout.add(button10, 3, 1, 1, 1);
        formLayout.add(button11, 4, 1, 1, 1);
        formLayout.add(button12, 5, 1, 1, 1);
        formLayout.add(button13, 0, 2, 1, 1);
        formLayout.add(button14, 1, 2, 1, 1);
        formLayout.add(button15, 2, 2, 1, 1);
        formLayout.add(button16, 3, 2, 1, 1);
        formLayout.add(button17, 4, 2, 1, 1);
        formLayout.add(button18, 5, 2, 1, 1);
        formLayout.add(button19, 0, 3, 1, 1);
        formLayout.add(button20, 1, 3, 1, 1);
        formLayout.add(button21, 2, 3, 1, 1);
        formLayout.add(button22, 3, 3, 1, 1);
        formLayout.add(button23, 4, 3, 1, 1);
        formLayout.add(button24, 5, 3, 1, 1);
        formLayout.add(button25, 0, 4, 1, 1);
        formLayout.add(button26, 1, 4, 1, 1);
        formLayout.add(button27, 2, 4, 1, 1);
        formLayout.add(button28, 3, 4, 1, 1);
        formLayout.add(button29, 4, 4, 1, 1);
        formLayout.add(button30, 5, 4, 1, 1);




        //
        HBox menu = new HBox();
        menu.getStyleClass().add("menu");
        menu.getChildren().addAll(returnButton,exitButton, logout);

        HBox filmname = new HBox();
        filmname.setAlignment(Pos.CENTER);
        filmname.getChildren().add(goFuckYourSelf);


        HBox downBar = new HBox();
        downBar.getStyleClass().add("downbar");
        downBar.getChildren().addAll(buyTicket);

        VBox center = new VBox();
        center.getChildren().addAll(formLayout,downBar);





        // Layout
        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(5);
        layout.getChildren().addAll(menu,filmname,center);

        Scene hallScene = new Scene(layout);
        hallScene.getStylesheets().add("css/hall-view-style.css");
        stage.setScene(hallScene);
        stage.sizeToScene();
        stage.show();


    }
}
