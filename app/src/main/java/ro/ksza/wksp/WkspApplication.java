package ro.ksza.wksp;

import android.app.Application;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @Override
    public void onCreate() {
        super.onCreate();

        converter = new Gson();
        client = getClient();

        instance = this;

        logger.debug("Created custom application");
    }

    public Gson getGson() {
        return converter;
    }

    public OkHttpClient getClient() {
        return client;
    }
}
