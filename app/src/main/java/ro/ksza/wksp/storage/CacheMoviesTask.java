package ro.ksza.wksp.storage;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.util.List;

import ro.ksza.wksp.WkspApplication;
import ro.ksza.wksp.omdb.model.OmdbMovie;

/**
 * Created by karoly.szanto on 30/11/15.
 */
public class CacheMoviesTask extends AsyncTask<List<OmdbMovie>, Void, Boolean> {

    private static final String WISHLIST_FILE_NAME = "wishlist";

    private final Gson converter;
    private final Context context;

    public CacheMoviesTask() {
        converter = WkspApplication.getInstance().getGson();
        context = WkspApplication.getInstance();
    }

    @Override
    protected Boolean doInBackground(List<OmdbMovie>... params) {

        final String serialisedList = converter.toJson(params[0]);

        return FileUtils.writeToFile(WISHLIST_FILE_NAME, serialisedList, context);
    }
}
