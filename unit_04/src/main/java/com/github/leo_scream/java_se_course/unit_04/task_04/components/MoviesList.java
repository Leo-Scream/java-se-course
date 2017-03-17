package com.github.leo_scream.java_se_course.unit_04.task_04.components;

import com.github.leo_scream.java_se_course.unit_04.task_04.business.Movie;
import com.github.leo_scream.java_se_course.unit_04.task_04.stores.MovieStore;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 * @author Denis Verkhoturov, mod.satyr@gmail.com
 */
public class MoviesList {

    private static volatile MoviesList instance;

    @FXML
    private ListView<Movie> moviesList;

    private final MovieStore movieStore;

    private MoviesList() {
        movieStore = MovieStore.getInstance();
    }

    public void initialize() {
        movieStore.onChange(this::renderMoviesList);
        renderMoviesList();
    }

    private void renderMoviesList() {
        movieStore.getMovies().forEach(moviesList.getItems()::add);
    }

    public void minimize() {
        Main.getInstance().getGridPane().setColumnSpan(moviesList, 2);
    }

    public void maximize() {
        Main.getInstance().getGridPane().setColumnSpan(moviesList, 5);
    }

    public static MoviesList getInstance() {
        MoviesList localInstance = instance;
        if (localInstance == null) {
            synchronized (MoviesList.class) {
                localInstance = instance;
                if (localInstance == null) {
                    localInstance = instance = new MoviesList();
                }
            }
        }
        return localInstance;
    }
}
