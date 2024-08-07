package io.feedback.survey.repository;

import io.feedback.core.repository.AbstractRepository;
import io.feedback.survey.entity.Page;
import org.springframework.stereotype.Repository;

import jakarta.persistence.Query;

@Repository
public class PageRepository extends AbstractRepository<Page> {

    public Page findBySurveyIdAndPageNumber(Long surveyId, Integer pageNumber) {
        Query query = getEntityManager().createQuery(
                "from Page p " +
                        "left join fetch p.questions q " +
                        "left join fetch q.answers a " +
                        "where p.survey.id = :surveyId " +
                        "order by p.position, q.position, a.position");
        query.setParameter("surveyId", surveyId);
        query.setFirstResult(pageNumber - 1);
        query.setMaxResults(1);
        Page page = (Page) query.getSingleResult();
        return page;
    }
}