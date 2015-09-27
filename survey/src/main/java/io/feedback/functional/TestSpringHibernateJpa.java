package io.feedback.functional;

import io.feedback.survey.entity.Project;
import io.feedback.survey.service.ProjectService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringHibernateJpa {
      
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-config.xml");
        ProjectService projectService = (ProjectService) context
                .getBean("projectService");

        Project project = new Project();
        project.setName("My name");
        project.setTitle("My title");
        projectService.addProject(project);
        System.out.println("Project : " + project + " added successfully");

        System.out.println(projectService.findAllProjects().toString());
        
        ((ConfigurableApplicationContext)context).close();
    }
}
