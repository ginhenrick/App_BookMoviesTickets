
package com.example.bookmoviestickets.Domain;

import com.google.gson.annotations.Expose;

import java.util.List;
public class ListFilm {

    @Expose
    private List<Datum> data;
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
