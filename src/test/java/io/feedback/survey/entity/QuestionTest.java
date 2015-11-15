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
public class QuestionTest {

    private Question question;

    @Before
    public void setUp() {
        question = new Question();
    }

    @Test
    public void getAndSetType() {
        question.setType(Question.Type.SINGLE_CHOICE);
        assertEquals(Question.Type.SINGLE_CHOICE, question.getType());
    }

    @Test
    public void getAndSetAnswers() {
        Set<Answer> answerMocks = new HashSet<>();
        question.setAnswers(answerMocks);
        assertEquals(answerMocks, question.getAnswers());
    }

    @Test
    public void getAndSetPage() {
        Page pageMock = mock(Page.class);
        question.setPage(pageMock);
        assertEquals(pageMock, question.getPage());
    }

    @Test
    public void getAndSetName() {
        String name = "Name";
        question.setName(name);
        assertEquals(name, question.getName());
    }

    @Test
    public void getAndSetTitle() {
        String title = "Title";
        question.setTitle(title);
        assertEquals(title, question.getTitle());
    }

    @Test
    public void getAndSetPosition() {
        int position = 1;
        question.setPosition(position);
        assertEquals(position, question.getPosition());
    }
}