package movielistview;

import junit.framework.TestCase;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import projekt.movielistview.MovieListViewModel;
import projekt.utils.MovieTitle;

public class MovieListViewModelTest {

    @Test
    public void AddPositionDontEnableWhenAllTextFieldsIsEmpty() {
        //given
        MovieTitle movieTitle = new MovieTitle();
        MovieListViewModel model = new MovieListViewModel(true,movieTitle ,() -> {});
        //when
        model.getTitleArea().setValue("");
        model.getDirectorArea().setValue("");
        model.getHallArea().setValue("");
        model.getDateArea().setValue("");
        //then
        Assertions.assertThat(model.cannotAddPosition().getValue()).isTrue();
    }
    @Test
    public void AddPositionDontEnableWhenOneOfTheTextFieldsIsEmpty() {
        //given
        MovieTitle movieTitle = new MovieTitle();
        MovieListViewModel model = new MovieListViewModel(true,movieTitle ,() -> {});
        //when
        model.getTitleArea().setValue("aaa");
        model.getDirectorArea().setValue("aaa");
        model.getHallArea().setValue("aaa");
        model.getDateArea().setValue("");
        //then
        Assertions.assertThat(model.cannotAddPosition().getValue()).isTrue();
    }

    @Test
    public void AddPositionDontEnableWhenOneOfTheTextFieldsIsNull() {
        //given
        MovieTitle movieTitle = new MovieTitle();
        MovieListViewModel model = new MovieListViewModel(true,movieTitle ,() -> {});
        //when
        model.getTitleArea().setValue("sdasd");
        model.getDirectorArea().setValue(null);
        model.getHallArea().setValue(null);
        model.getDateArea().setValue(null);
        //then
        Assertions.assertThat(model.cannotAddPosition().getValue()).isTrue();
    }




}