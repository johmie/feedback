package io.feedback.survey.repository;

import java.util.List;

import io.feedback.core.repository.AbstractBaseRepository;
import io.feedback.survey.entity.Survey;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class SurveyRepository extends AbstractBaseRepository<Survey> {

    private static final String SELECT_QUERY = "select s from Survey s";

    @Override
    public void insertOrUpdate(Survey project) {
        getEntityManager().persist(project);
        getEntityManager().refresh(project);
    }

    @Override
    public List<Survey> fetchAll() {
        Query query = getEntityManager().createQuery(SELECT_QUERY);
        @SuppressWarnings("unchecked")
        List<Survey> projects = (List<Survey>) query.getResultList();
        return projects;
    }
    
    @Override
    public Survey fetchById(Long id) {
        return getEntityManager().find(Survey.class, id);
    }
}