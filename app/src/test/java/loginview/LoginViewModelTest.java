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
        model.username().setValue("");
        model.password().setValue("");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }

    @Test
    public void cannotLoginWithoutPassword() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.username().setValue("user1");
        model.password().setValue("");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }
    @Test
    public void cannotLoginWithoutUsername() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.username().setValue("");
        model.password().setValue("password1");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }
    @Test
    public void cannotLoginWhenPasswordHasLessThanThreeSignsOfPass() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.username().setValue("user1");
        model.password().setValue("pas");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }

    @Test
    public void cannotLoginWhenPasswordHasLessThanSevenSigns() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.username().setValue("user1");
        model.password().setValue("passwo");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }

    @Test
    public void cannotLoginWhenUserLessThanThreeSigns() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.username().setValue("us");
        model.password().setValue("password1");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }

    @Test
    public void cannotLoginWhenUserMoreThanThirtySigns() {
        //given
        LoginViewModel model = new LoginViewModel(new UserAuthentiactor(),() -> {});
        //when
        model.username().setValue("user12345123451234512345123451212345");
        model.password().setValue("password1");

        //then
        Assertions.assertThat(model.cannotLogin().getValue()).isTrue();
    }

}