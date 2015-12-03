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
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
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
    public void savePage_SomePage_SaveMethodOfPageRepositoryIsCalled() {
        Page pageMock = mock(Page.class);

        pageService.savePage(pageMock);

        verify(pageService.getPageRepository()).insertOrUpdate(pageMock);
    }

    @Test
    public void loadPage_SomeArguments_PageIsReturned() {
        Long surveyId = 1L;
        Integer pageNumber = 1;
        Page pageMock = mock(Page.class);
        when(pageService.getPageRepository().findBySurveyIdAndPageNumber(surveyId, pageNumber)).thenReturn(pageMock);

        Page page = pageService.loadPage(surveyId, pageNumber);

        assertEquals(pageMock, page);
    }
}