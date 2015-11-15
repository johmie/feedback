package io.feedback.survey.entity;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class AnswerTest {

    private Answer answer;

    @Before
    public void setUp() {
        answer = new Answer();
    }

    @Test
    public void defaultValueOfValueType() {
        assertEquals(Answer.ValueType.CHOICE, answer.getValueType());
    }

    @Test
    public void getAndSetValueType() {
        answer.setValueType(Answer.ValueType.FREE_TEXT);
        assertEquals(Answer.ValueType.FREE_TEXT, answer.getValueType());
    }

    @Test
    public void getAndSetResults() {
        Set<Result> resultMocks = new HashSet<>();
        answer.setResults(resultMocks);
        assertEquals(resultMocks, answer.getResults());
    }

    @Test
    public void getAndSetQuestion() {
        Question questionMock = mock(Question.class);
        answer.setQuestion(questionMock);
        assertEquals(questionMock, answer.getQuestion());
    }

    @Test
    public void getAndSetValue() {
        String value = "Value";
        answer.setValue(value);
        assertEquals(value, answer.getValue());
    }

    @Test
    public void getAndSetName() {
        String name = "Name";
        answer.setName(name);
        assertEquals(name, answer.getName());
    }

    @Test
    public void getAndSetTitle() {
        String title = "Title";
        answer.setTitle(title);
        assertEquals(title, answer.getTitle());
    }

    @Test
    public void getAndSetPosition() {
        int position = 1;
        answer.setPosition(position);
        assertEquals(position, answer.getPosition());
    }
}