package ro.ksza.wksp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

/**
 * Created by karoly.szanto on 02/12/15.
 */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        final CheckBox cb = (CheckBox) findViewById(R.id.checkable);

        findViewById(R.id.checker).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cb.setChecked(! cb.isChecked());
            }
        });
    }
}
