package com.example.pagingexample.Model;


import java.util.List;

public class Photos{
    private int page;
    private int pages;
    private int perpage;
    private int total;
    private List<Photo> photo=null;

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public int getTotal() {
        return total;
    }

    public List<Photo> getPhoto() {
        return photo;
    }
}
