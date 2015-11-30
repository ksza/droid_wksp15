package ro.ksza.wksp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

import ro.ksza.wksp.R;
import ro.ksza.wksp.omdb.model.OmdbMovie;
import ro.ksza.wksp.omdb.model.OmdbMovieDetails;

/**
 * Handles a collection of MovieItems
 */
public class MoviesAdapter extends RecyclerView.Adapter<MovieCardHolder> {

    private List<OmdbMovieDetails> items = Collections.emptyList();

    private final LayoutInflater inflater;

    public MoviesAdapter(final Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MovieCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.movie_item_card_layout, parent, false);
        return new MovieCardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieCardHolder holder, int position) {
        holder.map(items.get(position), position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public OmdbMovie getItemAt(final int position) {
        return items.get(position);
    }

    public void insertItemAt(final OmdbMovieDetails item, final int position) {
        items.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItemAt(final int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }
}
