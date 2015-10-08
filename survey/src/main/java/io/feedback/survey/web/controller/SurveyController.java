package io.feedback.survey.web.controller;

import io.feedback.survey.entity.Page;
import io.feedback.survey.service.SurveyService;
import io.feedback.survey.web.model.PageModel;

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

    private SurveyService surveyService;

    public SurveyService getSurveyService() {
        return surveyService;
    }

    @Autowired
    public void setSurveyService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping(value = "/survey/{surveyId}/{pageNumber}", method = RequestMethod.GET)
    public String page(
            @PathVariable Long surveyId,
            @PathVariable Integer pageNumber,
            @ModelAttribute("pageModel") PageModel pageModel,
            Model model) {
        Page page = getSurveyService().loadPage(surveyId, pageNumber);
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
        Page page = getSurveyService().loadPage(surveyId, pageNumber);
        getSurveyService().saveResultsIfValid(pageModel, bindingResult);
        model.addAttribute("page", page);
        return "survey/page";
    }
}