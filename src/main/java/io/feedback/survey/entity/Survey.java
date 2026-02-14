package io.feedback.survey.entity;

import io.feedback.core.entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

import java.util.Set;

@Entity
public class Survey extends AbstractEntity {

    private String name;

    private String title;

    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY)
    @OrderBy("position")
    private Set<Page> pages;

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
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
}