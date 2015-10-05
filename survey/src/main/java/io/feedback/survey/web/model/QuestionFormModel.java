package io.feedback.survey.web.model;

import java.util.List;

import io.feedback.survey.entity.Result;

public class QuestionFormModel {

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
