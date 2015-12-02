package ro.ksza.wksp.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import ro.ksza.wksp.R;

public class DetailsActivity extends BaseActivity {

    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Bind(R.id.details_image)
    ImageView detailsImage;

    @Bind(R.id.plot_details)
    TextView plotDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initToolbar("Dummy movie name");
    }

    private void initToolbar(final String movieTitle) {
        setToolbarTitle(movieTitle);
    }

    private void setToolbarTitle(final String title) {
        collapsingToolbarLayout.setTitle(title);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if(Build.VERSION.SDK_INT < 23) {
            //noinspection deprecation
            collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        } else {
            collapsingToolbarLayout.setExpandedTitleColor(getColor(android.R.color.transparent));
        }
    }
}
