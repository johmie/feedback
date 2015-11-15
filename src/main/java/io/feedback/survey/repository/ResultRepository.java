package io.feedback.survey.repository;

import io.feedback.core.repository.AbstractRepository;
import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.Result;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ResultRepository extends AbstractRepository<Result> {

    public void saveResults(List<Result> results) {
        for (Result result : results) {
            initializeAnswerOfResult(result);
            insertOrUpdate(result);
        }
    }

    private void initializeAnswerOfResult(Result result) {
        Answer answer = getEntityManager().find(Answer.class, result.getAnswer().getId());
        result.setAnswer(answer);
    }
}