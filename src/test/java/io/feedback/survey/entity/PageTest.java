package io.feedback.survey.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class PageTest {

    private Page page;

    @BeforeEach
    void setUp() {
        page = new Page();
    }

    @Test
    void getType_InstantiatedPage_DefaultValueIsReturned() {
        Page.Type type = page.getType();

        assertEquals(Page.Type.ASK, type);
    }

    @Test
    void setType_SomeType_SameValueIsReturnedByGetType() {
        Page.Type type = Page.Type.END;

        page.setType(type);

        assertEquals(type, page.getType());
    }

    @Test
    void setQuestions_SomeQuestions_SameValueIsReturnedByGetQuestions() {
        Set<Question> questionMocks = new HashSet<>();

        page.setQuestions(questionMocks);

        assertEquals(questionMocks, page.getQuestions());
    }

    @Test
    void setSurvey_SomeSurvey_SameValueIsReturnedByGetSurvey() {
        Survey surveyMock = mock(Survey.class);

        page.setSurvey(surveyMock);

        assertEquals(surveyMock, page.getSurvey());
    }

    @Test
    void setName_SomeName_SameValueIsReturnedByGetName() {
        String name = "Name";

        page.setName(name);

        assertEquals(name, page.getName());
    }

    @Test
    void setTitle_SomeTitle_SameValueIsReturnedByGetTitle() {
        String title = "Title";

        page.setTitle(title);

        assertEquals(title, page.getTitle());
    }

    @Test
    void setPosition_SomePosition_SameValueIsReturnedByGetPosition() {
        int position = 1;

        page.setPosition(position);

        assertEquals(position, page.getPosition());
    }
}