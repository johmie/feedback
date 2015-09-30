package io.feedback.web.controller;

import io.feedback.survey.entity.Page;
import io.feedback.survey.service.SurveyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SurveyController {

    private SurveyService surveyService;

    public SurveyService getSurveyService() {
        return surveyService;
    }

    @Autowired
    public void setProjectService(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @RequestMapping(value = "/survey/{surveyId}/{pageNumber}")
    public String renderPage(@PathVariable Long surveyId,
            @PathVariable Integer pageNumber, Model model) {
        Page page = getSurveyService().fetchPageOfSurveyByPageNumber(surveyId,
                pageNumber);
        model.addAttribute("page", page);
        return "survey/page";
    }

    public String process() {
        return "processed";
    }
}