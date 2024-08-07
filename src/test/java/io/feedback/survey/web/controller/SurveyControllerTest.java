package io.feedback.survey.web.controller;

import io.feedback.core.factory.ObjectFactory;
import io.feedback.survey.entity.Page;
import io.feedback.survey.service.PageService;
import io.feedback.survey.web.dto.PageFormDto;
import io.feedback.survey.web.dto.ParticipationDto;
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

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
        ObjectFactory objectFactoryMock = mock(ObjectFactory.class);
        when(objectFactoryMock.createInstance(PageFormDto.class)).thenReturn(mock(PageFormDto.class));
        when(objectFactoryMock.createInstance(ParticipationDto.class)).thenReturn(mock(ParticipationDto.class));

        surveyController = new SurveyController();
        surveyController.setObjectFactory(objectFactoryMock);
        surveyController.setPageService(mock(PageService.class));
        surveyController.setResultService(mock(ResultService.class));
    }

    @Test
    public void setPageService_SomePageService_SameValueIsReturnedByGetPageService() {
        PageService pageServiceMock = mock(PageService.class);

        surveyController.setPageService(pageServiceMock);

        assertEquals(pageServiceMock, surveyController.getPageService());
    }

    @Test
    public void setResultService_SomeResultService_SameValueIsReturnedByGetResultService() {
        ResultService resultServiceMock = mock(ResultService.class);

        surveyController.setResultService(resultServiceMock);

        assertEquals(resultServiceMock, surveyController.getResultService());
    }

    @Test
    @Parameters(method = "parametersForCorrectViewIsUsedTest")
    public void page_RequestMethodIsGet_CorrectViewIsUsed(Long surveyId, Integer pageNumber,
                                                          Page.Type type, String expectedView) {
        createPageMock(surveyId, pageNumber, type);

        String actualView = surveyController.page(surveyId, pageNumber, mock(PageModel.class), mock(Model.class));

        assertEquals(expectedView, actualView);
    }

    @Test
    @Parameters(method = "parametersForCorrectViewIsUsedTest")
    public void page_RequestMethodIsPost_CorrectViewIsUsed(long surveyId, int pageNumber,
                                                           Page.Type type, String expectedView) {
        createPageMock(surveyId, pageNumber, type);
        HttpServletRequest httpServletRequestMock = createHttpServletRequestMock(null, null);

        String actualView = surveyController.page(surveyId, pageNumber, mock(PageModel.class),
                mock(BindingResult.class), mock(Model.class), httpServletRequestMock);

        assertEquals(expectedView, actualView);
    }

    @Test
    public void page_HttpServletRequestWithSessionId_SessionIdIsSetAsParticipationIdentifier() {
        String sessionId = "Participation identifier";
        long surveyId = 1L;
        int pageNumber = 1;
        createPageMock(surveyId, pageNumber, Page.Type.ASK);
        HttpServletRequest httpServletRequestMock = createHttpServletRequestMock(sessionId, null);
        ParticipationDto participationDtoMock = mock(ParticipationDto.class);
        when(surveyController.getObjectFactory().createInstance(ParticipationDto.class))
                .thenReturn(participationDtoMock);

        surveyController.page(surveyId, pageNumber, mock(PageModel.class), mock(BindingResult.class), mock(Model.class),
                httpServletRequestMock);

        verify(participationDtoMock).setIdentifier(sessionId);
    }

    @Test
    public void page_HttpServletRequestWithRemoteAddress_RemoteAddressIsAppliedFromHttpServletRequest() {
        String remoteAddress = "127.0.0.1";
        Long surveyId = 1L;
        Integer pageNumber = 1;
        createPageMock(surveyId, pageNumber, Page.Type.ASK);
        HttpServletRequest httpServletRequestMock = createHttpServletRequestMock(null, remoteAddress);
        ParticipationDto participationDtoMock = mock(ParticipationDto.class);
        when(surveyController.getObjectFactory().createInstance(ParticipationDto.class))
                .thenReturn(participationDtoMock);

        surveyController.page(surveyId, pageNumber, mock(PageModel.class), mock(BindingResult.class), mock(Model.class),
                httpServletRequestMock);

        verify(participationDtoMock).setRemoteAddress(remoteAddress);
    }

    @Test
    public void page_ResultsAreSaved_CorrectRedirectUrlIsReturned() {
        Long surveyId = 1L;
        Integer pageNumber = 1;
        HttpServletRequest httpServletRequestMock = createHttpServletRequestMock(null, null);
        when(surveyController.getResultService()
                .saveResultsIfValid(any(PageFormDto.class), any(ParticipationDto.class))).thenReturn(true);

        String actualView = surveyController.page(surveyId, pageNumber, mock(PageModel.class),
                mock(BindingResult.class), mock(Model.class), httpServletRequestMock);

        String expectedView = "redirect:/survey/" + surveyId + "/" + (pageNumber + 1);
        assertEquals(expectedView, actualView);
    }

    @Test
    public void page_RequestMethodIsGetAndPageTypeIsAsk_PageIsAddedToModel() {
        Long surveyId = 1L;
        Integer pageNumber = 1;
        Page pageMock = createPageMock(surveyId, pageNumber, Page.Type.ASK);
        Model modelMock = mock(Model.class);

        surveyController.page(surveyId, pageNumber, mock(PageModel.class), modelMock);

        verify(modelMock).addAttribute("page", pageMock);
    }

    @Test
    public void page_RequestMethodIsPostAndPageTypeIsAsk_PageIsAddedToModel() {
        Long surveyId = 1L;
        Integer pageNumber = 1;
        Page pageMock = createPageMock(surveyId, pageNumber, Page.Type.ASK);
        Model modelMock = mock(Model.class);
        HttpServletRequest httpServletRequestMock = createHttpServletRequestMock(null, null);

        surveyController.page(surveyId, pageNumber, mock(PageModel.class), mock(BindingResult.class), modelMock,
                httpServletRequestMock);

        verify(modelMock).addAttribute("page", pageMock);
    }

    @Test
    public void page_RequestMethodIsGetAndNoResultExceptionIsThrown_NotFoundExceptionIsThrown() {
        Long surveyId = 1L;
        Integer pageNumber = 1;
        when(surveyController.getPageService().loadPage(surveyId, pageNumber)).thenThrow(NoResultException.class);

        thrown.expect(NotFoundException.class);

        surveyController.page(surveyId, pageNumber, mock(PageModel.class), mock(Model.class));
    }

    @Test
    public void page_RequestMethodIsPostAndNoResultExceptionIsThrown_NotFoundExceptionIsThrown() {
        Long surveyId = 1L;
        Integer pageNumber = 1;
        when(surveyController.getPageService().loadPage(surveyId, pageNumber)).thenThrow(NoResultException.class);

        thrown.expect(NotFoundException.class);

        surveyController.page(surveyId, pageNumber, mock(PageModel.class), mock(BindingResult.class), mock(Model.class),
                mock(HttpServletRequest.class));
    }

    @Test
    public void handleNotFoundException_InstantiatedSurveyController_CorrectViewIsReturned() {
        String view = surveyController.handleNotFoundException();

        assertEquals("error/404", view);
    }

    private Page createPageMock(Long surveyId, Integer pageNumber, Page.Type type) {
        Page pageMock = mock(Page.class);
        when(pageMock.getType()).thenReturn(type);
        when(surveyController.getPageService().loadPage(surveyId, pageNumber)).thenReturn(pageMock);
        return pageMock;
    }

    private HttpServletRequest createHttpServletRequestMock(String sessionId, String remoteAddress) {
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        when(httpServletRequestMock.getRemoteAddr()).thenReturn(remoteAddress);
        HttpSession httpSessionMock = mock(HttpSession.class);
        when(httpSessionMock.getId()).thenReturn(sessionId);
        when(httpServletRequestMock.getSession()).thenReturn(httpSessionMock);
        return httpServletRequestMock;
    }

    private Object[] parametersForCorrectViewIsUsedTest() {
        return new Object[]{
                new Object[]{1L, 1, Page.Type.ASK, "survey/page/ask"},
                new Object[]{1L, 1, Page.Type.END, "survey/page/end"},
        };
    }
}