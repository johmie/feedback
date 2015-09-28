package io.feedback.web.controller;

import io.feedback.survey.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectController {
    
    private ProjectService projectService;

    public ProjectService getProjectService() {
        return projectService;
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "/survey")
    public ModelAndView renderPage() {
        //Project project = getProjectService().fetchProjectById(1L);
        String message = "<br><div style='text-align:center;'>"
                + "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
        return new ModelAndView("survey/page", "message", message);
    }
    
    public String process() {
        return "processed";
    }
}
