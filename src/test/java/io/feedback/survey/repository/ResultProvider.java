package io.feedback.survey.repository;

import io.feedback.survey.entity.Answer;
import io.feedback.survey.entity.Result;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResultProvider {

    public Object[] provideForSaveResults() {
        return new Object[]{
                new Object[]{createMocksWithOneResult()},
                new Object[]{createMocksWithTwoResults()}
        };
    }

    private List<Result> createMocksWithOneResult() {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(1L);

        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);

        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMock);

        return resultMocks;
    }

    private List<Result> createMocksWithTwoResults() {
        Answer answerMockOne = mock(Answer.class);
        when(answerMockOne.getId()).thenReturn(1L);
        Answer answerMockTwo = mock(Answer.class);
        when(answerMockTwo.getId()).thenReturn(2L);

        Result resultMockOne = mock(Result.class);
        when(resultMockOne.getAnswer()).thenReturn(answerMockOne);
        Result resultMockTwo = mock(Result.class);
        when(resultMockTwo.getAnswer()).thenReturn(answerMockTwo);

        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMockOne);
        resultMocks.add(resultMockTwo);

        return resultMocks;
    }
}