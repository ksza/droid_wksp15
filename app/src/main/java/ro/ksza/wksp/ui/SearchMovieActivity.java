package ro.ksza.wksp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import ro.ksza.wksp.R;
import ro.ksza.wksp.omdb.SearchListener;
import ro.ksza.wksp.omdb.SearchTask;
import ro.ksza.wksp.omdb.model.OmdbMovie;
import ro.ksza.wksp.omdb.model.OmdbSearchMovies;

/**
 * Helps search for a movie by title and displays the results in a list. Selecting
 * a movie from the list would return the selected result.
 */
public class SearchMovieActivity extends AppCompatActivity implements SearchListener {

    private static final Logger logger = LoggerFactory.getLogger(SearchMovieActivity.class);

    public static final int SEARCH_MOVIE_REQUEST_CODE = 1;
    public static final String SELECTED_ITEM_KEY = "SELECTED_ITEM_KEY";

    @Bind(R.id.search_movies_list)
    ListView searchMovieList;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.searchText)
    EditText searchText;

    private SearchMoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        initToolbar();

        moviesAdapter = new SearchMoviesAdapter(this);
        searchMovieList.setAdapter(moviesAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.do_search) {
            initSearch();
        }

        return super.onOptionsItemSelected(item);
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

    public void initSearch() {
        new SearchTask(this).execute(searchText.getText().toString());
    }

    @OnItemClick(R.id.search_movies_list)
    public void onItemClick(int position) {
        final OmdbMovie clickedItem = moviesAdapter.getItem(position);
        logger.debug("Selected Movie: " + clickedItem);

        final Intent intent = this.getIntent();
        intent.putExtra(SELECTED_ITEM_KEY, clickedItem);
        this.setResult(RESULT_OK, intent);

        finish();
    }

    public static Intent createSearchMovieIntent(final Context context) {
        return new Intent(context, SearchMovieActivity.class);
    }

    @Override
    public void searchReady(OmdbSearchMovies searchMovies) {
        logger.debug("Search Ready: " + searchMovies);
        moviesAdapter.replace(searchMovies.movies);
    }
}
