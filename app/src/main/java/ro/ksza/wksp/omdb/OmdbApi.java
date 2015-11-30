package ro.ksza.wksp.omdb;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import ro.ksza.wksp.omdb.model.OmdbMovieDetails;
import ro.ksza.wksp.omdb.model.OmdbSearchMovies;

/**
 * The Open Movie Database API
 */
public interface OmdbApi {

    @GET("/?plot=full&tomatoes=true")
    Call<OmdbMovieDetails> getByTitle(@Query(value = "t", encoded = true) String title);

    @GET("/")
    Call<OmdbSearchMovies> searchByTitle(@Query(value = "s", encoded = true) String title);
}
