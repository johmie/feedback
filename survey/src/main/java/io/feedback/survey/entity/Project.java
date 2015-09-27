package io.feedback.survey.entity;

import java.util.Set;

import io.feedback.core.entity.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Project extends AbstractBaseEntity {
    private String name;
    private String title;
    @OneToMany(mappedBy = "project")
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