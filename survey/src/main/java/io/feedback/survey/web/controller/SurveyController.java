package io.feedback.survey.web.controller;

import javax.validation.Valid;

import io.feedback.survey.entity.Page;
import io.feedback.survey.service.SurveyService;
import io.feedback.survey.web.model.PageFormModel;
import io.feedback.survey.web.validator.PageFormValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SurveyController {

    @Autowired
    private PageFormValidator pageFormValidator;

    private SurveyService surveyService;

    public SurveyService getSurveyService() {
        return surveyService;
    }

    @Autowired
    public void setSurveyService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @InitBinder("pageFormModel")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(pageFormValidator);
    }

    @RequestMapping(value = "/survey/{surveyId}/{pageNumber}", method = RequestMethod.GET)
    public String page(
            @PathVariable Long surveyId,
            @PathVariable Integer pageNumber,
            @ModelAttribute("pageFormModel") PageFormModel pageFormModel,
            Model model) {
        Page page = getSurveyService().loadPage(surveyId, pageNumber);
        model.addAttribute("page", page);
        return "survey/page";
    }

    @RequestMapping(value = "/survey/{surveyId}/{pageNumber}", method = RequestMethod.POST)
    public String page(
            @PathVariable Long surveyId,
            @PathVariable Integer pageNumber,
            @Valid @ModelAttribute("pageFormModel") PageFormModel pageFormModel,
            BindingResult result, Model model) {
        //Boolean hasErrors = result.hasErrors();
        Page page = getSurveyService().loadPage(surveyId, pageNumber);
        model.addAttribute("page", page);
        return "survey/page";
    }
}