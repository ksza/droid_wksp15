package ro.ksza.wksp.storage;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Text;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ro.ksza.wksp.WkspApplication;
import ro.ksza.wksp.omdb.model.OmdbMovie;

public class CachedMoviesLoader extends AsyncTaskLoader<List<OmdbMovie>> {

    private final static Logger logger = LoggerFactory.getLogger(CachedMoviesLoader.class);

    private static final String WISHLIST_FILE_NAME = "wishlist";

    private final InterestingConfigChanges mLastConfig = new InterestingConfigChanges();

    private List<OmdbMovie> mData = new ArrayList<>();

    private final Gson converter;

    public CachedMoviesLoader(Context context) {
        super(context);
        converter = WkspApplication.getInstance().getGson();
    }

    /**
     * This is where the bulk of our work is done.  This function is
     * called in a background thread and should generate a new set of
     * data to be published by the loader.
     */
    @Override
    public List<OmdbMovie> loadInBackground() {

        String rawFileContent = FileUtils.readFromFile(WISHLIST_FILE_NAME, getContext());
        Type listType = new TypeToken<List<OmdbMovie>>() {}.getType();

        logger.debug("---> {}", rawFileContent);

        if(TextUtils.isEmpty(rawFileContent)) {
            /* empty list */
            rawFileContent = "[]";
        }

        return converter.fromJson(rawFileContent, listType);
    }

    /**
     * Called when there is new data to deliver to the client.  The
     * super class will take care of delivering it; the implementation
     * here just adds a little more logic.
     */
    @Override
    public void deliverResult(final List<OmdbMovie> data) {
        if (isReset()) {
            // An async query came in while the loader is stopped.  We don't need the result.
            if (data != null) {
                onReleaseResources(data);
            }
        }
        List<OmdbMovie> oldData = data;
        mData = data;

        if (isStarted()) {
            // If the Loader is currently started, we can immediately deliver its results.
            super.deliverResult(data);
        }

        // At this point we can release the resources associated with 'oldData' if needed; now that the new result is delivered we
        // know that it is no longer in use.
        if (oldData != null) {
            onReleaseResources(oldData);
        }
    }

    /**
     * Handles a request to start the Loader.
     */
    @Override
    protected void onStartLoading() {
        if (mData != null) {
            // If we currently have a result available, deliver it immediately.
            deliverResult(mData);
        }

        // Has something interesting in the configuration changed since we last built the list?
        boolean configChange = mLastConfig.applyNewConfig(getContext().getResources());

        if (takeContentChanged() || mData == null || configChange) {
            // If the data has changed since the last time it was loaded or is not currently available, start a load.
            forceLoad();
        }
    }

    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    /**
     * Handles a request to cancel a load.
     */
    @Override
    public void onCanceled(List<OmdbMovie> data) {
        super.onCanceled(data);

        // At this point we can release the resources associated with 'data' if needed.
        onReleaseResources(data);
    }

    /**
     * Handles a request to completely reset the Loader.
     */
    @Override
    protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        // At this point we can release the resources associated with 'data'
        // if needed.
        if (mData != null) {
            onReleaseResources(mData);
            mData = null;
        }
    }

    /**
     * Helper function to take care of releasing resources associated
     * with an actively loaded data set.
     */
    protected void onReleaseResources(List<OmdbMovie> data) {
        // For a simple List<> there is nothing to do.  For something
        // like a Cursor, we would close it here.
    }

    /**
     * Helper for determining if the configuration has changed in an interesting
     * way so we need to rebuild the app list.
     */
    public static class InterestingConfigChanges {
        final Configuration mLastConfiguration = new Configuration();
        int mLastDensity;

        boolean applyNewConfig(Resources res) {
            int configChanges = mLastConfiguration.updateFrom(res.getConfiguration());
            boolean densityChanged = mLastDensity != res.getDisplayMetrics().densityDpi;
            if (densityChanged || (configChanges & (ActivityInfo.CONFIG_LOCALE
                    | ActivityInfo.CONFIG_UI_MODE | ActivityInfo.CONFIG_SCREEN_LAYOUT)) != 0) {
                mLastDensity = res.getDisplayMetrics().densityDpi;
                return true;
            }
            return false;
        }
    }
}
