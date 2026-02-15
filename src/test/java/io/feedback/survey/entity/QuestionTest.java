package io.feedback.survey.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class QuestionTest {

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question();
    }

    @Test
    void setType_SomeType_SameValueIsReturnedByGetType() {
        Question.Type type = Question.Type.SINGLE_CHOICE;

        question.setType(type);

        assertEquals(type, question.getType());
    }

    @Test
    void setAnswers_SomeAnswers_SameValueIsReturnedByGetAnswers() {
        Set<Answer> answerMocks = new HashSet<>();

        question.setAnswers(answerMocks);

        assertEquals(answerMocks, question.getAnswers());
    }

    @Test
    void setPage_SomePage_SameValueIsReturnedByGetPage() {
        Page pageMock = mock(Page.class);

        question.setPage(pageMock);

        assertEquals(pageMock, question.getPage());
    }

    @Test
    void setName_SomeName_SameValueIsReturnedByGetName() {
        String name = "Name";

        question.setName(name);

        assertEquals(name, question.getName());
    }

    @Test
    void setTitle_SomeTitle_SameValueIsReturnedByGetTitle() {
        String title = "Title";

        question.setTitle(title);

        assertEquals(title, question.getTitle());
    }

    @Test
    void setPosition_SomePosition_SameValueIsReturnedByGetPosition() {
        int position = 1;

        question.setPosition(position);

        assertEquals(position, question.getPosition());
    }
}