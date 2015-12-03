package io.feedback.survey.entity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ResultMockProvider {

    public Object[] provideListWithoutId() {
        return new Object[]{
                new Object[]{createListWithOneResultWithoutId()},
                new Object[]{createListWithTwoResultsWithoutId()}
        };
    }

    public Object[] provideListWithId() {
        return new Object[]{
                new Object[]{createListWithOneResultWithId()},
                new Object[]{createListWithTwoResultsWithId()}
        };
    }

    public Object[] provideListWithoutAnswer() {
        return new Object[]{
                new Object[]{createListWithOneResultWithoutAnswer()},
                new Object[]{createListWithTwoResultsWithoutAnswer()}
        };
    }

    public Object[] provideOneWithValidAndInvalidFreeText() {
        return new Object[]{
                new Object[]{createOneWithSelectedButUnsetFreeText(), false},
                new Object[]{createOneWithSelectedButEmptyFreeText(), false},
                new Object[]{createOneWithSelectedAndSetFreeText(), true},
                new Object[]{createOneWithUnselectedAndUnsetFreeText(), true},
                new Object[]{createOneWithUnselectedAndEmptyFreeText(), true},
                new Object[]{createOneWithUnselectedAndSetFreeText(), true}
        };
    }

    public Object[] provideOneWithSelectedAndUnselectedAnswer() {
        return new Object[]{
                new Object[]{createOneWithSelectedAnswer(), true},
                new Object[]{createOneWithUnselectedAnswer(), true}
        };
    }

    private List<Result> createListWithOneResultWithoutId() {
        Answer answerMock = createAnswerMock(1L);
        Result resultMock = createResultMock(null, answerMock);
        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMock);
        return resultMocks;
    }

    private List<Result> createListWithTwoResultsWithoutId() {
        Answer answerMockOne = createAnswerMock(1L);
        Answer answerMockTwo = createAnswerMock(2L);
        Result resultMockOne = createResultMock(null, answerMockOne);
        Result resultMockTwo = createResultMock(null, answerMockTwo);
        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMockOne);
        resultMocks.add(resultMockTwo);
        return resultMocks;
    }

    private List<Result> createListWithOneResultWithId() {
        Answer answerMock = createAnswerMock(null);
        Result resultMock = createResultMock(1L, answerMock);
        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMock);
        return resultMocks;
    }

    private List<Result> createListWithTwoResultsWithId() {
        Answer answerMockOne = createAnswerMock(null);
        Answer answerMockTwo = createAnswerMock(null);
        Result resultMockOne = createResultMock(1L, answerMockOne);
        Result resultMockTwo = createResultMock(2L, answerMockTwo);
        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMockOne);
        resultMocks.add(resultMockTwo);
        return resultMocks;
    }

    private List<Result> createListWithOneResultWithoutAnswer() {
        Result resultMock = createResultMock(null, null);
        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMock);
        return resultMocks;
    }

    private List<Result> createListWithTwoResultsWithoutAnswer() {
        Result resultMockOne = createResultMock(null, null);
        Result resultMockTwo = createResultMock(null, null);
        List<Result> resultMocks = new ArrayList<>();
        resultMocks.add(resultMockOne);
        resultMocks.add(resultMockTwo);
        return resultMocks;
    }

    private Result createOneWithSelectedButUnsetFreeText() {
        Answer answerMock = createAnswerMock(1L);
        Result resultMock = createResultMock(null, answerMock);
        when(resultMock.getFreeText()).thenReturn(null);
        return resultMock;
    }

    private Result createOneWithSelectedButEmptyFreeText() {
        Answer answerMock = createAnswerMock(1L);
        Result resultMock = createResultMock(null, answerMock);
        when(resultMock.getFreeText()).thenReturn("");
        return resultMock;
    }

    private Result createOneWithSelectedAndSetFreeText() {
        Answer answerMock = createAnswerMock(1L);
        Result resultMock = createResultMock(null, answerMock);
        when(resultMock.getFreeText()).thenReturn("Free text");
        return resultMock;
    }

    private Result createOneWithUnselectedAndUnsetFreeText() {
        Answer answerMock = createAnswerMock(null);
        Result resultMock = createResultMock(null, answerMock);
        when(resultMock.getFreeText()).thenReturn(null);
        return resultMock;
    }

    private Result createOneWithUnselectedAndEmptyFreeText() {
        Answer answerMock = createAnswerMock(null);
        Result resultMock = createResultMock(null, answerMock);
        when(resultMock.getFreeText()).thenReturn("");
        return resultMock;
    }

    private Result createOneWithUnselectedAndSetFreeText() {
        Answer answerMock = createAnswerMock(null);
        Result resultMock = createResultMock(null, answerMock);
        when(resultMock.getFreeText()).thenReturn("Free text");
        return resultMock;
    }

    private Result createOneWithSelectedAnswer() {
        Answer answerMock = createAnswerMock(1L);
        Result resultMock = createResultMock(null, answerMock);
        return resultMock;
    }

    private Result createOneWithUnselectedAnswer() {
        Answer answerMock = createAnswerMock(null);
        Result resultMock = createResultMock(null, answerMock);
        return resultMock;
    }

    private Answer createAnswerMock(Long answerId) {
        Answer answerMock = mock(Answer.class);
        when(answerMock.getId()).thenReturn(answerId);
        return answerMock;
    }

    private Result createResultMock(Long resultId, Answer answerMock) {
        Result resultMock = mock(Result.class);
        when(resultMock.getAnswer()).thenReturn(answerMock);
        when(resultMock.getId()).thenReturn(resultId);
        return resultMock;
    }
}