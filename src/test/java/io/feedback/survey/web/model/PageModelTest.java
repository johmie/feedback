package io.feedback.survey.web.model;

import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class PageModelTest {

    private PageModel pageModel;

    @Before
    public void setUp() {
        pageModel = new PageModel();
    }

    @Test
    public void setQuestionModels_SomeQuestionModels_SameValueIsReturnedByGetQuestionModels() {
        Map<Long, QuestionModel> questionModelMocks = new HashMap<>();

        pageModel.setQuestionModels(questionModelMocks);

        assertEquals(questionModelMocks, pageModel.getQuestionModels());
    }
}