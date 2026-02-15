package io.feedback.survey.service;

import io.feedback.survey.entity.Page;
import io.feedback.survey.repository.PageRepository;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class PageServiceTest {

    private PageService pageService;

    @Before
    public void setUp() {
        pageService = new PageService();
        pageService.setPageRepository(mock(PageRepository.class));
    }

    @Test
    public void setPageRepository_SomePageRepository_SameValueIsReturnedByGetPageRepository() {
        PageRepository pageRepositoryMock = mock(PageRepository.class);

        pageService.setPageRepository(pageRepositoryMock);

        assertEquals(pageRepositoryMock, pageService.getPageRepository());
    }

    @Test
    public void loadPage_SomeArguments_PageIsReturned() {
        Long surveyId = 1L;
        Integer pageNumber = 1;
        Page pageMock = mock(Page.class);
        List<Page> pages = new ArrayList<>();
        pages.add(pageMock);
        when(pageService.getPageRepository().findBySurveyId(surveyId)).thenReturn(pages);

        Page page = pageService.loadPage(surveyId, pageNumber);

        assertEquals(pageMock, page);
    }

    @Test
    public void loadPage_WhenNoPages_ReturnsNull() {
        Long surveyId = 1L;
        Integer pageNumber = 1;
        List<Page> pages = new ArrayList<>();
        when(pageService.getPageRepository().findBySurveyId(surveyId)).thenReturn(pages);

        Page page = pageService.loadPage(surveyId, pageNumber);

        assertNull(page);
    }
}