package ro.ksza.wksp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ro.ksza.wksp.R;

/**
 * Created by karoly.szanto on 30/11/15.
 */
public class SearchMoviesAdapter<T extends String> extends BaseAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SearchMoviesAdapter.class);

    private final List<T> data;
    private final Context context;

    private final LayoutInflater inflater;

    public SearchMoviesAdapter(Context context) {
        this(context, new ArrayList<T>());
    }

    public SearchMoviesAdapter(Context context, List<T> objects) {
        this.context = context;
        this.data = objects;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void replace(final List<T> objects) {
        data.clear();
        data.addAll(objects);
        notifyDataSetChanged();
    }

    public static List<String> createDummyMoviesList(final int size) {
        final List<String> dummyMoviesList = new ArrayList<>();

        for(int i = 0; i <= size; i++) {
            dummyMoviesList.add("Movie no " + i);
        }

        return dummyMoviesList;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {

            logger.debug("Creating view for position {}", position);

            convertView = inflater.inflate(R.layout.item_layout1, parent, false);

            /* set-up */
            final ViewHolder viewHolder = new ViewHolder();
            ButterKnife.bind(viewHolder, convertView);

            convertView.setTag(viewHolder);
        } else {
            logger.debug("Reusing view for position {}", position);
        }

        final ViewHolder vh = (ViewHolder) convertView.getTag();
        vh.itemName.setText(data.get(position));

        return convertView;
    }

    class ViewHolder {
        @Bind(R.id.movie_item)
        TextView itemName;
    }
}
