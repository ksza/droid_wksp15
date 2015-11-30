package ro.ksza.wksp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ro.ksza.wksp.R;
import ro.ksza.wksp.omdb.model.OmdbMovie;

public class WishListActivity extends AppCompatActivity {

    private static final Logger logger = LoggerFactory.getLogger(WishListActivity.class);

    @Bind(R.id.movies_list)
    MoviesRecycler moviesRecycler;

    @Bind(R.id.empty_recycler)
    View emptyRecyclerView;

    private LinearLayoutManager cardListLayoutManager;
    private MoviesAdapter moviesListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        moviesListAdapter = new MoviesAdapter(this);
        cardListLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        moviesRecycler.setLayoutManager(cardListLayoutManager);
        moviesRecycler.setEmptyView(emptyRecyclerView);
        moviesRecycler.setAdapter(moviesListAdapter);

        logger.debug("WishListActivity created");
    }

    @OnClick(R.id.search_button)
    public void onSearchClick() {
        final Intent searchMovieIntent = SearchMovieActivity.createSearchMovieIntent(this);
        startActivityForResult(searchMovieIntent, SearchMovieActivity.SEARCH_MOVIE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SearchMovieActivity.SEARCH_MOVIE_REQUEST_CODE && resultCode == RESULT_OK) {
            final OmdbMovie selectedMovie = (OmdbMovie) data.getExtras().getSerializable(SearchMovieActivity.SELECTED_ITEM_KEY);
            logger.debug("User selected movie: " + selectedMovie);
        }
    }
}
