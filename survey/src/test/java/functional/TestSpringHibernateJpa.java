package functional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    @PersistenceContext
    private EntityManager entityManager;
    
    public void test(ApplicationContext context) {

        entityManager.createNativeQuery("delete from Project").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE Project AUTO_INCREMENT = 1").executeUpdate();
        
        entityManager.createNativeQuery("delete from Page").executeUpdate();
        entityManager.createNativeQuery("truncate table Page").executeUpdate();
        
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
        
        projectService.addProject(project);

        System.out.println("Project : " + project + " added successfully");
        
        return project;
    }
    
    public void createPages(Project project) {
        
        Page page = new Page();
        page.setName("My name");
        page.setTitle("My title");
        page.setProject(project);
        
        pageService.addPage(page);
        
        System.out.println("Page #1: " + page + " added successfully");
        
        Page page2 = new Page();
        page2.setName("My name 2");
        page2.setTitle("My title 2");
        page2.setProject(project);
        
        pageService.addPage(page2);
        
        System.out.println("Page #2: " + page2 + " added successfully");
        
        Page page3 = new Page();
        page3.setName("My name 3");
        page3.setTitle("My title 3");
        page3.setProject(project);
        
        pageService.addPage(page3);
        
        System.out.println("Page #3: " + page3 + " added successfully");
    }
    
    public void listPages() {
        
        List<Project> projects = projectService.fetchAllProjects();
        Integer numberOfProjects = projects.size();
        Integer lastIndexOfProjects = numberOfProjects - 1;
        System.out.println(lastIndexOfProjects);

        Project project = projects.get(lastIndexOfProjects);
        System.out.println("Pages: " + project.getPages());
        
        System.out.println("Project ID: " + project.getId());
    }
}

