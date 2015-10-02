package io.feedback.survey.web.form;

import java.util.List;

public class PageForm {

    private List<QuestionResult> questionResults;

    public List<QuestionResult> getQuestionResults() {
        return questionResults;
    }

    public void setQuestionResults(List<QuestionResult> questionResults) {
        this.questionResults = questionResults;
    } 
}