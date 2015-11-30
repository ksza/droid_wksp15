package ro.ksza.wksp.omdb.task;

import ro.ksza.wksp.omdb.model.OmdbSearchMovies;

/**
 * Created by karoly.szanto on 30/11/15.
 */
public interface SearchListener {
    void searchReady(final OmdbSearchMovies searchMovies);
}
