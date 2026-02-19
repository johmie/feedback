package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.AnswerRow;
import io.feedback.survey.entity.Question;
import io.feedback.survey.entity.Result;
import io.feedback.survey.web.model.QuestionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionModelValidatorTest {

    private QuestionModelValidator questionModelValidator;

    private ResultValidator resultValidatorMock;

    @BeforeEach
    public void setUp() {
        questionModelValidator = new QuestionModelValidator();
        resultValidatorMock = mock(ResultValidator.class);

        questionModelValidator.setResultValidator(resultValidatorMock);
    }

    @Test
    public void validate_NonMatrixQuestion_ValidAnswerSelected_NoError() {
        QuestionModel questionModel = new QuestionModel();
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(1L);
        result.setAnswer(answer);
        results.add(result);
        questionModel.setResults(results);

        Errors errorsMock = mock(Errors.class);
        Question question = new Question();
        question.setId(1L);
        question.setType(Question.Type.SINGLE_CHOICE);

        when(resultValidatorMock.isValid(result)).thenReturn(true);

        questionModelValidator.validate(questionModel, errorsMock, question);

        verify(errorsMock, never()).rejectValue(
                "questionModels[1].results[0]",
                "error.answer_is_invalid"
        );
    }

    @Test
    public void validate_NonMatrixQuestion_InvalidAnswer_AddsError() {
        QuestionModel questionModel = new QuestionModel();
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        results.add(result);
        questionModel.setResults(results);

        Errors errorsMock = mock(Errors.class);
        Question question = new Question();
        question.setId(1L);
        question.setType(Question.Type.SINGLE_CHOICE);

        when(resultValidatorMock.isValid(result)).thenReturn(false);

        questionModelValidator.validate(questionModel, errorsMock, question);

        verify(errorsMock).rejectValue("questionModels[1].results[0]", "error.answer_is_invalid");
    }

    @Test
    public void validate_NonMatrixQuestion_NoValidAnswer_AddsQuestionNotAnsweredError() {
        QuestionModel questionModel = new QuestionModel();
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        results.add(result);
        questionModel.setResults(results);

        Errors errorsMock = mock(Errors.class);
        Question question = new Question();
        question.setId(1L);
        question.setType(Question.Type.SINGLE_CHOICE);

        when(resultValidatorMock.isValid(result)).thenReturn(true);

        questionModelValidator.validate(questionModel, errorsMock, question);

        verify(errorsMock).rejectValue("questionModels[1]", "error.question_not_answered");
    }

    @Test
    public void validate_NonMatrixQuestion_ValidAnswerSelected_NoQuestionNotAnsweredError() {
        QuestionModel questionModel = new QuestionModel();
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(1L);
        result.setAnswer(answer);
        results.add(result);
        questionModel.setResults(results);

        Errors errorsMock = mock(Errors.class);
        Question question = new Question();
        question.setId(1L);
        question.setType(Question.Type.SINGLE_CHOICE);

        when(resultValidatorMock.isValid(result)).thenReturn(true);

        questionModelValidator.validate(questionModel, errorsMock, question);

        verify(errorsMock, never()).rejectValue(
                "questionModels[1]",
                "error.question_not_answered"
        );
    }

    @Test
    public void validate_MatrixQuestion_NotAllRowsAnswered_AddsQuestionNotAnsweredError() {
        QuestionModel questionModel = new QuestionModel();
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(1L);
        result.setAnswer(answer);
        AnswerRow answerRow = new AnswerRow();
        answerRow.setId(100L);
        result.setAnswerRow(answerRow);
        results.add(result);
        questionModel.setResults(results);

        Errors errorsMock = mock(Errors.class);
        Question question = new Question();
        question.setId(1L);
        question.setType(Question.Type.MATRIX_SINGLE_CHOICE);

        AnswerRow answerRow1 = new AnswerRow();
        answerRow1.setId(100L);
        AnswerRow answerRow2 = new AnswerRow();
        answerRow2.setId(200L);
        Set<AnswerRow> answerRows = new HashSet<>();
        answerRows.add(answerRow1);
        answerRows.add(answerRow2);
        question.setAnswerRows(answerRows);

        questionModelValidator.validate(questionModel, errorsMock, question);

        verify(errorsMock).rejectValue("questionModels[1]", "error.question_not_answered");
    }

    @Test
    public void validate_MatrixQuestion_AllRowsAnswered_NoQuestionNotAnsweredError() {
        QuestionModel questionModel = new QuestionModel();
        List<Result> results = new ArrayList<>();

        Result result1 = new Result();
        Answer answer1 = new Answer();
        answer1.setId(1L);
        result1.setAnswer(answer1);
        AnswerRow answerRow1 = new AnswerRow();
        answerRow1.setId(100L);
        result1.setAnswerRow(answerRow1);
        results.add(result1);

        Result result2 = new Result();
        Answer answer2 = new Answer();
        answer2.setId(2L);
        result2.setAnswer(answer2);
        AnswerRow answerRow2 = new AnswerRow();
        answerRow2.setId(200L);
        result2.setAnswerRow(answerRow2);
        results.add(result2);

        questionModel.setResults(results);

        Errors errorsMock = mock(Errors.class);
        Question question = new Question();
        question.setId(1L);
        question.setType(Question.Type.MATRIX_SINGLE_CHOICE);

        AnswerRow ar1 = new AnswerRow();
        ar1.setId(100L);
        AnswerRow ar2 = new AnswerRow();
        ar2.setId(200L);
        Set<AnswerRow> answerRows = new HashSet<>();
        answerRows.add(ar1);
        answerRows.add(ar2);
        question.setAnswerRows(answerRows);

        questionModelValidator.validate(questionModel, errorsMock, question);

        verify(errorsMock, never()).rejectValue(
                "questionModels[1]",
                "error.question_not_answered"
        );
    }

    @Test
    public void validate_MatrixQuestion_NoAnswersSelected_AddsQuestionNotAnsweredError() {
        QuestionModel questionModel = new QuestionModel();
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        results.add(result);
        questionModel.setResults(results);

        Errors errorsMock = mock(Errors.class);
        Question question = new Question();
        question.setId(1L);
        question.setType(Question.Type.MATRIX_SINGLE_CHOICE);

        AnswerRow answerRow1 = new AnswerRow();
        answerRow1.setId(100L);
        Set<AnswerRow> answerRows = new HashSet<>();
        answerRows.add(answerRow1);
        question.setAnswerRows(answerRows);

        questionModelValidator.validate(questionModel, errorsMock, question);

        verify(errorsMock).rejectValue("questionModels[1]", "error.question_not_answered");
    }

    @Test
    public void validate_MatrixQuestion_MultipleAnswersForSameRow_CountIsIncremented() {
        QuestionModel questionModel = new QuestionModel();
        List<Result> results = new ArrayList<>();

        Result result1 = new Result();
        Answer answer1 = new Answer();
        answer1.setId(1L);
        result1.setAnswer(answer1);
        AnswerRow answerRow1 = new AnswerRow();
        answerRow1.setId(100L);
        result1.setAnswerRow(answerRow1);
        results.add(result1);

        Result result2 = new Result();
        Answer answer2 = new Answer();
        answer2.setId(2L);
        result2.setAnswer(answer2);
        AnswerRow answerRow2 = new AnswerRow();
        answerRow2.setId(100L);
        result2.setAnswerRow(answerRow2);
        results.add(result2);

        questionModel.setResults(results);

        Errors errorsMock = mock(Errors.class);
        Question question = new Question();
        question.setId(1L);
        question.setType(Question.Type.MATRIX_SINGLE_CHOICE);

        AnswerRow ar1 = new AnswerRow();
        ar1.setId(100L);
        Set<AnswerRow> answerRows = new HashSet<>();
        answerRows.add(ar1);
        question.setAnswerRows(answerRows);

        when(resultValidatorMock.isValid(result1)).thenReturn(true);
        when(resultValidatorMock.isValid(result2)).thenReturn(true);

        questionModelValidator.validate(questionModel, errorsMock, question);

        verify(errorsMock, never()).rejectValue(
                "questionModels[1]",
                "error.question_not_answered"
        );
    }

    @Test
    public void validate_NonMatrixQuestion_AnswerWithNullId_NotCountedAsValid() {
        QuestionModel questionModel = new QuestionModel();
        List<Result> results = new ArrayList<>();
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(null);
        result.setAnswer(answer);
        results.add(result);
        questionModel.setResults(results);

        Errors errorsMock = mock(Errors.class);
        Question question = new Question();
        question.setId(1L);
        question.setType(Question.Type.SINGLE_CHOICE);

        when(resultValidatorMock.isValid(result)).thenReturn(true);

        questionModelValidator.validate(questionModel, errorsMock, question);

        verify(errorsMock).rejectValue("questionModels[1]", "error.question_not_answered");
    }

    @Test
    public void setResultValidator_SomeResultValidator_SameValueIsReturnedByGetResultValidator() {
        ResultValidator validatorMock = mock(ResultValidator.class);

        questionModelValidator.setResultValidator(validatorMock);

        assertEquals(validatorMock, questionModelValidator.getResultValidator());
    }
}