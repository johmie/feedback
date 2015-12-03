package io.feedback.survey.entity;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class ResultTest {

    private Result result;

    @Before
    public void setUp() {
        result = new Result();
    }

    @Test
    public void setParticipationIdentifier_SomeParticipationIdentifier_SameValueIsReturnedByGetParticipationIdentifier() {
        String participationIdentifier = "Participation identifier";

        result.setParticipationIdentifier(participationIdentifier);

        assertEquals(participationIdentifier, result.getParticipationIdentifier());
    }

    @Test
    public void setFreeText_SomeFreeText_SameValueIsReturnedByGetFreeText() {
        String freeText = "Free text";

        result.setFreeText(freeText);

        assertEquals(freeText, result.getFreeText());
    }

    @Test
    public void setAnswer_SomeAnswer_SameValueIsReturnedByGetAnswer() {
        Answer answerMock = mock(Answer.class);

        result.setAnswer(answerMock);

        assertEquals(answerMock, result.getAnswer());
    }

    @Test
    public void setCreated_SomeTimestamp_SameValueIsReturnedByGetCreated() {
        Timestamp created = mock(Timestamp.class);

        result.setCreated(created);

        assertEquals(created, result.getCreated());
    }

    @Test
    public void setRemoteAddress_SomeRemoteAddress_SameValueIsReturnedByGetRemoteAddress() {
        String remoteAddress = "127.0.0.1";

        result.setRemoteAddress(remoteAddress);

        assertEquals(remoteAddress, result.getRemoteAddress());
    }
}