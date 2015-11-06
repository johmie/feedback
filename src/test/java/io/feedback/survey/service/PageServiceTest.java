package io.feedback.survey.service;

import io.feedback.survey.entity.Page;
import io.feedback.survey.repository.PageRepository;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class PageServiceTest {

    private PageService pageService;

    @Before
    public void setUp() {
        pageService = new PageService();
        PageRepository pageRepositoryMock = mock(PageRepository.class);
        pageService.setPageRepository(pageRepositoryMock);
    }

    @Test
    public void getAndSetPageRepositoryWorks() {
        PageRepository pageRepositoryMock = mock(PageRepository.class);
        pageService.setPageRepository(pageRepositoryMock);
        assertEquals(pageRepositoryMock, pageService.getPageRepository());
    }

    @Test
    public void savePageCallsSaveMethodInRepository() {
        Page pageMock = mock(Page.class);
        pageService.savePage(pageMock);
        verify(pageService.getPageRepository()).insertOrUpdate(pageMock);
    }

    @Test
    public void loadPageCallsFindMethodInRepository() {
        pageService.loadPage(1L, 1);
        verify(pageService.getPageRepository()).findBySurveyIdAndPageNumber(1L, 1);
    }
}