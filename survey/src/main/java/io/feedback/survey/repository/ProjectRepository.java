package io.feedback.survey.repository;

import java.util.List;

import io.feedback.core.repository.AbstractBaseRepository;
import io.feedback.survey.entity.Project;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository extends AbstractBaseRepository<Project> {

    private static final String SELECT_QUERY = "select p from Project p";

    public void insert(Project project) {
        getEntityManager().persist(project);
        getEntityManager().refresh(project);
    }

    public List<Project> fetchAll() {
        Query query = getEntityManager().createQuery(SELECT_QUERY);
        @SuppressWarnings("unchecked")
        List<Project> projects = (List<Project>) query.getResultList();
        return projects;
    }
}