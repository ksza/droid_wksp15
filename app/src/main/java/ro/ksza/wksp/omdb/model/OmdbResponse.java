package ro.ksza.wksp.omdb.model;

import com.google.gson.annotations.SerializedName;

public class OmdbResponse {
    @SerializedName("Response")
    public boolean success = true;

    @SerializedName("Error")
    public String errorMessage;

    @Override
    public String toString() {
        return "OmdbResponse{" +
                "success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
