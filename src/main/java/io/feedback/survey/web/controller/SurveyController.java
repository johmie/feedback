package io.feedback.survey.web.controller;

import io.feedback.survey.entity.Page;
import io.feedback.survey.service.PageService;
import io.feedback.survey.web.exception.BadRequestException;
import io.feedback.survey.web.exception.NotFoundException;
import io.feedback.survey.web.model.PageModel;

import io.feedback.survey.web.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@Controller
public class SurveyController {

    private PageService pageService;

    private ResultService resultService;

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

    @RequestMapping(value = "/survey/{surveyId}/{pageNumber}", method = RequestMethod.GET)
    public String page(
            @PathVariable Long surveyId,
            @PathVariable Integer pageNumber,
            @ModelAttribute("pageModel") PageModel pageModel,
            Model model) {
        try {
            return modelAndViewPage(surveyId, pageNumber, model);
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
            Model model) {
        try {
            if (getResultService().saveResultsIfValid(pageModel, bindingResult)) {
                return "redirect:/survey/" + surveyId + "/" + (pageNumber + 1);
            } else {
                return modelAndViewPage(surveyId, pageNumber, model);
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            throw new BadRequestException();
        } catch (NoResultException noResultException) {
            throw new NotFoundException();
        }
    }

    private String modelAndViewPage(Long surveyId, Integer pageNumber, Model model) {
        Page page = getPageService().loadPage(surveyId, pageNumber);
        if (page.getType() == Page.Type.END) {
            return "survey/page/end";
        }
        model.addAttribute("page", page);
        return "survey/page/ask";
    }

    @ExceptionHandler(BadRequestException.class)
    public String handleBadRequestException() {
        return "error/400";
    }

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException() {
        return "error/404";
    }
}