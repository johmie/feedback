package io.feedback.survey.repository;

import io.feedback.core.repository.AbstractBaseRepository;
import io.feedback.survey.entity.Question;

import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepository extends AbstractBaseRepository<Question> {

    @Override
    public void insertOrUpdate(Question question) {
        getEntityManager().persist(question);
    }

    @Override
    public Question fetchById(Long id) {
        return getEntityManager().find(Question.class, id);
    }
}