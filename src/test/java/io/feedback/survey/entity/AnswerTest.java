package io.feedback.survey.entity;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(JUnitParamsRunner.class)
public class AnswerTest {

    private Answer answer;

    @Before
    public void setUp() {
        answer = new Answer();
    }

    @Test
    public void getValueType_InstantiatedAnswer_DefaultValueIsReturned() {
        Answer.ValueType valueType = answer.getValueType();

        assertEquals(Answer.ValueType.CHOICE, valueType);
    }

    @Test
    public void setValueType_SomeValueType_SameValueIsReturnedByGetValueType() {
        Answer.ValueType valueType = Answer.ValueType.FREE_TEXT;

        answer.setValueType(valueType);

        assertEquals(valueType, answer.getValueType());
    }

    @Test
    public void setResults_SomeResults_SameValueIsReturnedByGetResults() {
        Set<Result> resultMocks = new HashSet<>();

        answer.setResults(resultMocks);

        assertEquals(resultMocks, answer.getResults());
    }

    @Test
    public void setQuestion_SomeQuestion_SameValueIsReturnedByGetQuestion() {
        Question questionMock = mock(Question.class);

        answer.setQuestion(questionMock);

        assertEquals(questionMock, answer.getQuestion());
    }

    @Test
    public void setValue_SomeValue_SameValueIsReturnedByGetValue() {
        String value = "Value";

        answer.setValue(value);

        assertEquals(value, answer.getValue());
    }

    @Test
    public void setName_SomeName_SameValueIsReturnedByGetName() {
        String name = "Name";

        answer.setName(name);

        assertEquals(name, answer.getName());
    }

    @Test
    public void setTitle_SomeTitle_SameValueIsReturnedByGetTitle() {
        String title = "Title";

        answer.setTitle(title);

        assertEquals(title, answer.getTitle());
    }

    @Test
    public void setPosition_SomePosition_SameValueIsReturnedByGetPosition() {
        int position = 1;

        answer.setPosition(position);

        assertEquals(position, answer.getPosition());
    }
}