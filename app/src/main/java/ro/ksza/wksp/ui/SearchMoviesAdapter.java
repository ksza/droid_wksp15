package ro.ksza.wksp.ui;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import ro.ksza.wksp.omdb.model.OmdbMovie;

/**
 * Created by karoly.szanto on 30/11/15.
 */
public class SearchMoviesAdapter extends ArrayAdapter<OmdbMovie> {

    public SearchMoviesAdapter(Context context) {
        super(context, android.R.layout.simple_list_item_1, new ArrayList<OmdbMovie>());
    }

    public void replace(final List<OmdbMovie> objects) {
        clear();
        addAll(objects);
        notifyDataSetChanged();
    }
//
//    public static List<String> createDummyMoviesList(final int size) {
//        final List<String> dummyMoviesList = new ArrayList<>();
//
//        for(int i = 0; i <= size; i++) {
//            dummyMoviesList.add("Movie no " + i);
//        }
//
//        return dummyMoviesList;
//    }
}
