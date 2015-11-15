package io.feedback.survey.entity;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

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
    public void getAndSetFreeText() {
        String freeText = "Free text";
        result.setFreeText(freeText);
        assertEquals(freeText, result.getFreeText());
    }

    @Test
    public void getAndSetAnswer() {
        Answer answerMock = mock(Answer.class);
        result.setAnswer(answerMock);
        assertEquals(answerMock, result.getAnswer());
    }
}