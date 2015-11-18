package io.feedback.survey.entity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResultProvider {

    public Object[] provideWithoutId() {
        return new Object[]{
                new Object[]{createMocksWithOneResultWithoutId()},
                new Object[]{createMocksWithTwoResultsWithoutId()}
        };
    }

    public Object[] provideWithId() {
        return new Object[]{
                new Object[]{createMocksWithOneResultWithId()},
                new Object[]{createMocksWithTwoResultsWithId()}
        };
    }

    public Object[] provideWithoutAnswer() {
        return new Object[]{
                new Object[]{createMocksWithOneResultWithoutAnswer()},
                new Object[]{createMocksWithTwoResultsWithoutAnswer()}
        };
    }

    public Object[] provideForFreeTextValidator() {
        return new Object[]{
                new Object[]{createMockWithSelectedButUnsetFreeTextAnswer(), false},
                new Object[]{createMockWithSelectedButEmptyFreeTextAnswer(), false},
                new Object[]{createMockWithSelectedAndSetFreeTextAnswer(), true},
                new Object[]{createMockWithUnselectedAndUnsetFreeTextAnswer(), true},
                new Object[]{createMockWithUnselectedAndEmptyFreeTextAnswer(), true},
                new Object[]{createMockWithUnselectedAndSetFreeTextAnswer(), true}
        };
    }

    public Object[] provideForChoiceValidator() {
        return new Object[]{
                new Object[]{createMockWithSelectedAnswer(), true},
                new Object[]{createMockWithUnselectedAnswer(), true}
        };
    }

    private List<Result> createMocksWithOneResultWithoutId() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(1L);

        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);
        when(resultMock.getId()).thenReturn(null);

        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMock);

        return resultMocks;
    }

    private List<Result> createMocksWithTwoResultsWithoutId() {
        Answer answerMockOne = mock(Answer.class);
        when(answerMockOne.getId()).thenReturn(1L);
        Answer answerMockTwo = mock(Answer.class);
        when(answerMockTwo.getId()).thenReturn(2L);

        Result resultMockOne = mock(Result.class);
        when(resultMockOne.getAnswer()).thenReturn(answerMockOne);
        when(resultMockOne.getId()).thenReturn(null);
        Result resultMockTwo = mock(Result.class);
        when(resultMockTwo.getAnswer()).thenReturn(answerMockTwo);
        when(resultMockTwo.getId()).thenReturn(null);

        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMockOne);
        resultMocks.add(resultMockTwo);

        return resultMocks;
    }

    private List<Result> createMocksWithOneResultWithoutAnswer() {
        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(null);
        when(resultMock.getId()).thenReturn(null);

        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMock);

        return resultMocks;
    }

    private List<Result> createMocksWithTwoResultsWithoutAnswer() {
        Result resultMockOne = mock(Result.class);
        when(resultMockOne.getAnswer()).thenReturn(null);
        when(resultMockOne.getId()).thenReturn(null);
        Result resultMockTwo = mock(Result.class);
        when(resultMockTwo.getAnswer()).thenReturn(null);
        when(resultMockTwo.getId()).thenReturn(null);

        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMockOne);
        resultMocks.add(resultMockTwo);

        return resultMocks;
    }

    private List<Result> createMocksWithOneResultWithId() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(1L);

        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);
        when(resultMock.getId()).thenReturn(1L);

        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMock);

        return resultMocks;
    }

    private List<Result> createMocksWithTwoResultsWithId() {
        Answer answerMockOne = mock(Answer.class);
        when(answerMockOne.getId()).thenReturn(1L);
        Answer answerMockTwo = mock(Answer.class);
        when(answerMockTwo.getId()).thenReturn(2L);

        Result resultMockOne = mock(Result.class);
        when(resultMockOne.getAnswer()).thenReturn(answerMockOne);
        when(resultMockOne.getId()).thenReturn(1L);
        Result resultMockTwo = mock(Result.class);
        when(resultMockTwo.getAnswer()).thenReturn(answerMockTwo);
        when(resultMockTwo.getId()).thenReturn(2L);

        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMockOne);
        resultMocks.add(resultMockTwo);

        return resultMocks;
    }

    private Result createMockWithSelectedButUnsetFreeTextAnswer() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(1L);

        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);
        when(resultMock.getFreeText()).thenReturn(null);

        return resultMock;
    }

    private Result createMockWithSelectedButEmptyFreeTextAnswer() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(1L);

        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);
        when(resultMock.getFreeText()).thenReturn("");

        return resultMock;
    }

    private Result createMockWithSelectedAndSetFreeTextAnswer() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(1L);

        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);
        when(resultMock.getFreeText()).thenReturn("Free text");

        return resultMock;
    }

    private Result createMockWithUnselectedAndSetFreeTextAnswer() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(null);

        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);
        when(resultMock.getFreeText()).thenReturn("Free text");

        return resultMock;
    }

    private Result createMockWithUnselectedAndEmptyFreeTextAnswer() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(null);

        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);
        when(resultMock.getFreeText()).thenReturn("");

        return resultMock;
    }

    private Result createMockWithUnselectedAndUnsetFreeTextAnswer() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(null);

        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);
        when(resultMock.getFreeText()).thenReturn(null);

        return resultMock;
    }

    private Result createMockWithSelectedAnswer() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(1L);

        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);

        return resultMock;
    }

    private Result createMockWithUnselectedAnswer() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(null);

        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);

        return resultMock;
    }
}