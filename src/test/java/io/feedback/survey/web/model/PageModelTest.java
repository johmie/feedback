package io.feedback.survey.web.model;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class PageModelTest {

    private PageModel pageModel;

    @Before
    public void setUp() {
        pageModel = new PageModel();
    }

    @Test
    public void getAndSetQuestionModels() {
        Map<Long, QuestionModel> questionModelMocks = new HashMap<>();
        pageModel.setQuestionModels(questionModelMocks);
        assertEquals(questionModelMocks, pageModel.getQuestionModels());
    }
}