package io.feedback.survey.entity;

import io.feedback.core.entity.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.sql.Timestamp;

@Entity
public class Result extends AbstractEntity {

    @Column(name = "participation_identifier")
    private String participationIdentifier;

    @Column(name = "free_text")
    private String freeText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    private Timestamp created;

    @Column(name = "remote_address")
    private String remoteAddress;

    public String getParticipationIdentifier() {
        return participationIdentifier;
    }

    public void setParticipationIdentifier(String participationIdentifier) {
        this.participationIdentifier = participationIdentifier;
    }

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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }
}