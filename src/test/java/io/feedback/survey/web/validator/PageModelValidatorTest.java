package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Page;
import io.feedback.survey.entity.Question;
import io.feedback.survey.repository.QuestionRepository;
import io.feedback.survey.web.model.PageModel;
import io.feedback.survey.web.model.QuestionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

public class PageModelValidatorTest {

    private PageModelValidator pageModelValidator;

    private QuestionRepository questionRepositoryMock;
    private QuestionModelValidator questionModelValidatorMock;

    @BeforeEach
    public void setUp() {
        pageModelValidator = new PageModelValidator();
        questionRepositoryMock = mock(QuestionRepository.class);
        questionModelValidatorMock = mock(QuestionModelValidator.class);

        pageModelValidator.setQuestionRepository(questionRepositoryMock);
        pageModelValidator.setQuestionModelValidator(questionModelValidatorMock);
    }

    @Test
    public void validate_WhenQuestionModelsIsNull_AddsErrorForEachQuestion() {
        PageModel pageModel = new PageModel();
        Errors errorsMock = mock(Errors.class);

        Page page = new Page();
        Question question1 = new Question();
        question1.setId(1L);
        Question question2 = new Question();
        question2.setId(2L);

        page.setQuestions(java.util.Set.of(question1, question2));

        pageModelValidator.validate(pageModel, errorsMock, page);

        verify(errorsMock).rejectValue("questionModels[1]", "error.question_not_answered");
        verify(errorsMock).rejectValue("questionModels[2]", "error.question_not_answered");
    }

    @Test
    public void validate_WhenQuestionModelsIsNotNull_CallsQuestionModelValidator() {
        PageModel pageModel = new PageModel();
        Map<Long, QuestionModel> questionModels = new HashMap<>();
        QuestionModel questionModel = new QuestionModel();
        questionModels.put(1L, questionModel);
        pageModel.setQuestionModels(questionModels);

        Errors errorsMock = mock(Errors.class);
        Page page = new Page();

        Question question = new Question();
        question.setId(1L);

        when(questionRepositoryMock.findById(1L)).thenReturn(Optional.of(question));

        pageModelValidator.validate(pageModel, errorsMock, page);

        verify(questionModelValidatorMock).validate(questionModel, errorsMock, question);
    }

    @Test
    public void validate_WhenQuestionModelsIsNotNull_ValidatesAllQuestions() {
        PageModel pageModel = new PageModel();
        Map<Long, QuestionModel> questionModels = new HashMap<>();
        QuestionModel questionModel1 = new QuestionModel();
        QuestionModel questionModel2 = new QuestionModel();
        questionModels.put(1L, questionModel1);
        questionModels.put(2L, questionModel2);
        pageModel.setQuestionModels(questionModels);

        Errors errorsMock = mock(Errors.class);
        Page page = new Page();

        Question question1 = new Question();
        question1.setId(1L);
        Question question2 = new Question();
        question2.setId(2L);

        when(questionRepositoryMock.findById(1L)).thenReturn(Optional.of(question1));
        when(questionRepositoryMock.findById(2L)).thenReturn(Optional.of(question2));

        pageModelValidator.validate(pageModel, errorsMock, page);

        verify(questionModelValidatorMock).validate(questionModel1, errorsMock, question1);
        verify(questionModelValidatorMock).validate(questionModel2, errorsMock, question2);
    }

    @Test
    public void validate_WhenPageHasNoQuestions_NoErrorsAdded() {
        PageModel pageModel = new PageModel();
        pageModel.setQuestionModels(new HashMap<>());

        Errors errorsMock = mock(Errors.class);
        Page page = new Page();
        page.setQuestions(java.util.Set.of());

        pageModelValidator.validate(pageModel, errorsMock, page);

        verifyNoInteractions(errorsMock);
    }

    @Test
    public void validate_WhenPageHasQuestionsNotInQuestionModels_AddsErrorForMissingQuestions() {
        PageModel pageModel = new PageModel();
        Map<Long, QuestionModel> questionModels = new HashMap<>();
        QuestionModel questionModel1 = new QuestionModel();
        questionModels.put(1L, questionModel1);
        pageModel.setQuestionModels(questionModels);

        Errors errorsMock = mock(Errors.class);
        Page page = new Page();

        Question question1 = new Question();
        question1.setId(1L);
        Question question2 = new Question();
        question2.setId(2L);

        page.setQuestions(Set.of(question1, question2));

        when(questionRepositoryMock.findById(1L)).thenReturn(Optional.of(question1));

        pageModelValidator.validate(pageModel, errorsMock, page);

        verify(questionModelValidatorMock).validate(questionModel1, errorsMock, question1);
        verify(errorsMock).rejectValue("questionModels[2]", "error.question_not_answered");
    }

    @Test
    public void setQuestionRepository_SomeQuestionRepository_SameValueIsReturnedByGetQuestionRepository() {
        QuestionRepository repositoryMock = mock(QuestionRepository.class);

        pageModelValidator.setQuestionRepository(repositoryMock);

        assertEquals(repositoryMock, pageModelValidator.getQuestionRepository());
    }

    @Test
    public void setQuestionModelValidator_SomeQuestionModelValidator_SameValueIsReturnedByGetQuestionModelValidator() {
        QuestionModelValidator validatorMock = mock(QuestionModelValidator.class);

        pageModelValidator.setQuestionModelValidator(validatorMock);

        assertEquals(validatorMock, pageModelValidator.getQuestionModelValidator());
    }
}