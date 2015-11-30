package ro.ksza.wksp.ui;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by karoly.szanto on 30/11/15.
 */
public class SearchMoviesAdapter extends ArrayAdapter<String> {

    public SearchMoviesAdapter(Context context) {
        this(context,new ArrayList<String>());
    }

    public SearchMoviesAdapter(Context context, List<String> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
    }

    public void replace(final List<String> objects) {
        clear();
        addAll(objects);
        notifyDataSetChanged();
    }

    public static List<String> createDummyMoviesList(final int size) {
        final List<String> dummyMoviesList = new ArrayList<>();

        for(int i = 0; i <= size; i++) {
            dummyMoviesList.add("Movie no " + i);
        }

        return dummyMoviesList;
    }
}
