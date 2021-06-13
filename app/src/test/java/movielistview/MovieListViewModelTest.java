package movielistview;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import projekt.movielistview.MovieListViewModel;
import projekt.utils.Movie;

public class MovieListViewModelTest {

    @Test
    public void AddPositionDontEnableWhenAllTextFieldsIsEmpty() {
        //given
        Movie movie = new Movie();
        MovieListViewModel model = new MovieListViewModel(true, movie,() -> {});
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
        Movie movie = new Movie();
        MovieListViewModel model = new MovieListViewModel(true, movie,() -> {});
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
        Movie movie = new Movie();
        MovieListViewModel model = new MovieListViewModel(true, movie,() -> {});
        //when
        model.getTitleArea().setValue("sdasd");
        model.getDirectorArea().setValue(null);
        model.getHallArea().setValue(null);
        model.getDateArea().setValue(null);
        //then
        Assertions.assertThat(model.cannotAddPosition().getValue()).isTrue();
    }




}