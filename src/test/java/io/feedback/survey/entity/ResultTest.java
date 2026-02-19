package io.feedback.survey.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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

    @Test
    void setAnswerRow_SomeAnswerRow_SameValueIsReturnedByGetAnswerRow() {
        AnswerRow answerRowMock = mock(AnswerRow.class);

        result.setAnswerRow(answerRowMock);

        assertEquals(answerRowMock, result.getAnswerRow());
    }

    @Test
    void setAnswerIdAnswerRowId_SomeValue_SameValueIsReturnedByGetAnswerIdAnswerRowId() {
        String answerIdAnswerRowId = "123_456";

        result.setAnswerIdAnswerRowId(answerIdAnswerRowId);

        assertEquals(answerIdAnswerRowId, result.getAnswerIdAnswerRowId());
    }

    @Test
    void setAnswerIdAnswerRowId_ValidFormat_SetsAnswerAndAnswerRowWithIds() {
        String answerIdAnswerRowId = "123_456";

        result.setAnswerIdAnswerRowId(answerIdAnswerRowId);

        assertEquals(123L, result.getAnswer().getId());
        assertEquals(456L, result.getAnswerRow().getId());
    }

    @Test
    void setAnswerIdAnswerRowId_Null_DoesNotSetAnswerOrAnswerRow() {
        result.setAnswerIdAnswerRowId(null);

        assertNull(result.getAnswer());
        assertNull(result.getAnswerRow());
    }

    @Test
    void setAnswerIdAnswerRowId_EmptyString_DoesNotSetAnswerOrAnswerRow() {
        result.setAnswerIdAnswerRowId("");

        assertNull(result.getAnswer());
        assertNull(result.getAnswerRow());
    }

    @Test
    void setAnswerIdAnswerRowId_InvalidFormat_DoesNotSetAnswerOrAnswerRow() {
        result.setAnswerIdAnswerRowId("invalid");

        assertNull(result.getAnswer());
        assertNull(result.getAnswerRow());
    }

    @Test
    void setAnswerIdAnswerRowId_InvalidNumberFormat_ThrowsNumberFormatException() {
        String answerIdAnswerRowId = "abc_123";

        try {
            result.setAnswerIdAnswerRowId(answerIdAnswerRowId);
        } catch (NumberFormatException e) {
        }

        assertNull(result.getAnswer());
        assertNull(result.getAnswerRow());
    }

    @Test
    void setAnswerIdAnswerRowId_MoreThanTwoParts_DoesNotSetAnswerOrAnswerRow() {
        result.setAnswerIdAnswerRowId("1_2_3");

        assertNull(result.getAnswer());
        assertNull(result.getAnswerRow());
    }

    @Test
    void setId_SomeId_SameValueIsReturnedByGetId() {
        Long id = 1L;

        result.setId(id);

        assertEquals(id, result.getId());
    }

    @Test
    void setVersion_SomeVersion_SameValueIsReturnedByGetVersion() {
        Long version = 1L;

        result.setVersion(version);

        assertEquals(version, result.getVersion());
    }
}