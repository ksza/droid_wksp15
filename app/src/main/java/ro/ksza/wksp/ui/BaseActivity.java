package ro.ksza.wksp.ui;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import ro.ksza.wksp.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    @Nullable
    Toolbar toolbar;

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        bindViews();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        bindViews();
    }

    protected void bindViews() {
        ButterKnife.bind(this);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }
}
