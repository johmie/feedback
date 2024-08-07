package io.feedback.survey.web.controller;

import io.feedback.core.factory.ObjectFactory;
import io.feedback.survey.entity.Page;
import io.feedback.survey.service.PageService;
import io.feedback.survey.web.dto.PageFormDto;
import io.feedback.survey.web.dto.ParticipationDto;
import io.feedback.survey.web.exception.NotFoundException;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SurveyController {

    private PageService pageService;

    private ResultService resultService;

    private ObjectFactory objectFactory;

    public PageService getPageService() {
        return pageService;
    }

    @Autowired
    public void setPageService(PageService pageService) {
        this.pageService = pageService;
    }

    public ResultService getResultService() {
        return resultService;
    }

    @Autowired
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }

    public ObjectFactory getObjectFactory() {
        return objectFactory;
    }

    @Autowired
    public void setObjectFactory(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    @RequestMapping(value = "/survey/{surveyId}/{pageNumber}", method = RequestMethod.GET)
    public String page(
            @PathVariable Long surveyId,
            @PathVariable Integer pageNumber,
            @ModelAttribute("pageModel") PageModel pageModel,
            Model model) {
        try {
            Page page = getPageService().loadPage(surveyId, pageNumber);
            return modelAndViewPage(page, model);
        } catch (NoResultException noResultException) {
            throw new NotFoundException();
        }
    }

    @RequestMapping(value = "/survey/{surveyId}/{pageNumber}", method = RequestMethod.POST)
    public String page(
            @PathVariable Long surveyId,
            @PathVariable Integer pageNumber,
            @ModelAttribute("pageModel") PageModel pageModel,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest request) {
        try {
            Page page = getPageService().loadPage(surveyId, pageNumber);
            PageFormDto pageFormDto = createPageFormDto(pageModel, bindingResult, page);
            ParticipationDto participationDto
                    = createParticipationDto(request.getSession().getId(), request.getRemoteAddr());
            if (getResultService().saveResultsIfValid(pageFormDto, participationDto)) {
                return "redirect:/survey/" + surveyId + "/" + (pageNumber + 1);
            } else {
                return modelAndViewPage(page, model);
            }
        } catch (NoResultException noResultException) {
            throw new NotFoundException();
        }
    }

    private PageFormDto createPageFormDto(PageModel pageModel, BindingResult bindingResult, Page page) {
        Object pageFormDtoAsObject = getObjectFactory().createInstance(PageFormDto.class);
        PageFormDto pageFormDto = (PageFormDto) pageFormDtoAsObject;
        pageFormDto.setPageModel(pageModel);
        pageFormDto.setBindingResult(bindingResult);
        pageFormDto.setPage(page);
        return pageFormDto;
    }

    private ParticipationDto createParticipationDto(String identifier, String remoteAddress) {
        Object participationDtoAsObject = getObjectFactory().createInstance(ParticipationDto.class);
        ParticipationDto participationDto = (ParticipationDto) participationDtoAsObject;
        participationDto.setIdentifier(identifier);
        participationDto.setRemoteAddress(remoteAddress);
        return participationDto;
    }

    private String modelAndViewPage(Page page, Model model) {
        if (page.getType() == Page.Type.END) {
            return "survey/page/end";
        }
        model.addAttribute("page", page);
        return "survey/page/ask";
    }

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException() {
        return "error/404";
    }
}