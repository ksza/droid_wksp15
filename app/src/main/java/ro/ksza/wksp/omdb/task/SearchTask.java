package ro.ksza.wksp.omdb.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import ro.ksza.wksp.omdb.model.OmdbSearchMovies;

public class SearchTask extends BaseTask {

    private static final Logger logger = LoggerFactory.getLogger(SearchTask.class);

    public SearchTask(final SearchListener searchListener) {
        super(searchListener);
    }

    @Override
    protected OmdbSearchMovies doInBackground(String... params) {

        String searchMovies = "";

        try {
            searchMovies = doSearch(params[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.debug("String result: " + searchMovies);

        return converter.fromJson(searchMovies, OmdbSearchMovies.class);
    }

    private String doSearch(final String movieTitle) throws IOException {
        return doGet(createEncodedSearchUrl(movieTitle));
    }

    /**
     * Given a URL, establishes an HttpUrlConnection and retrieves
     * the web page content as a InputStream, which it returns as
     * a string
     */
    private String doGet(final String requestUrl) throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);  /* milliseconds */
            conn.setConnectTimeout(15000); /* milliseconds */
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            /* Starts the query */
            conn.connect();
            int response = conn.getResponseCode();

            logger.debug("Status code: " + response);

            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = streamToString(is);
            return contentAsString;

            /* Makes sure that the InputStream is closed after the app is finished using it */
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * Reads an InputStream and converts it to a String.
     */
    public String streamToString(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder total = new StringBuilder();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            total.append(line);
        }

        return total.toString();
    }
}
