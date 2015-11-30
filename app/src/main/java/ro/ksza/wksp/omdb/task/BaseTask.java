package ro.ksza.wksp.omdb.task;

import android.net.Uri;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.ksza.wksp.WkspApplication;
import ro.ksza.wksp.omdb.model.OmdbSearchMovies;

/**
 * Created by karoly.szanto on 30/11/15.
 */
public abstract class BaseTask extends AsyncTask<String, Void, OmdbSearchMovies> {

    private static final Logger logger = LoggerFactory.getLogger(BaseTask.class);

    private final static String baseUrl = "http://www.omdbapi.com/";

    private final SearchListener searchListener;

    protected final Gson converter;

    public BaseTask(final SearchListener searchListener) {
        this.searchListener = searchListener;
        converter = WkspApplication.getInstance().getGson();
    }

    protected String createEncodedSearchUrl(final String movieTitle) {
        return Uri.parse(baseUrl)
                .buildUpon()
                .appendQueryParameter("s", movieTitle)
                .build()
                .toString();
    }

    @Override
    protected void onPostExecute(OmdbSearchMovies omdbSearchMovies) {
        super.onPostExecute(omdbSearchMovies);
        logger.debug("DONE: " + omdbSearchMovies);

        if(searchListener != null) {
            searchListener.searchReady(omdbSearchMovies);
        }
    }
}
