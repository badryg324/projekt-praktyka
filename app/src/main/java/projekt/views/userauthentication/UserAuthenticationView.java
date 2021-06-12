package projekt.views.userauthentication;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class UserAuthenticationView {
    public UserAuthenticationView(UserAuthenticationViewModel viewModel, Stage stage) {
        // Interactive components
        TextField usernameTextField = new TextField();
        PasswordField passwordField = new PasswordField();
        Label usernameHintLabel = new Label("Something is not yes");
        Label passwordHintLabel = new Label("Something is not yes");
        Button loginButton = new Button("Zaloguj");

        // Bindings
        usernameTextField.disableProperty().bind(viewModel.cannotChangeUsername());
        usernameTextField.textProperty().bindBidirectional(viewModel.enteredUsername());

        passwordField.disableProperty().bind(viewModel.cannotChangePassword());
        passwordField.textProperty().bindBidirectional(viewModel.enteredPassword());

        usernameHintLabel.textProperty().bind(viewModel.usernameHintMessage());
        usernameHintLabel.visibleProperty().bind(viewModel.shouldShowUsernameHint());
        usernameHintLabel.managedProperty().bind(viewModel.shouldShowUsernameHint());

        passwordHintLabel.textProperty().bind(viewModel.passwordHintMessage());
        passwordHintLabel.visibleProperty().bind(viewModel.shouldShowPasswordHint());
        passwordHintLabel.managedProperty().bind(viewModel.shouldShowPasswordHint());

        loginButton.disableProperty().bind(viewModel.cannotRequestLogin());
        loginButton.setOnAction(event -> viewModel.loginRequested());

        // Window layout
        GridPane form = new GridPane();
        form.setHgap(5);
        form.setVgap(5);
        form.addRow(0, new Label("Login:"), new VBox(usernameTextField, usernameHintLabel));
        form.addRow(1, new Label("Password:"), new VBox(passwordField, passwordHintLabel));

        ColumnConstraints leftColumn = new ColumnConstraints();
        leftColumn.setHalignment(HPos.RIGHT);
        ColumnConstraints rightColumn = new ColumnConstraints();
        rightColumn.setHalignment(HPos.LEFT);
        rightColumn.setHgrow(Priority.ALWAYS);
        form.getColumnConstraints().addAll(leftColumn, rightColumn);

        RowConstraints usernameRow = new RowConstraints();
        usernameRow.setValignment(VPos.BASELINE);
        RowConstraints passwordRow = new RowConstraints();
        passwordRow.setValignment(VPos.BASELINE);
        form.getRowConstraints().addAll(usernameRow, passwordRow);

        HBox actionButtonsLayout = new HBox();
        actionButtonsLayout.setAlignment(Pos.BASELINE_RIGHT);
        actionButtonsLayout.getChildren().addAll(
                loginButton
        );

        VBox layout = new VBox();
        layout.setPadding(new Insets(5));
        layout.setSpacing(5);
        layout.getChildren().addAll(
                form,
                actionButtonsLayout
        );

        // Window
        stage.setScene(new Scene(layout));
        stage.show();
    }
}
