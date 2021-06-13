package projekt.loginview;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LoginView {
    public LoginView(LoginViewModel viewModel, Stage stage) {
        TextField usernameTextField = new TextField();
        PasswordField passwordField = new PasswordField();
        Label greetings = new Label("Cinema Lublin<3");
        greetings.getStyleClass().add("greetings");
        Label usernameHintLabel = new Label();
        Label passwordHintLabel = new Label();
        Button loginButton = new Button("Login");
        Button cancelButton = new Button("Cancel");

        // Binding z danymi z ViewModel'u
        usernameTextField.textProperty().bindBidirectional(viewModel.usernameProperty());
        usernameTextField.disableProperty().bind(viewModel.cannotChangeUsername());

        passwordField.textProperty().bindBidirectional(viewModel.passwordProperty());
        passwordField.disableProperty().bind(viewModel.cannotChangePassword());

        usernameHintLabel.textProperty().bind(viewModel.usernameHint());
        usernameHintLabel.visibleProperty().bind(viewModel.shouldShowUsernameHint());
        usernameHintLabel.managedProperty().bind(viewModel.shouldShowUsernameHint());

        passwordHintLabel.textProperty().bind(viewModel.passwordHint());
        passwordHintLabel.visibleProperty().bind(viewModel.shouldShowPasswordHint());
        passwordHintLabel.managedProperty().bind(viewModel.shouldShowPasswordHint());

        loginButton.disableProperty().bind(viewModel.cannotLogin());
        loginButton.setOnAction(event -> viewModel.loginClicked());

        cancelButton.setOnAction(event -> viewModel.cancelLoginClicked());

        // Layout i ustawienie
        GridPane formLayout = new GridPane();
        formLayout.addRow(formLayout.getRowCount(), new Label("Username:"), new VBox(usernameTextField, usernameHintLabel));
        formLayout.addRow(formLayout.getRowCount(), new Label("Password:"), new VBox(passwordField, passwordHintLabel));


        formLayout.setAlignment(Pos.BASELINE_CENTER);


        usernameTextField.setMaxHeight(Double.MAX_VALUE);
        passwordField.setMaxWidth(Double.MAX_VALUE);

        formLayout.setHgap(5);
        formLayout.setVgap(5);

        ColumnConstraints leftColumn = new ColumnConstraints();
        leftColumn.setHalignment(HPos.RIGHT);
        formLayout.getColumnConstraints().addAll(leftColumn);

        GridPane.setHgrow(usernameTextField, Priority.ALWAYS);
        GridPane.setHgrow(passwordField, Priority.ALWAYS);

        // Przyciski
        HBox buttons = new HBox();
        buttons.setSpacing(5);
        buttons.setAlignment(Pos.BASELINE_CENTER);
        buttons.getChildren().addAll(cancelButton, loginButton);

        // Layout
        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(5);
        layout.getChildren().addAll(greetings,formLayout, buttons);

        Scene loginscene= new Scene(layout);

        loginscene.getStylesheets().add("css/login-view-style.css");
        stage.setScene(loginscene);
        stage.setWidth(600);
        stage.setHeight(200);

        stage.show();
    }
}

