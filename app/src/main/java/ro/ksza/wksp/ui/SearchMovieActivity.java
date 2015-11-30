package ro.ksza.wksp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import ro.ksza.wksp.R;

/**
 * Helps search for a movie by title and displays the results in a list. Selecting
 * a movie from the list would return the selected result.
 */
public class SearchMovieActivity extends AppCompatActivity {

    private static final Logger logger = LoggerFactory.getLogger(SearchMovieActivity.class);

    public static final int SEARCH_MOVIE_REQUEST_CODE = 1;
    public static final String SELECTED_ITEM_KEY = "SELECTED_ITEM_KEY";

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

    @OnItemClick(R.id.search_movies_list)
    public void onItemClick(int position) {
        final String clickedItem = moviesAdapter.getItem(position);
        logger.debug("Selected Movie: " + clickedItem);

        final Intent intent = this.getIntent();
        intent.putExtra(SELECTED_ITEM_KEY, clickedItem);
        this.setResult(RESULT_OK, intent);

        finish();
    }

    public static Intent createSearchMovieIntent(final Context context) {
        return new Intent(context, SearchMovieActivity.class);
    }
}
