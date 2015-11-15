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
public class SurveyTest {

    private Survey survey;

    @Before
    public void setUp() {
        survey = new Survey();
    }

    @Test
    public void getAndSetPages() {
        Set<Page> pageMocks = new HashSet<>();
        survey.setPages(pageMocks);
        assertEquals(pageMocks, survey.getPages());
    }

    @Test
    public void getAndSetName() {
        String name = "Name";
        survey.setName(name);
        assertEquals(name, survey.getName());
    }

    @Test
    public void getAndSetTitle() {
        String title = "Title";
        survey.setTitle(title);
        assertEquals(title, survey.getTitle());
    }
}