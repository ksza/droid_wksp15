package ro.ksza.wksp;

import android.app.Application;

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

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        logger.debug("Created custom application");
    }
}
