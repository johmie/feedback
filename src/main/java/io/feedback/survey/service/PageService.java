package io.feedback.survey.service;

import io.feedback.survey.entity.Page;
import io.feedback.survey.repository.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page loadPage(Long surveyId, Integer pageNumber) {
        List<Page> pages = getPageRepository().findBySurveyId(surveyId);
        return pages.isEmpty() ? null : pages.get(pageNumber - 1);
    }
}