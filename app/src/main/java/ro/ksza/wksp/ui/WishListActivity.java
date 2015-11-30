package ro.ksza.wksp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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

        setUpRecycler();

        logger.debug("WishListActivity created");
    }

    private void setUpRecycler() {
        moviesListAdapter = new MoviesAdapter(this);
        cardListLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        moviesRecycler.setLayoutManager(cardListLayoutManager);
        moviesRecycler.setEmptyView(emptyRecyclerView);
        moviesRecycler.setAdapter(moviesListAdapter);

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                final int position = viewHolder.getAdapterPosition();
                final OmdbMovie item = moviesListAdapter.getItemAt(position);

                moviesListAdapter.removeItemAt(position);

                final Snackbar snackBar = Snackbar.make(moviesRecycler, R.string.removed_from_wishlist, Snackbar.LENGTH_SHORT);
                snackBar.setAction(R.string.undo_remove, new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        moviesListAdapter.insertItemAt(item, position);
                    }
                });
                snackBar.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(moviesRecycler);
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

            moviesListAdapter.insertItemAtEnd(selectedMovie);
        }
    }
}
