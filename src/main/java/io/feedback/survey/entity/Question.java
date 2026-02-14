package io.feedback.survey.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.feedback.core.entity.AbstractEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

import java.util.Set;

@Entity
public class Question extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    private Type type;

    private String name;

    private String title;

    private int position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    @JsonIgnore
    private Page page;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    @OrderBy("position")
    private Set<Answer> answers;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public enum Type {
        SINGLE_CHOICE,
        MULTIPLE_CHOICE,
        MATRIX
    }
}