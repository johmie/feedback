package io.feedback.survey.repository;

import java.util.List;

import io.feedback.survey.entity.Project;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("projectDao")
@Transactional(propagation = Propagation.REQUIRED)
public class ProjectDao {
    
    private static final String SELECT_QUERY = "select p from Project p";

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void insert(Project project) {
        getEntityManager().persist(project);
    }

    public List<Project> findAll() {
        Query query = entityManager.createQuery(SELECT_QUERY);
        @SuppressWarnings("unchecked")
        List<Project> projects = (List<Project>) query.getResultList();
        return projects;
    }
}