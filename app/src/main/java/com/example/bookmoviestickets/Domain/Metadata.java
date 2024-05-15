
package com.example.bookmoviestickets.Domain;

import com.google.gson.annotations.Expose;

public class Metadata {

    @Expose
    private String currentPage;
    @Expose
    private Integer perPage;
    @Expose
    private Integer pageCount;
    @Expose
    private Integer totalCount;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
