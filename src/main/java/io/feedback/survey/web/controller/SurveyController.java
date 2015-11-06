package io.feedback.survey.web.controller;

import io.feedback.survey.entity.Page;
import io.feedback.survey.service.PageService;
import io.feedback.survey.web.model.PageModel;

import io.feedback.survey.web.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        Page page = getPageService().loadPage(surveyId, pageNumber);
        model.addAttribute("page", page);
        return "survey/page";
    }

    @RequestMapping(value = "/survey/{surveyId}/{pageNumber}", method = RequestMethod.POST)
    public String page(
            @PathVariable Long surveyId,
            @PathVariable Integer pageNumber,
            @ModelAttribute("pageModel") PageModel pageModel,
            BindingResult bindingResult,
            Model model) {
        getResultService().saveResultsIfValid(pageModel, bindingResult);
        Page page = getPageService().loadPage(surveyId, pageNumber);
        model.addAttribute("page", page);
        return "survey/page";
    }
}