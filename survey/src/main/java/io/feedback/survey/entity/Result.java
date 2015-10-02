package io.feedback.survey.entity;

import io.feedback.core.entity.AbstractBaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Result extends AbstractBaseEntity {

    @Column(name = "free_text")
    private String freeText;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public String getFreeText() {
        return freeText;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}