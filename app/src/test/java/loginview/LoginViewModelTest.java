package loginview;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import projekt.authentication.UserAuthentiactor;
import projekt.loginview.LoginViewModel;


public class LoginViewModelTest {

    @Test
    public void cannotLoginWithoutPasswordAndUsername() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.usernameProperty().setValue("");
        model.passwordProperty().setValue("");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }

    @Test
    public void cannotLoginWithoutPassword() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.usernameProperty().setValue("user1");
        model.passwordProperty().setValue("");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }
    @Test
    public void cannotLoginWithoutUsername() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.usernameProperty().setValue("");
        model.passwordProperty().setValue("password1");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }
    @Test
    public void cannotLoginWhenPasswordHasLessThanThreeSignsOfPass() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.usernameProperty().setValue("user1");
        model.passwordProperty().setValue("pas");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }

    @Test
    public void cannotLoginWhenPasswordHasLessThanSevenSigns() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.usernameProperty().setValue("user1");
        model.passwordProperty().setValue("passwo");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }

    @Test
    public void cannotLoginWhenUserLessThanThreeSigns() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.usernameProperty().setValue("us");
        model.passwordProperty().setValue("password1");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }

    @Test
    public void cannotLoginWhenUserMoreThanThirtySigns() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.usernameProperty().setValue("user12345123451234512345123451212345");
        model.passwordProperty().setValue("password1");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }

}