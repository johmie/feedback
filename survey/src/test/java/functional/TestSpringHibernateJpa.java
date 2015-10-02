package functional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Question;
import io.feedback.survey.entity.Question.Type;
import io.feedback.survey.entity.Survey;
import io.feedback.survey.service.AnswerService;
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
    private AnswerService answerService;
    @PersistenceContext
    private EntityManager entityManager;
    
    public void test(ApplicationContext context) {

        entityManager.createNativeQuery("DELETE FROM Survey").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE Survey AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE Page AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE Question AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE Answer AUTO_INCREMENT = 1").executeUpdate();
        entityManager.createNativeQuery("ALTER TABLE Result AUTO_INCREMENT = 1").executeUpdate();
        
        surveyService = (SurveyService) context.getBean("surveyService");
        pageService = (PageService) context.getBean("pageService");
        questionService = (QuestionService) context.getBean("questionService");
        answerService = (AnswerService) context.getBean("answerService");
        
        Survey survey = createSurvey();
        createPages(survey);
        System.out.println("End of test");
        //listPages();
    }
    
    public Survey createSurvey() {
        
        Survey survey = new Survey();
        survey.setName("Internal name of my first project");
        survey.setTitle("Title of my first project");
        
        surveyService.saveSurvey(survey);

        System.out.println("Survey: " + survey + " added successfully");
        
        return survey;
    }
    
    public void createPages(Survey survey) {
        
        Page page = new Page();
        page.setName("Page #1");
        page.setTitle("Page #1");
        page.setSurvey(survey);
        pageService.savePage(page);
        System.out.println("Page #1: " + page + " added successfully");

//        Page page2 = new Page();
//        page2.setName("Page #2");
//        page2.setTitle("Page #2");
//        page2.setSurvey(survey);
//        pageService.addPage(page2);
//        System.out.println("Page #2: " + page2 + " added successfully");
//        
//        Page page3 = new Page();
//        page3.setName("Page #3");
//        page3.setTitle("Page #3");
//        page3.setSurvey(survey);
//        pageService.addPage(page3);
//        System.out.println("Page #3: " + page3 + " added successfully");
        
        createQuestions(page);
        System.out.println("Questions added to Page #1");
    }
    
    private void createQuestions(Page page) {
        
        Question question1 = new Question();
        question1.setType(Type.SINGLE);
        question1.setName("Schönste Farbe");
        question1.setTitle("Einfachauswahl: Welche Farbe ist die schönste?");
        question1.setPosition(1);
        question1.setPage(page);
        questionService.saveQuestion(question1);
        createAnswers1(question1);
        
        Question question2 = new Question();
        question2.setType(Type.MULTIPLE);
        question2.setName("Farben");
        question2.setTitle("Mehrfachauswahl: Welche Farben findest du schön?");
        question2.setPosition(2);
        question2.setPage(page);
        questionService.saveQuestion(question2);
        createAnswers1(question2);
    }

    private void createAnswers1(Question question) {
        
        Answer answer1 = new Answer();
        answer1.setTitle("Antwortoption #1");
        answer1.setName("grün");
        answer1.setValue("#00ff00");
        answer1.setPosition(1);
        answer1.setQuestion(question);
        answerService.saveAnswer(answer1);
        
        Answer answer2 = new Answer();
        answer2.setTitle("Antwortoption #2");
        answer2.setName("rot");
        answer2.setValue("#ff0000");
        answer2.setPosition(2);
        answer2.setQuestion(question);
        answerService.saveAnswer(answer2);
        
        Answer answer3 = new Answer();
        answer3.setTitle("Antwortoption #3");
        answer3.setName("blau");
        answer3.setValue("#0000ff");
        answer3.setPosition(3);
        answer3.setQuestion(question);
        answerService.saveAnswer(answer3);
        answer3.setName("blau resaved");
        answerService.saveAnswer(answer3);
        
        answerService.deleteAnswer(answer2);
    }
}
