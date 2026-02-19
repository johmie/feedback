package io.feedback.survey.entity;

import io.feedback.core.entity.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_row_id")
    private AnswerRow answerRow;

    @Transient
    private String answerIdAnswerRowId;

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

    public AnswerRow getAnswerRow() {
        return answerRow;
    }

    public void setAnswerRow(AnswerRow answerRow) {
        this.answerRow = answerRow;
    }

    public String getAnswerIdAnswerRowId() {
        return answerIdAnswerRowId;
    }

    public void setAnswerIdAnswerRowId(String answerIdAnswerRowId) {
        this.answerIdAnswerRowId = answerIdAnswerRowId;
        if (answerIdAnswerRowId != null && !answerIdAnswerRowId.isEmpty()) {
            String[] parts = answerIdAnswerRowId.split("_");
            if (parts.length == 2) {
                Long answerId = Long.parseLong(parts[0]);
                Long answerRowId = Long.parseLong(parts[1]);
                Answer answer = new Answer();
                answer.setId(answerId);
                setAnswer(answer);
                AnswerRow answerRow = new AnswerRow();
                answerRow.setId(answerRowId);
                setAnswerRow(answerRow);
            }
        }
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