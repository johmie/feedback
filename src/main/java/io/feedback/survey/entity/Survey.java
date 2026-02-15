package io.feedback.survey.entity;

import io.feedback.core.entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class Survey extends AbstractEntity {

    @NotBlank(message = "Name may not be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Title may not be blank")
    @Size(min = 2, max = 150, message = "Title must be between 2 and 150 characters")
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