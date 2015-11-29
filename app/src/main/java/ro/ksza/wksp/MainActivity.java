package ro.ksza.wksp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainActivity extends AppCompatActivity {

    private static final Logger logger = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logger.debug("MainActivity created");
    }
}
