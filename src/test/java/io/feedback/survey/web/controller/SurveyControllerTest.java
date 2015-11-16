package io.feedback.survey.web.controller;

import io.feedback.survey.entity.Page;
import io.feedback.survey.service.PageService;
import io.feedback.survey.web.exception.BadRequestException;
import io.feedback.survey.web.exception.NotFoundException;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.service.ResultService;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.persistence.NoResultException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class SurveyControllerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private SurveyController surveyController;

    @Before
    public void setUp() {
        surveyController = new SurveyController();
        surveyController.setPageService(mock(PageService.class));
        surveyController.setResultService(mock(ResultService.class));
    }

    @Test
    public void getAndSetPageService() {
        PageService pageServiceMock = mock(PageService.class);
        surveyController.setPageService(pageServiceMock);
        assertEquals(pageServiceMock, surveyController.getPageService());
    }

    @Test
    public void getAndSetResultService() {
        ResultService resultServiceMock = mock(ResultService.class);
        surveyController.setResultService(resultServiceMock);
        assertEquals(resultServiceMock, surveyController.getResultService());
    }

    @Test
    @Parameters(method = "parametersForPage")
    public void pageUsesCorrectViewDependingOnItsTypeWithRequestMethodGet(Long surveyId, Integer pageNumber,
                                                                          Page.Type type, String expectedView) {
        createPageMock(surveyId, pageNumber, type);
        String actualView = surveyController.page(surveyId, pageNumber, mock(PageModel.class), mock(Model.class));
        assertEquals(expectedView, actualView);
    }

    @Test
    @Parameters(method = "parametersForPage")
    public void pageUsesCorrectViewDependingOnItsTypeWithRequestMethodPost(long surveyId, int pageNumber,
                                                                           Page.Type type, String expectedView) {
        createPageMock(surveyId, pageNumber, type);
        String actualView = surveyController.page(surveyId, pageNumber, mock(PageModel.class),
                mock(BindingResult.class), mock(Model.class));
        assertEquals(expectedView, actualView);
    }

    private Page createPageMock(Long surveyId, Integer pageNumber, Page.Type type) {
        Page pageMock = mock(Page.class);
        when(pageMock.getType()).thenReturn(type);
        when(surveyController.getPageService().loadPage(surveyId, pageNumber)).thenReturn(pageMock);
        return pageMock;
    }

    private Object[] parametersForPage() {
        return new Object[]{
                new Object[]{1L, 1, Page.Type.ASK, "survey/page/ask"},
                new Object[]{1L, 1, Page.Type.END, "survey/page/end"},
        };
    }

    @Test
    public void pageRedirectsCorrectlyToNextPageAfterSavingValidResults() {
        long surveyId = 1L;
        int pageNumber = 1;
        PageModel pageModelMock = mock(PageModel.class);
        BindingResult bindingResultMock = mock(BindingResult.class);
        when(surveyController.getResultService().saveResultsIfValid(pageModelMock, bindingResultMock)).thenReturn(true);
        String actualView = surveyController.page(surveyId, pageNumber, pageModelMock, bindingResultMock,
                mock(Model.class));
        String expectedView = "redirect:/survey/" + surveyId + "/" + (pageNumber + 1);
        assertEquals(expectedView, actualView);
    }

    @Test
    public void pageAddsPageToModelIfTypeIsAskAndRequestMethodGet() {
        long surveyId = 1L;
        int pageNumber = 1;
        Page pageMock = createPageMock(surveyId, pageNumber, Page.Type.ASK);
        Model modelMock = mock(Model.class);
        surveyController.page(surveyId, pageNumber, mock(PageModel.class), modelMock);
        verify(modelMock).addAttribute("page", pageMock);
    }

    @Test
    public void pageAddsPageToModelIfTypeIsAskAndRequestMethodPost() {
        long surveyId = 1L;
        int pageNumber = 1;
        Page pageMock = createPageMock(surveyId, pageNumber, Page.Type.ASK);
        Model modelMock = mock(Model.class);
        surveyController.page(surveyId, pageNumber, mock(PageModel.class), mock(BindingResult.class), modelMock);
        verify(modelMock).addAttribute("page", pageMock);
    }

    @Test
    public void pageThrowsExceptionOnIllegalArgument() {
        thrown.expect(BadRequestException.class);
        long surveyId = 1L;
        int pageNumber = 1;
        PageModel pageModelMock = mock(PageModel.class);
        BindingResult bindingResultMock = mock(BindingResult.class);
        when(surveyController.getResultService().saveResultsIfValid(pageModelMock, bindingResultMock))
                .thenThrow(IllegalArgumentException.class);
        surveyController.page(surveyId, pageNumber, pageModelMock, bindingResultMock, mock(Model.class));
    }


    @Test
    public void pageThrowsExceptionOnIllegalArgumentWithRequestMethodGet() {
        thrown.expect(NotFoundException.class);
        long surveyId = 1L;
        int pageNumber = 1;
        when(surveyController.getPageService().loadPage(surveyId, pageNumber)).thenThrow(NoResultException.class);
        surveyController.page(surveyId, pageNumber, mock(PageModel.class), mock(Model.class));
    }

    @Test
    public void pageThrowsExceptionOnIllegalArgumentWithRequestMethodPost() {
        thrown.expect(NotFoundException.class);
        long surveyId = 1L;
        int pageNumber = 1;
        when(surveyController.getPageService().loadPage(surveyId, pageNumber)).thenThrow(NoResultException.class);
        surveyController.page(surveyId, pageNumber, mock(PageModel.class), mock(BindingResult.class),
                mock(Model.class));
    }

    @Test
    public void handleBadRequestExceptionReturnsCorrectView() {
        assertEquals("error/400", surveyController.handleBadRequestException());
    }

    @Test
    public void handleNotFoundExceptionReturnsCorrectView() {
        assertEquals("error/404", surveyController.handleNotFoundException());
    }
}