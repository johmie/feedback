package io.feedback.survey.entity;

import io.feedback.core.entity.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question extends AbstractBaseEntity {

    private String name;
    private String title;
    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
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