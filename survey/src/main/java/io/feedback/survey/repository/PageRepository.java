package io.feedback.survey.repository;

import io.feedback.core.repository.AbstractBaseRepository;
import io.feedback.survey.entity.Page;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class PageRepository extends AbstractBaseRepository<Page> {

    public Page findBySurveyIdAndPageNumber(Long surveyId, Integer pageNumber) {
        Query query = getEntityManager().createQuery(
                "select p from Page p where survey_id = :surveyId order by position");
        query.setParameter("surveyId", surveyId);
        query.setFirstResult(pageNumber - 1);
        query.setMaxResults(1);
        Page page = (Page) query.getSingleResult();
        return page;
    }
}