package ro.ksza.wksp.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import ro.ksza.wksp.R;
import ro.ksza.wksp.omdb.model.OmdbMovie;

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

    public void map(final OmdbMovie item, final int position) {
        titleView.setText(item.title);

        Picasso.with(itemView.getContext())
                .load(item.posterUri.toString())
                .into(posterView);
    }
}
