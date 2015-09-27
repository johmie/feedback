package functional;

import java.util.List;

import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Project;
import io.feedback.survey.service.PageService;
import io.feedback.survey.service.ProjectService;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TestSpringHibernateJpa {
    
    private ProjectService projectService;
    private PageService pageService;
    
    public void test(ApplicationContext context) {
        projectService = (ProjectService) context.getBean("projectService");
        pageService = (PageService) context.getBean("pageService");
        
        Project project = createProject();
        createPages(project);
        listPages();
    }
    
    public Project createProject() {
        
        Project project = new Project();
        project.setName("My name");
        project.setTitle("My title");
        
        this.projectService.addProject(project);

        System.out.println("Project : " + project + " added successfully");
        
        return project;
    }
    
    public void createPages(Project project) {
        
        Page page = new Page();
        page.setName("My name");
        page.setTitle("My title");
        page.setProject(project);
        
        this.pageService.addProject(page);
        
        Page page2 = new Page();
        page2.setName("My name 2");
        page2.setTitle("My title 2");
        page2.setProject(project);
        
        this.pageService.addProject(page2);
        
        Page page3 = new Page();
        page3.setName("My name 3");
        page3.setTitle("My title 3");
        page3.setProject(project);
        
        this.pageService.addProject(page3);
    }
    
    public void listPages() {
        
        List<Project> projects = this.projectService.findAllProjects();
        Integer numberOfProjects = projects.size();
        Integer lastIndexOfProjects = numberOfProjects - 1;
        Project project = projects.get(lastIndexOfProjects);
        System.out.println(project.getPages().size());
    }
}

