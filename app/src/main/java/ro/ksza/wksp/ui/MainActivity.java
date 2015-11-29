package ro.ksza.wksp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ro.ksza.wksp.R;

public class MainActivity extends AppCompatActivity {

    private static final Logger logger = LoggerFactory.getLogger(MainActivity.class);

    @Bind(R.id.hello_text_view)
    TextView helloView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        logger.debug("MainActivity created");
    }

    @OnClick(R.id.button)
    public void onHelloButtonClick() {
        helloView.setText(R.string.changed_hello_world);
    }
}
