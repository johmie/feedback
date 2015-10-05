package io.feedback.survey.web.model;

import java.util.List;

public class PageFormModel {

    private List<QuestionFormModel> questions;

    public List<QuestionFormModel> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionFormModel> questions) {
        this.questions = questions;
    }


}