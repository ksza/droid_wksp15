package ro.ksza.wksp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ro.ksza.wksp.R;

/**
 * Helps search for a movie by title and displays the results in a list. Selecting
 * a movie from the list would return the selected result.
 */
public class SearchMovieActivity extends AppCompatActivity {

    @Bind(R.id.search_movies_list)
    ListView searchMovieList;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private SearchMoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        initToolbar();

        moviesAdapter = new SearchMoviesAdapter(this, SearchMoviesAdapter.createDummyMoviesList(20));
        searchMovieList.setAdapter(moviesAdapter);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
