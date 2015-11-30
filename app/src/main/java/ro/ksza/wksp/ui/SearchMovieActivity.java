package ro.ksza.wksp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.Bind;
import butterknife.OnItemClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import ro.ksza.wksp.R;
import ro.ksza.wksp.WkspApplication;
import ro.ksza.wksp.omdb.OmdbApi;
import ro.ksza.wksp.omdb.model.OmdbMovie;
import ro.ksza.wksp.omdb.model.OmdbSearchMovies;

/**
 * Helps search for a movie by title and displays the results in a list. Selecting
 * a movie from the list would return the selected result.
 */
public class SearchMovieActivity extends BaseActivity {

    private static final Logger logger = LoggerFactory.getLogger(SearchMovieActivity.class);

    public static final int SEARCH_MOVIE_REQUEST_CODE = 1;
    public static final String SELECTED_ITEM_KEY = "SELECTED_ITEM_KEY";

    @Bind(R.id.search_movies_list)
    ListView searchMovieList;

    @Bind(R.id.searchText)
    EditText searchText;

    private SearchMoviesAdapter moviesAdapter;

    private OmdbApi omdbApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        omdbApi = WkspApplication.getInstance().getOmdbApi();

        setContentView(R.layout.activity_search);

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
        final String searchString = searchText.getText().toString();
        if(!TextUtils.isEmpty(searchString)) {
            omdbApi.searchByTitle(searchString)
                    .enqueue(new CallbackListener());
        }
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

    private class CallbackListener implements Callback<OmdbSearchMovies> {

        @Override
        public void onResponse(Response<OmdbSearchMovies> response, Retrofit retrofit) {
            logger.debug("Search Ready: " + response.body());
            if(response.body() != null && response.body().movies != null) {
                moviesAdapter.replace(response.body().movies);
            }
        }

        @Override
        public void onFailure(Throwable t) {
            logger.error("Error while requesting: {}", t.getMessage());
        }
    }
}
