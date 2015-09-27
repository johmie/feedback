package io.feedback.survey.service;

import java.util.List;

import javax.persistence.EntityGraph;

import io.feedback.survey.entity.Project;
import io.feedback.survey.repository.ProjectDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectService {
    private ProjectDao projectDao;

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    @Autowired
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }
    
    public void addProject(Project project) {
        getProjectDao().insert(project);
    }
    
    public List<Project> findAllProjects() {
        return getProjectDao().findAll();
    }
}