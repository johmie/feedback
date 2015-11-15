package io.feedback.survey.web.model;

import io.feedback.survey.entity.Result;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class QuestionModelTest {

    private QuestionModel questionModel;

    @Before
    public void setUp() {
        questionModel = new QuestionModel();
    }

    @Test
    public void getAndSetResults() {
        List<Result> resultMocks = new ArrayList<>();
        questionModel.setResults(resultMocks);
        assertEquals(resultMocks, questionModel.getResults());
    }
}