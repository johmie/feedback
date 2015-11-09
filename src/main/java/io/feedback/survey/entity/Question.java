package io.feedback.survey.entity;

import java.util.Set;

import io.feedback.core.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

@Entity
public class Question extends AbstractEntity {

    public enum Type {
        SINGLE_CHOICE,
        MULTIPLE_CHOICE,
        MATRIX
    }
    @Enumerated(EnumType.STRING)
    private Type type;

    private String name;

    private String title;

    private Integer position = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "page_id")
    private Page page;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    @OrderBy("position ASC")
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

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}