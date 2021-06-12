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
import javafx.stage.Stage;

public class LoginView {
    public LoginView(LoginViewModel viewModel, Stage stage) {
        TextField usernameTextField = new TextField();
        PasswordField passwordField = new PasswordField();
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
        buttons.setAlignment(Pos.BASELINE_RIGHT);
        buttons.getChildren().addAll(cancelButton, loginButton);

        // Layout
        VBox layout = new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(5);
        layout.getChildren().addAll(formLayout, buttons);

        stage.setScene(new Scene(layout));
        stage.show();
    }
}

