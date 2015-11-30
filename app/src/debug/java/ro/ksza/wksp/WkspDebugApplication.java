package ro.ksza.wksp;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp.StethoInterceptor;
import com.squareup.okhttp.OkHttpClient;

public class WkspDebugApplication extends WkspApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

    @Override
    public OkHttpClient getClient() {
        final OkHttpClient client = super.getClient();
        client.networkInterceptors().add(new StethoInterceptor());
        return client;
    }
}
