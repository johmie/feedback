package io.feedback.survey.entity;

import java.util.Set;

import io.feedback.core.entity.AbstractEntity;

import javax.persistence.*;

@Entity
public class Page extends AbstractEntity {

    public enum Type {
        ASK,
        END
    }
    @Enumerated(EnumType.STRING)
    private Type type = Type.ASK;

    private String name;

    private String title;

    private int position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "page", fetch = FetchType.EAGER)
    @OrderBy("position ASC")
    private Set<Question> questions;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
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
}