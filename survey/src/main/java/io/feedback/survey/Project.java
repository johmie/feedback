package io.feedback.survey;

import java.util.List;

public class Project {
    private int id;
    private String name;
    private String title;
    private List<Page> pages;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Project [id=" + id + ", name=" + name + ", title=" + title
                + ", pages=" + pages + "]";
    }
}
