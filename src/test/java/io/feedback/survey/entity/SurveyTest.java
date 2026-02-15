package io.feedback.survey.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurveyTest {

    private Survey survey;

    @BeforeEach
    void setUp() {
        survey = new Survey();
    }

    @Test
    void setPages_SomePages_SameValueIsReturnedByGetPages() {
        Set<Page> pageMocks = new HashSet<>();

        survey.setPages(pageMocks);

        assertEquals(pageMocks, survey.getPages());
    }

    @Test
    void setName_SomeName_SameValueIsReturnedByGetName() {
        String name = "Name";

        survey.setName(name);

        assertEquals(name, survey.getName());
    }

    @Test
    void setTitle_SomeTitle_SameValueIsReturnedByGetTitle() {
        String title = "Title";

        survey.setTitle(title);

        assertEquals(title, survey.getTitle());
    }
}