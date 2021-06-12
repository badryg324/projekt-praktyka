package projekt;

import javafx.application.Application;
import javafx.stage.Stage;
import projekt.hallview.HallView;
import projekt.hallview.HallViewFactory;
import projekt.hallview.HallViewModel;
import projekt.loginview.LoginView;
import projekt.loginview.LoginViewFactory;
import projekt.loginview.LoginViewModel;


public class    FxApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //LoginViewFactory loginViewFactory = new LoginViewFactory();
        //LoginViewModel viewModel = new LoginViewModel();

        //LoginView loginView = loginViewFactory.create(viewModel, primaryStage);

        HallViewFactory hallViewFactory = new HallViewFactory();
        HallViewModel viewModel = new HallViewModel();

        HallView hallView = hallViewFactory.create(viewModel, primaryStage);

    }
}
