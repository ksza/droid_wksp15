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
    protected OkHttpClient createClient() {
        final OkHttpClient client = super.createClient();
        client.networkInterceptors().add(new StethoInterceptor());
        return client;
    }
}
