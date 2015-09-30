package functional;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Question;
import io.feedback.survey.entity.Survey;
import io.feedback.survey.service.PageService;
import io.feedback.survey.service.QuestionService;
import io.feedback.survey.service.SurveyService;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class TestSpringHibernateJpa {
    
    private SurveyService surveyService;
    private PageService pageService;
    private QuestionService questionService;
    @PersistenceContext
    private EntityManager entityManager;
    
    public void test(ApplicationContext context) {

        entityManager.createNativeQuery("DELETE FROM Survey").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE Survey AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE Page AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE Question AUTO_INCREMENT = 1").executeUpdate();
        
        surveyService = (SurveyService) context.getBean("surveyService");
        pageService = (PageService) context.getBean("pageService");
        questionService = (QuestionService) context.getBean("questionService");
        
        Survey survey = createSurvey();
        createPages(survey);
        listPages();
    }
    
    public Survey createSurvey() {
        
        Survey survey = new Survey();
        survey.setName("My name");
        survey.setTitle("My title");
        
        surveyService.addSurvey(survey);

        System.out.println("Survey : " + survey + " added successfully");
        
        return survey;
    }
    
    public void createPages(Survey survey) {
        
        Page page = new Page();
        page.setName("My name");
        page.setTitle("My title");
        page.setSurvey(survey);

        pageService.addPage(page);
        System.out.println("Page #1: " + page + " added successfully");

        createQuestions(page);
        System.out.println("Questions added to Page #1");
        
        

        Page page2 = new Page();
        page2.setName("My name 2");
        page2.setTitle("My title 2");
        page2.setSurvey(survey);
        
        pageService.addPage(page2);
        
        System.out.println("Page #2: " + page2 + " added successfully");
        
        Page page3 = new Page();
        page3.setName("My name 3");
        page3.setTitle("My title 3");
        page3.setSurvey(survey);
        
        pageService.addPage(page3);
        
        System.out.println("Page #3: " + page3 + " added successfully");
    }
    
    private void createQuestions(Page page) {
        Question question1 = new Question();
        question1.setName("My name 1");
        question1.setTitle("My title 1");
        question1.setPage(page);
        questionService.addQuestion(question1);
        
        Question question2 = new Question();
        question2.setName("My name 2");
        question2.setTitle("My title 2");
        question2.setPage(page);
        questionService.addQuestion(question2);
    }
    
    public void listPages() {
        
        List<Survey> surveys = surveyService.fetchAllSurveys();
        Integer numberOfSurveys = surveys.size();
        Integer lastIndexOfSurveys = numberOfSurveys - 1;
        System.out.println(lastIndexOfSurveys);

        Survey survey = surveys.get(lastIndexOfSurveys);
        System.out.println("Pages: " + survey.getPages());
        
        System.out.println("Survey ID: " + survey.getId());
    }
}

