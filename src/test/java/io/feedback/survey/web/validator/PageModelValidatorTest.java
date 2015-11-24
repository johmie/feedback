package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Question;
import io.feedback.survey.repository.QuestionRepository;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.model.PageModelProvider;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
@ContextConfiguration(locations = {"/test-spring-config.xml"})
public class PageModelValidatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private PageModelValidator pageModelValidator;

    @Before
    public void setUp() {
        pageModelValidator = new PageModelValidator();
        pageModelValidator.setQuestionModelValidator(mock(QuestionModelValidator.class));
        pageModelValidator.setQuestionRepository(mock(QuestionRepository.class));
    }

    @Test
    public void getAndSetQuestionRepository() {
        QuestionRepository questionRepository = mock(QuestionRepository.class);
        pageModelValidator.setQuestionRepository(questionRepository);
        assertEquals(questionRepository, pageModelValidator.getQuestionRepository());
    }

    @Test
    public void getAndSetQuestionModelValidator() {
        QuestionModelValidator questionModelValidator = mock(QuestionModelValidator.class);
        pageModelValidator.setQuestionModelValidator(questionModelValidator);
        assertEquals(questionModelValidator, pageModelValidator.getQuestionModelValidator());
    }

    @Test
    @Parameters(source = PageModelProvider.class, method = "provideForNoQuestionAnswered")
    public void validateSetsCorrectErrorsWhenNoQuestionIsAnswered(PageModel pageModelMock, Page pageMock) {
        Errors errorsMock = mock(Errors.class);
        when(pageModelMock.getQuestionModels()).thenReturn(null);
        pageModelValidator.validate(pageModelMock, errorsMock, pageMock);
        for (Question questionMock : pageMock.getQuestions()) {
            verify(errorsMock, times(1))
                    .rejectValue("questionModels[" + questionMock.getId() + "]", "error.question_not_answered");
        }
    }

    @Test
    @Parameters(source = PageModelProvider.class, method = "provideForNoQuestionAnsweredWithCountOfQuestions")
    public void validateSetsErrorForAnyQuestionOnPageWhenNoQuestionIsAnswered(PageModel pageModelMock, Page pageMock,
                                                                              int countOfQuestions) {
        Errors errorsMock = mock(Errors.class);
        when(pageModelMock.getQuestionModels()).thenReturn(null);
        pageModelValidator.validate(pageModelMock, errorsMock, pageMock);
        verify(errorsMock, times(countOfQuestions)).rejectValue(anyString(), eq("error.question_not_answered"));
    }

    @Test
    @Parameters(source = PageModelProvider.class, method = "provideForCountQuestions")
    public void validateLoadsAnyQuestionFromPage(PageModel pageModelMock, int countOfQuestions) {
        pageModelValidator.validate(pageModelMock, mock(Errors.class), mock(Page.class));
        for (long i = 1; i <= countOfQuestions; i++) {
            verify(pageModelValidator.getQuestionRepository()).findById(i);
        }
    }

    @Test
    @Parameters(source = PageModelProvider.class, method = "provideForCountQuestions")
    public void validateValidatesAnyQuestionFromPage(PageModel pageModelMock, int countOfQuestions) {
        Errors errorsMock = mock(Errors.class);
        Map<Long, Question> questionMocks = new HashMap<>();
        for (long i = 1; i <= countOfQuestions; i++) {
            Question questionMock = mock(Question.class);
            questionMocks.put(i, questionMock);
            when(pageModelValidator.getQuestionRepository().findById(i)).thenReturn(questionMock);
        }
        pageModelValidator.validate(pageModelMock, errorsMock, mock(Page.class));
        for (long i = 1; i <= countOfQuestions; i++) {
            verify(pageModelValidator.getQuestionModelValidator())
                    .validate(pageModelMock.getQuestionModels().get(i), errorsMock, questionMocks.get(i));
        }
    }
}