package io.feedback.survey.service;

import java.util.List;

import io.feedback.survey.entity.Page;
import io.feedback.survey.repository.PageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageService {
    private PageRepository pageRepository;

    public PageRepository getPageRepository() {
        return pageRepository;
    }

    @Autowired
    public void setProjectRepository(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }
    
    public void addProject(Page page) {
        getPageRepository().insert(page);
    }
    
    public List<Page> findAllProjects() {
        return getPageRepository().findAll();
    }
}