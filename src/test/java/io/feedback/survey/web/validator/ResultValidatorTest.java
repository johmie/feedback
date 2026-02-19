package io.feedback.survey.web.validator;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.Result;
import io.feedback.survey.repository.AnswerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResultValidatorTest {

    private ResultValidator resultValidator;

    private AnswerRepository answerRepositoryMock;

    @BeforeEach
    public void setUp() {
        resultValidator = new ResultValidator();
        answerRepositoryMock = mock(AnswerRepository.class);

        resultValidator.setAnswerRepository(answerRepositoryMock);
    }

    @Test
    public void isValid_WhenAnswerIsSelected_FoundInDb_IsChoice_ReturnsTrue() {
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(1L);
        answer.setValueType(Answer.ValueType.CHOICE);
        result.setAnswer(answer);

        when(answerRepositoryMock.findById(1L)).thenReturn(Optional.of(answer));

        boolean valid = resultValidator.isValid(result);

        assertTrue(valid);
    }

    @Test
    public void isValid_WhenAnswerIsSelected_FoundInDb_IsFreeTextWithNonEmptyFreeText_ReturnsTrue() {
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(1L);
        answer.setValueType(Answer.ValueType.FREE_TEXT);
        result.setAnswer(answer);
        result.setFreeText("some feedback");

        when(answerRepositoryMock.findById(1L)).thenReturn(Optional.of(answer));

        boolean valid = resultValidator.isValid(result);

        assertTrue(valid);
    }

    @Test
    public void isValid_WhenAnswerIsSelected_FoundInDb_IsFreeTextWithEmptyFreeText_ReturnsFalse() {
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(1L);
        answer.setValueType(Answer.ValueType.FREE_TEXT);
        result.setAnswer(answer);
        result.setFreeText("");

        when(answerRepositoryMock.findById(1L)).thenReturn(Optional.of(answer));

        boolean valid = resultValidator.isValid(result);

        assertFalse(valid);
    }

    @Test
    public void isValid_WhenAnswerIsSelected_FoundInDb_IsFreeTextWithNullFreeText_ReturnsTrue() {
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(1L);
        answer.setValueType(Answer.ValueType.FREE_TEXT);
        result.setAnswer(answer);
        result.setFreeText(null);

        when(answerRepositoryMock.findById(1L)).thenReturn(Optional.of(answer));

        boolean valid = resultValidator.isValid(result);

        assertTrue(valid);
    }

    @Test
    public void isValid_WhenAnswerIsSelected_NotFoundInDb_ReturnsFalse() {
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(1L);
        result.setAnswer(answer);

        when(answerRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        boolean valid = resultValidator.isValid(result);

        assertFalse(valid);
    }

    @Test
    public void isValid_WhenAnswerIsNull_ReturnsTrue() {
        Result result = new Result();
        result.setAnswer(null);

        boolean valid = resultValidator.isValid(result);

        assertTrue(valid);
    }

    @Test
    public void isValid_WhenAnswerIdIsNull_ReturnsTrue() {
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(null);
        result.setAnswer(answer);

        boolean valid = resultValidator.isValid(result);

        assertTrue(valid);
    }

    @Test
    public void isValid_WhenAnswerIsNotNullAndIdIsNotNull_NotFoundInDb_ReturnsFalse() {
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(1L);
        result.setAnswer(answer);

        when(answerRepositoryMock.findById(1L)).thenReturn(Optional.empty());

        boolean valid = resultValidator.isValid(result);

        assertFalse(valid);
    }

    @Test
    public void isValid_WhenAnswerIsSelected_FoundInDb_ValueTypeIsNull_ReturnsFalse() {
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(1L);
        answer.setValueType(null);
        result.setAnswer(answer);
        result.setFreeText("some feedback");

        when(answerRepositoryMock.findById(1L)).thenReturn(Optional.of(answer));

        boolean valid = resultValidator.isValid(result);

        assertFalse(valid);
    }

    @Test
    public void isAnswerNotSelected_WhenAnswerIsNull_ReturnsTrue() {
        Result result = new Result();
        result.setAnswer(null);

        boolean notSelected = resultValidator.isAnswerNotSelected(result);

        assertTrue(notSelected);
    }

    @Test
    public void isAnswerNotSelected_WhenAnswerIdIsNull_ReturnsTrue() {
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(null);
        result.setAnswer(answer);

        boolean notSelected = resultValidator.isAnswerNotSelected(result);

        assertTrue(notSelected);
    }

    @Test
    public void isAnswerNotSelected_WhenAnswerAndIdAreNotNull_ReturnsFalse() {
        Result result = new Result();
        Answer answer = new Answer();
        answer.setId(1L);
        result.setAnswer(answer);

        boolean notSelected = resultValidator.isAnswerNotSelected(result);

        assertFalse(notSelected);
    }

    @Test
    public void setAnswerRepository_SomeAnswerRepository_SameValueIsReturnedByGetAnswerRepository() {
        AnswerRepository repositoryMock = mock(AnswerRepository.class);

        resultValidator.setAnswerRepository(repositoryMock);

        assertEquals(repositoryMock, resultValidator.getAnswerRepository());
    }
}