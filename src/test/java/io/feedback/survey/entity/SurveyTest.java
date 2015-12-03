package io.feedback.survey.entity;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class SurveyTest {

    private Survey survey;

    @Before
    public void setUp() {
        survey = new Survey();
    }

    @Test
    public void setPages_SomePages_SameValueIsReturnedByGetPages() {
        Set<Page> pageMocks = new HashSet<>();

        survey.setPages(pageMocks);

        assertEquals(pageMocks, survey.getPages());
    }

    @Test
    public void setName_SomeName_SameValueIsReturnedByGetName() {
        String name = "Name";

        survey.setName(name);

        assertEquals(name, survey.getName());
    }

    @Test
    public void setTitle_SomeTitle_SameValueIsReturnedByGetTitle() {
        String title = "Title";

        survey.setTitle(title);

        assertEquals(title, survey.getTitle());
    }
}