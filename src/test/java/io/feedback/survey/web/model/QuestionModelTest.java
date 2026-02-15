package io.feedback.survey.web.model;

import io.feedback.survey.entity.Result;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class QuestionModelTest {

    private QuestionModel questionModel;

    @Before
    public void setUp() {
        questionModel = new QuestionModel();
    }

    @Test
    public void setResults_SomeResults_SameValueIsReturnedByGetResults() {
        List<Result> resultMocks = new ArrayList<>();

        questionModel.setResults(resultMocks);

        assertEquals(resultMocks, questionModel.getResults());
    }
}