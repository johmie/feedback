package io.feedback.survey.repository;

import java.util.List;

import io.feedback.core.repository.AbstractBaseRepository;
import io.feedback.survey.entity.Survey;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class SurveyRepository extends AbstractBaseRepository<Survey> {

    @Override
    public void insertOrUpdate(Survey project) {
        getEntityManager().persist(project);
    }

    @Override
    public Survey fetchById(Long id) {
        return getEntityManager().find(Survey.class, id);
    }

    public List<Survey> fetchAll() {
        Query query = getEntityManager().createQuery("select s from Survey s");
        @SuppressWarnings("unchecked")
        List<Survey> surveys = (List<Survey>) query.getResultList();
        return surveys;
    }
}