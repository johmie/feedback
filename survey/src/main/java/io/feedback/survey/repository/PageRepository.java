package io.feedback.survey.repository;

import java.util.List;

import io.feedback.core.repository.AbstractBaseRepository;
import io.feedback.survey.entity.Page;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class PageRepository extends AbstractBaseRepository<Page> {

    private static final String SELECT_QUERY = "select p from Page p";

    public void insert(Page page) {
        getEntityManager().persist(page);
        getEntityManager().refresh(page);
    }

    public List<Page> fetchAll() {
        Query query = getEntityManager().createQuery(SELECT_QUERY);
        @SuppressWarnings("unchecked")
        List<Page> pages = (List<Page>) query.getResultList();
        return pages;
    }
}