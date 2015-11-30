package ro.ksza.wksp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ro.ksza.wksp.R;

public class WishListActivity extends AppCompatActivity {

    private static final Logger logger = LoggerFactory.getLogger(WishListActivity.class);

    @Bind(R.id.hello_text_view)
    TextView helloView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        logger.debug("WishListActivity created");
    }

    @OnClick(R.id.search_button)
    public void onHelloButtonClick() {
        final Intent searchMovieIntent = SearchMovieActivity.createSearchMovieIntent(this);
        startActivityForResult(searchMovieIntent, SearchMovieActivity.SEARCH_MOVIE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SearchMovieActivity.SEARCH_MOVIE_REQUEST_CODE && resultCode == RESULT_OK) {
            final String selectedMovie = data.getExtras().getString(SearchMovieActivity.SELECTED_ITEM_KEY);
            logger.debug("User selected movie: " + selectedMovie);
        }
    }
}
