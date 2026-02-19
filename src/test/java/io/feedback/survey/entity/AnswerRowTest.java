package io.feedback.survey.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class AnswerRowTest {

    private AnswerRow answerRow;

    @BeforeEach
    void setUp() {
        answerRow = new AnswerRow();
    }

    @Test
    void setName_SomeName_SameValueIsReturnedByGetName() {
        String name = "Answer name";

        answerRow.setName(name);

        assertEquals(name, answerRow.getName());
    }

    @Test
    void setTitle_SomeTitle_SameValueIsReturnedByGetTitle() {
        String title = "Answer title";

        answerRow.setTitle(title);

        assertEquals(title, answerRow.getTitle());
    }

    @Test
    void setValue_SomeValue_SameValueIsReturnedByGetValue() {
        String value = "Answer value";

        answerRow.setValue(value);

        assertEquals(value, answerRow.getValue());
    }

    @Test
    void setPosition_SomePosition_SameValueIsReturnedByGetPosition() {
        int position = 5;

        answerRow.setPosition(position);

        assertEquals(position, answerRow.getPosition());
    }

    @Test
    void setQuestion_SomeQuestion_SameValueIsReturnedByGetQuestion() {
        Question questionMock = mock(Question.class);

        answerRow.setQuestion(questionMock);

        assertEquals(questionMock, answerRow.getQuestion());
    }

    @Test
    void setResults_SomeResults_SameValueIsReturnedByGetResults() {
        var results = new HashSet<Result>();

        answerRow.setResults(results);

        assertEquals(results, answerRow.getResults());
    }

    @Test
    void setId_SomeId_SameValueIsReturnedByGetId() {
        Long id = 1L;

        answerRow.setId(id);

        assertEquals(id, answerRow.getId());
    }

    @Test
    void setVersion_SomeVersion_SameValueIsReturnedByGetVersion() {
        Long version = 1L;

        answerRow.setVersion(version);

        assertEquals(version, answerRow.getVersion());
    }
}