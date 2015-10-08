package io.feedback.survey.web.model;

import java.util.Map;

public class PageModel {

    private Map<Long, QuestionModel> questionModels;

    public Map<Long, QuestionModel> getQuestionModels() {
        return questionModels;
    }

    public void setQuestionModels(Map<Long, QuestionModel> questionModels) {
        this.questionModels = questionModels;
    }
}