package io.feedback.survey.repository;

import io.feedback.core.repository.AbstractRepository;
import io.feedback.survey.entity.Question;

import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepository extends AbstractRepository<Question> {

    public QuestionRepository() {
        super(Question.class);
    }
}