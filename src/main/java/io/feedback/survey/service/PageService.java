package io.feedback.survey.service;

import io.feedback.survey.entity.Page;
import io.feedback.survey.repository.PageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    private PageRepository pageRepository;

    public PageRepository getPageRepository() {
        return pageRepository;
    }

    @Autowired
    public void setPageRepository(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public void savePage(Page page) {
        getPageRepository().insertOrUpdate(page);
    }

    public Page loadPage(Long surveyId, Integer pageNumber) {
        return getPageRepository().findBySurveyIdAndPageNumber(surveyId, pageNumber);
    }
}