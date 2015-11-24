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

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
        ObjectFactory objectFactoryMock = mock(ObjectFactory.class);
        when(objectFactoryMock.createInstance(PageFormDto.class)).thenReturn(mock(PageFormDto.class));
        when(objectFactoryMock.createInstance(ParticipationDto.class)).thenReturn(mock(ParticipationDto.class));
        surveyController.setObjectFactory(objectFactoryMock);
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
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        when(httpServletRequestMock.getSession()).thenReturn(mock(HttpSession.class));
        String actualView = surveyController.page(surveyId, pageNumber, mock(PageModel.class),
                mock(BindingResult.class), mock(Model.class), httpServletRequestMock);
        assertEquals(expectedView, actualView);
    }

    @Test
    public void pageSetsSessionIdAsParticipationIdentifier() {
        String participationIdentifier = "Participation identifier";
        long surveyId = 1L;
        int pageNumber = 1;
        PageModel pageModelMock = mock(PageModel.class);
        BindingResult bindingResultMock = mock(BindingResult.class);
        Page pageMock = createPageMock(surveyId, pageNumber, Page.Type.ASK);

        PageFormDto pageFormDtoMock = mock(PageFormDto.class);
        when(pageFormDtoMock.getPageModel()).thenReturn(pageModelMock);
        when(pageFormDtoMock.getBindingResult()).thenReturn(bindingResultMock);
        when(pageFormDtoMock.getPage()).thenReturn(pageMock);

        ParticipationDto participationDtoMock = mock(ParticipationDto.class);

        HttpSession httpSessionMock = mock(HttpSession.class);
        when(httpSessionMock.getId()).thenReturn(participationIdentifier);
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        when(httpServletRequestMock.getSession()).thenReturn(httpSessionMock);

        when(surveyController.getObjectFactory().createInstance(PageFormDto.class)).thenReturn(pageFormDtoMock);
        when(surveyController.getObjectFactory().createInstance(ParticipationDto.class))
                .thenReturn(participationDtoMock);

        surveyController.page(surveyId, pageNumber, pageModelMock, bindingResultMock,
                mock(Model.class), httpServletRequestMock);

        verify(participationDtoMock).setIdentifier(participationIdentifier);
    }

    @Test
    public void pageSetsRemoteAddressFromRequest() {
        String remoteAddress = "127.0.0.1";
        long surveyId = 1L;
        int pageNumber = 1;
        PageModel pageModelMock = mock(PageModel.class);
        BindingResult bindingResultMock = mock(BindingResult.class);
        Page pageMock = createPageMock(surveyId, pageNumber, Page.Type.ASK);

        PageFormDto pageFormDtoMock = mock(PageFormDto.class);
        when(pageFormDtoMock.getPageModel()).thenReturn(pageModelMock);
        when(pageFormDtoMock.getBindingResult()).thenReturn(bindingResultMock);
        when(pageFormDtoMock.getPage()).thenReturn(pageMock);

        ParticipationDto participationDtoMock = mock(ParticipationDto.class);

        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        when(httpServletRequestMock.getRemoteAddr()).thenReturn(remoteAddress);
        when(httpServletRequestMock.getSession()).thenReturn(mock(HttpSession.class));

        when(surveyController.getObjectFactory().createInstance(PageFormDto.class)).thenReturn(pageFormDtoMock);
        when(surveyController.getObjectFactory().createInstance(ParticipationDto.class))
                .thenReturn(participationDtoMock);

        surveyController.page(surveyId, pageNumber, pageModelMock, bindingResultMock,
                mock(Model.class), httpServletRequestMock);

        verify(participationDtoMock).setRemoteAddress(remoteAddress);
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
        Page pageMock = mock(Page.class);

        PageFormDto pageFormDtoMock = mock(PageFormDto.class);
        when(pageFormDtoMock.getPageModel()).thenReturn(pageModelMock);
        when(pageFormDtoMock.getBindingResult()).thenReturn(bindingResultMock);
        when(pageFormDtoMock.getPage()).thenReturn(pageMock);

        ParticipationDto participationDtoMock = mock(ParticipationDto.class);

        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        when(httpServletRequestMock.getSession()).thenReturn(mock(HttpSession.class));

        when(surveyController.getObjectFactory().createInstance(PageFormDto.class)).thenReturn(pageFormDtoMock);
        when(surveyController.getObjectFactory().createInstance(ParticipationDto.class))
                .thenReturn(participationDtoMock);
        when(surveyController.getResultService()
                .saveResultsIfValid(pageFormDtoMock, participationDtoMock)).thenReturn(true);

        String actualView = surveyController.page(surveyId, pageNumber, pageModelMock, bindingResultMock,
                mock(Model.class), httpServletRequestMock);
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
        HttpServletRequest httpServletRequestMock = mock(HttpServletRequest.class);
        when(httpServletRequestMock.getSession()).thenReturn(mock(HttpSession.class));
        surveyController.page(surveyId, pageNumber, mock(PageModel.class), mock(BindingResult.class), modelMock,
                httpServletRequestMock);
        verify(modelMock).addAttribute("page", pageMock);
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
        surveyController.page(surveyId, pageNumber, mock(PageModel.class), mock(BindingResult.class), mock(Model.class),
                mock(HttpServletRequest.class));
    }

    @Test
    public void handleNotFoundExceptionReturnsCorrectView() {
        assertEquals("error/404", surveyController.handleNotFoundException());
    }
}