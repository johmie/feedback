package io.feedback.survey.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ResultTest {

    private Result result;

    @BeforeEach
    void setUp() {
        result = new Result();
    }

    @Test
    void setParticipationIdentifier_SomeParticipationIdentifier_SameValueIsReturnedByGetParticipationIdentifier() {
        String participationIdentifier = "Participation identifier";

        result.setParticipationIdentifier(participationIdentifier);

        assertEquals(participationIdentifier, result.getParticipationIdentifier());
    }

    @Test
    void setFreeText_SomeFreeText_SameValueIsReturnedByGetFreeText() {
        String freeText = "Free text";

        result.setFreeText(freeText);

        assertEquals(freeText, result.getFreeText());
    }

    @Test
    void setAnswer_SomeAnswer_SameValueIsReturnedByGetAnswer() {
        Answer answerMock = mock(Answer.class);

        result.setAnswer(answerMock);

        assertEquals(answerMock, result.getAnswer());
    }

    @Test
    void setCreated_SomeTimestamp_SameValueIsReturnedByGetCreated() {
        Timestamp created = mock(Timestamp.class);

        result.setCreated(created);

        assertEquals(created, result.getCreated());
    }

    @Test
    void setRemoteAddress_SomeRemoteAddress_SameValueIsReturnedByGetRemoteAddress() {
        String remoteAddress = "127.0.0.1";

        result.setRemoteAddress(remoteAddress);

        assertEquals(remoteAddress, result.getRemoteAddress());
    }
}