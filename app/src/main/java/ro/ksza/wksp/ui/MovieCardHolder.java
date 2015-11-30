package ro.ksza.wksp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ro.ksza.wksp.R;
import ro.ksza.wksp.omdb.model.OmdbMovieDetails;

public class MovieCardHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.poster)
    ImageView posterView;

    @Bind(R.id.title)
    TextView titleView;

    @Bind(R.id.actors)
    TextView actorsView;

    public MovieCardHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void map(final OmdbMovieDetails item, final int position) {
        titleView.setText(item.title);
        actorsView.setText(item.actors);
    }
}
