package io.feedback.survey.entity;

import java.util.Set;

import io.feedback.core.entity.AbstractBaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Answer extends AbstractBaseEntity {

    public static enum Type {
        PREDEFINED, FREE_TEXT 
    }
    @Enumerated(EnumType.STRING)
    private Type type = Type.PREDEFINED;
    private String name;
    private String title;
    private String value;
    private Integer position = 0;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    @OneToMany(mappedBy = "answer")
    private Set<Result> results;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}