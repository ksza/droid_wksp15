package ro.ksza.wksp;

import android.app.Application;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import ro.ksza.wksp.omdb.OmdbApi;

/**
 * Created by karoly.szanto on 29/11/15.
 */
public class WkspApplication extends Application {

    private static final Logger logger = LoggerFactory.getLogger(WkspApplication.class);

    private static WkspApplication instance;

    public static WkspApplication getInstance() {
        return instance;
    }

    private Gson converter;
    private OkHttpClient client;

    private OmdbApi omdbApi;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        converter = new Gson();
        client = createClient();
        omdbApi = buildApi();

        logger.debug("Created custom application");
    }

    public OmdbApi getOmdbApi() {
        return omdbApi;
    }

    public Gson getGson() {
        return converter;
    }

    public OkHttpClient getClient() {
        return client;
    }

    private OmdbApi buildApi() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create(converter))
                .client(client)
                .build();

        return retrofit.create(OmdbApi.class);
    }

    protected OkHttpClient createClient() {
        return new OkHttpClient();
    }
}
