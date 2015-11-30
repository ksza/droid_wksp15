package ro.ksza.wksp.omdb.task;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import ro.ksza.wksp.WkspApplication;
import ro.ksza.wksp.omdb.model.OmdbSearchMovies;

/**
 * Created by karoly.szanto on 30/11/15.
 */
public class ImprovedSearchTask extends BaseTask {

    private final OkHttpClient client;

    public ImprovedSearchTask(final SearchListener searchListener) {
        super(searchListener);
        client = WkspApplication.getInstance().createClient();
    }

    @Override
    protected OmdbSearchMovies doInBackground(String... params) {
        final Request request = new Request.Builder()
                .url(createEncodedSearchUrl(params[0]))
                .method("GET", null)
                .build();

        String rawResponse = "";

        try {
            Response response = client.newCall(request).execute();
            rawResponse = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return converter.fromJson(rawResponse, OmdbSearchMovies.class);
    }
}
