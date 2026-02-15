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
public class PageTest {

    private Page page;

    @Before
    public void setUp() {
        page = new Page();
    }

    @Test
    public void getType_InstantiatedPage_DefaultValueIsReturned() {
        Page.Type type = page.getType();

        assertEquals(Page.Type.ASK, type);
    }

    @Test
    public void setType_SomeType_SameValueIsReturnedByGetType() {
        Page.Type type = Page.Type.END;

        page.setType(type);

        assertEquals(type, page.getType());
    }

    @Test
    public void setQuestions_SomeQuestions_SameValueIsReturnedByGetQuestions() {
        Set<Question> questionMocks = new HashSet<>();

        page.setQuestions(questionMocks);

        assertEquals(questionMocks, page.getQuestions());
    }

    @Test
    public void setSurvey_SomeSurvey_SameValueIsReturnedByGetSurvey() {
        Survey surveyMock = mock(Survey.class);

        page.setSurvey(surveyMock);

        assertEquals(surveyMock, page.getSurvey());
    }

    @Test
    public void setName_SomeName_SameValueIsReturnedByGetName() {
        String name = "Name";

        page.setName(name);

        assertEquals(name, page.getName());
    }

    @Test
    public void setTitle_SomeTitle_SameValueIsReturnedByGetTitle() {
        String title = "Title";

        page.setTitle(title);

        assertEquals(title, page.getTitle());
    }

    @Test
    public void setPosition_SomePosition_SameValueIsReturnedByGetPosition() {
        int position = 1;

        page.setPosition(position);

        assertEquals(position, page.getPosition());
    }
}