package io.feedback.survey.repository;

import io.feedback.survey.entity.Page;
import junitparams.JUnitParamsRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class PageRepositoryTest {

    private PageRepository pageRepository;

    private Query queryMock;

    @Before
    public void setUp() {
        pageRepository = new PageRepository();
        pageRepository.setEntityManager(mock(EntityManager.class));

        queryMock = mock(Query.class);
        when(pageRepository.getEntityManager().createQuery(anyString())).thenReturn(queryMock);
    }

    @Test
    public void findBySurveyIdAndPageNumberUsesCorrectQuery() {
        pageRepository.findBySurveyIdAndPageNumber(1L, 1);
        verify(pageRepository.getEntityManager()).createQuery(
                "from Page p " +
                        "left join fetch p.questions q " +
                        "left join fetch q.answers a " +
                        "where p.survey.id = :surveyId " +
                        "order by p.position, q.position, a.position");
    }

    @Test
    public void findBySurveyIdAndPageNumberUsesCorrectSurveyId() {
        Long surveyId = 1L;
        pageRepository.findBySurveyIdAndPageNumber(surveyId, 1);
        verify(queryMock).setParameter("surveyId", surveyId);
    }

    @Test
    public void findBySurveyIdAndPageNumberSetsFirstResultCorrectly() {
        Integer pageNumber = 1;
        Integer firstResult = pageNumber - 1;
        pageRepository.findBySurveyIdAndPageNumber(1L, pageNumber);
        verify(queryMock).setFirstResult(firstResult);
    }

    @Test
    public void findBySurveyIdAndPageNumberSetsMaxResultsToOne() {
        pageRepository.findBySurveyIdAndPageNumber(1L, 1);
        verify(queryMock).setMaxResults(1);
    }

    @Test
    public void findBySurveyIdAndPageNumberReturnsPage() {
        Page pageMock = mock(Page.class);
        when(queryMock.getSingleResult()).thenReturn(pageMock);
        Page page = pageRepository.findBySurveyIdAndPageNumber(1L, 1);
        assertEquals(pageMock, page);
    }
}