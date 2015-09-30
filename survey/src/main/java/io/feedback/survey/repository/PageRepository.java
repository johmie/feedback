package io.feedback.survey.repository;

import java.util.List;

import io.feedback.core.repository.AbstractBaseRepository;
import io.feedback.survey.entity.Page;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class PageRepository extends AbstractBaseRepository<Page> {

    private static final String SELECT_QUERY = "select p from Page p";

    @Override
    public void insert(Page page) {
        getEntityManager().persist(page);
        getEntityManager().refresh(page);
    }

    @Override
    public List<Page> fetchAll() {
        Query query = getEntityManager().createQuery(SELECT_QUERY);
        @SuppressWarnings("unchecked")
        List<Page> pages = (List<Page>) query.getResultList();
        return pages;
    }

    @Override
    public Page fetchById(Long id) {
        return getEntityManager().find(Page.class, id);
    }

    public Page fetchOfSurveyByPageNumber(Long surveyId, Integer pageNumber) {
        Query query = getEntityManager().createQuery(
                "select p from Page p where survey_id = :surveyId order by position");
        query.setParameter("surveyId", surveyId);
        query.setMaxResults(1);
        query.setFirstResult(pageNumber - 1);
        Page page = (Page) query.getSingleResult();
        return page;
    }
}