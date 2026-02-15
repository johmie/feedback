package io.feedback.survey.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class AnswerTest {

    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer();
    }

    @Test
    void getValueType_InstantiatedAnswer_DefaultValueIsReturned() {
        Answer.ValueType valueType = answer.getValueType();

        assertEquals(Answer.ValueType.CHOICE, valueType);
    }

    @Test
    void setValueType_SomeValueType_SameValueIsReturnedByGetValueType() {
        Answer.ValueType valueType = Answer.ValueType.FREE_TEXT;

        answer.setValueType(valueType);

        assertEquals(valueType, answer.getValueType());
    }

    @Test
    void setResults_SomeResults_SameValueIsReturnedByGetResults() {
        Set<Result> resultMocks = new HashSet<>();

        answer.setResults(resultMocks);

        assertEquals(resultMocks, answer.getResults());
    }

    @Test
    void setQuestion_SomeQuestion_SameValueIsReturnedByGetQuestion() {
        Question questionMock = mock(Question.class);

        answer.setQuestion(questionMock);

        assertEquals(questionMock, answer.getQuestion());
    }

    @Test
    void setValue_SomeValue_SameValueIsReturnedByGetValue() {
        String value = "Value";

        answer.setValue(value);

        assertEquals(value, answer.getValue());
    }

    @Test
    void setName_SomeName_SameValueIsReturnedByGetName() {
        String name = "Name";

        answer.setName(name);

        assertEquals(name, answer.getName());
    }

    @Test
    void setTitle_SomeTitle_SameValueIsReturnedByGetTitle() {
        String title = "Title";

        answer.setTitle(title);

        assertEquals(title, answer.getTitle());
    }

    @Test
    void setPosition_SomePosition_SameValueIsReturnedByGetPosition() {
        int position = 1;

        answer.setPosition(position);

        assertEquals(position, answer.getPosition());
    }
}