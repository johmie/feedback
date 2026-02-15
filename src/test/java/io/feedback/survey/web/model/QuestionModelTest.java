package io.feedback.survey.web.model;

import io.feedback.survey.entity.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionModelTest {

    private QuestionModel questionModel;

    @BeforeEach
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