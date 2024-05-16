package com.example.bookmoviestickets.Domain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ListFilm {

    @SerializedName("data")
    @Expose
    private List<Datum> data;
    @SerializedName("metadata")
    @Expose
    private Metadata metadata;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
