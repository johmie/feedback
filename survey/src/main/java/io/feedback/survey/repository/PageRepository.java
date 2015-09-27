package io.feedback.survey.repository;

import java.util.List;

import io.feedback.core.repository.AbstractBaseRepository;
import io.feedback.survey.entity.Page;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository("pageRepository")
public class PageRepository extends AbstractBaseRepository {

    private static final String SELECT_QUERY = "select p from Page p";

    public void insert(Page page) {
        getEntityManager().persist(page);
    }

    public List<Page> findAll() {
        Query query = getEntityManager().createQuery(SELECT_QUERY);
        @SuppressWarnings("unchecked")
        List<Page> pages = (List<Page>) query.getResultList();
        return pages;
    }
}