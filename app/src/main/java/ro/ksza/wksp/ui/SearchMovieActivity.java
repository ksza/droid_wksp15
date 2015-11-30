package ro.ksza.wksp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ro.ksza.wksp.R;

/**
 * Helps search for a movie by title and displays the results in a list. Selecting
 * a movie from the list would return the selected result.
 */
public class SearchMovieActivity extends Activity {

    @Bind(R.id.search_movies_list)
    ListView searchMovieList;

    private SearchMoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        moviesAdapter = new SearchMoviesAdapter(this, SearchMoviesAdapter.createDummyMoviesList(20));
        searchMovieList.setAdapter(moviesAdapter);
    }
}
