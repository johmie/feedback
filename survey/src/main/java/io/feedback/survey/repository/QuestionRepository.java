package io.feedback.survey.repository;

import java.util.List;

import io.feedback.core.repository.AbstractBaseRepository;
import io.feedback.survey.entity.Question;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class QuestionRepository extends AbstractBaseRepository<Question> {

    private static final String SELECT_QUERY = "select q from Question q";

    @Override
    public void insertOrUpdate(Question question) {
        getEntityManager().persist(question);
        getEntityManager().refresh(question);
    }
    
    @Override
    public List<Question> fetchAll() {
        Query query = getEntityManager().createQuery(SELECT_QUERY);
        @SuppressWarnings("unchecked")
        List<Question> questions = (List<Question>) query.getResultList();
        return questions;
    }
    
    @Override
    public Question fetchById(Long id) {
        return getEntityManager().find(Question.class, id);
    }
}