package io.feedback.survey.web.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PageModelTest {

    private PageModel pageModel;

    @BeforeEach
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