package io.feedback.survey.web.bind;

import io.feedback.survey.entity.Result;

import java.util.List;

public class ResultList {

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}