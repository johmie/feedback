package io.feedback.survey.web;

import io.feedback.survey.entity.Result;

import java.util.List;

public class ResultSet {

    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}