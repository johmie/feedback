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
public class PageTest {

    private Page page;

    @Before
    public void setUp() {
        page = new Page();
    }

    @Test
    public void defaultValueOfType() {
        assertEquals(Page.Type.ASK, page.getType());
    }

    @Test
    public void getAndSetType() {
        page.setType(Page.Type.END);
        assertEquals(Page.Type.END, page.getType());
    }

    @Test
    public void getAndSetQuestions() {
        Set<Question> questionMocks = new HashSet<>();
        page.setQuestions(questionMocks);
        assertEquals(questionMocks, page.getQuestions());
    }

    @Test
    public void getAndSetSurvey() {
        Survey surveyMock = mock(Survey.class);
        page.setSurvey(surveyMock);
        assertEquals(surveyMock, page.getSurvey());
    }

    @Test
    public void getAndSetName() {
        String name = "Name";
        page.setName(name);
        assertEquals(name, page.getName());
    }

    @Test
    public void getAndSetTitle() {
        String title = "Title";
        page.setTitle(title);
        assertEquals(title, page.getTitle());
    }

    @Test
    public void getAndSetPosition() {
        int position = 1;
        page.setPosition(position);
        assertEquals(position, page.getPosition());
    }
}