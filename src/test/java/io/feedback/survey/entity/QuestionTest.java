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
public class QuestionTest {

    private Question question;

    @Before
    public void setUp() {
        question = new Question();
    }

    @Test
    public void setType_SomeType_SameValueIsReturnedByGetType() {
        Question.Type type = Question.Type.SINGLE_CHOICE;

        question.setType(type);

        assertEquals(type, question.getType());
    }

    @Test
    public void setAnswers_SomeAnswers_SameValueIsReturnedByGetAnswers() {
        Set<Answer> answerMocks = new HashSet<>();

        question.setAnswers(answerMocks);

        assertEquals(answerMocks, question.getAnswers());
    }

    @Test
    public void setPage_SomePage_SameValueIsReturnedByGetPage() {
        Page pageMock = mock(Page.class);

        question.setPage(pageMock);

        assertEquals(pageMock, question.getPage());
    }

    @Test
    public void setName_SomeName_SameValueIsReturnedByGetName() {
        String name = "Name";

        question.setName(name);

        assertEquals(name, question.getName());
    }

    @Test
    public void setTitle_SomeTitle_SameValueIsReturnedByGetTitle() {
        String title = "Title";

        question.setTitle(title);

        assertEquals(title, question.getTitle());
    }

    @Test
    public void setPosition_SomePosition_SameValueIsReturnedByGetPosition() {
        int position = 1;

        question.setPosition(position);

        assertEquals(position, question.getPosition());
    }
}